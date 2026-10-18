"""Check migrated GUI hooks against the actual cached Minecraft 26.2 bytecode.

Run with JAVA_HOME pointing to JDK 25 after Loom has prepared Minecraft.
This checks API signatures, not whether Mixin injection points apply at runtime.
"""

import os
from pathlib import Path
import re
import shutil
import subprocess
import sys


ROOT = Path(__file__).resolve().parents[1]
JAVA = ROOT / "src/main/java/com/hongminh54/marisaoptimize"


def validate():
    java_home = os.environ.get("JAVA_HOME")
    javap = str(Path(java_home) / "bin/javap.exe") if java_home else shutil.which("javap")
    if not javap:
        raise RuntimeError("Set JAVA_HOME to JDK 25 or put javap on PATH")
    jars = list((ROOT / ".gradle/loom-cache/minecraftMaven/net/minecraft").glob("*/26.2/*.jar"))
    jars = [path for path in jars if not path.name.endswith("-sources.jar")]
    if len(jars) != 1:
        raise RuntimeError(f"Expected one Loom Minecraft 26.2 jar, found {len(jars)}")

    errors = []
    classes = {}

    def bytecode(name):
        if name not in classes:
            result = subprocess.run(
                [javap, "-private", "-s", "-classpath", str(jars[0]), name],
                capture_output=True, text=True, check=True,
            )
            classes[name] = result.stdout
        return classes[name]

    for source_name, owner in (
        ("MinecraftClientAccessor", "net.minecraft.client.Minecraft"),
        ("ClientPlayNetworkHandlerAccessor", "net.minecraft.client.multiplayer.ClientPacketListener"),
        ("MinecraftServerAccessor", "net.minecraft.server.MinecraftServer"),
        ("ResourceReloadLoggerAccessor", "net.minecraft.client.ResourceLoadStateTracker"),
        ("ReloadStateAccessor", "net.minecraft.client.ResourceLoadStateTracker$ReloadState"),
        ("PlayerSkinProviderAccessor", "net.minecraft.client.resources.SkinManager"),
        ("FileCacheAccessor", "net.minecraft.client.resources.SkinManager$TextureCache"),
        ("CreativeInventoryScreenAccessor", "net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen"),
        ("ItemGroupsAccessor", "net.minecraft.world.item.CreativeModeTabs"),
        ("MountScreenHandlerAccessor", "net.minecraft.world.inventory.AbstractMountInventoryMenu"),
        ("AbstractBlockAccessor", "net.minecraft.world.level.block.state.BlockBehaviour"),
        ("AbstractSignEditScreenAccessor", "net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen"),
        ("ClientChunkManagerAccessor", "net.minecraft.client.multiplayer.ClientChunkCache"),
        ("ClientChunkMapAccessor", "net.minecraft.client.multiplayer.ClientChunkCache$Storage"),
        ("ChunkAccessor", "net.minecraft.world.level.chunk.ChunkAccess"),
        ("BlockHitResultAccessor", "net.minecraft.world.phys.BlockHitResult"),
        ("BlockEntityRenderManagerAccessor", "net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher"),
        ("ChatHudAccessor", "net.minecraft.client.gui.components.ChatComponent"),
        ("ClientConnectionAccessor", "net.minecraft.network.Connection"),
        ("ClientPlayerEntityAccessor", "net.minecraft.client.player.LocalPlayer"),
        ("ClientPlayerInteractionManagerAccessor", "net.minecraft.client.multiplayer.MultiPlayerGameMode"),
        ("ContainerComponentAccessor", "net.minecraft.world.item.component.ItemContainerContents"),
        ("DrawContextAccessor", "net.minecraft.client.gui.GuiGraphicsExtractor"),
        ("DirectionAccessor", "net.minecraft.core.Direction"),
        ("EntityBucketItemAccessor", "net.minecraft.world.item.MobBucketItem"),
        ("EntityAccessor", "net.minecraft.world.entity.Entity"),
        ("LivingEntityAccessor", "net.minecraft.world.entity.LivingEntity"),
        ("FishingBobberEntityAccessor", "net.minecraft.world.entity.projectile.FishingHook"),
        ("HandledScreenAccessor", "net.minecraft.client.gui.screens.inventory.AbstractContainerScreen"),
        ("ItemRenderStateAccessor", "net.minecraft.client.renderer.item.ItemStackRenderState"),
        ("EntityTrackingSectionAccessor", "net.minecraft.world.level.entity.EntitySection"),
        ("EntityVelocityUpdateS2CPacketAccessor", "net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket"),
    ):
        source = (JAVA / "mixin" / (source_name + ".java")).read_text(encoding="utf-8")
        code = bytecode(owner)
        for field in re.findall(r'@Accessor\("([^"]+)"\)', source):
            if not re.search(r"\b" + re.escape(field) + r";", code):
                errors.append(f"{source_name}: missing accessor field {owner}.{field}")
        for method in re.findall(r'@Invoker\("([^"]+)"\)', source):
            if not re.search(r"\b" + re.escape(method) + r"\(", code):
                errors.append(f"{source_name}: missing invoker method {owner}.{method}")
    if not re.search(r"\bgetOverworldClockTime\(\);\s+descriptor: \(\)J", bytecode("net.minecraft.world.level.Level")):
        errors.append("Missing overworld clock API for Utils.getWorldTime")
    option_source = (JAVA / "mixin/SimpleOptionMixin.java").read_text(encoding="utf-8")
    option_code = bytecode("net.minecraft.client.OptionInstance")
    for field, descriptor in (("value", "Ljava/lang/Object;"), ("onValueUpdate", "Lnet/minecraft/client/OptionInstance$ValueUpdateListener;")):
        if not re.search(r"\b" + field + r";\s+descriptor: " + re.escape(descriptor), option_code):
            errors.append(f"Missing OptionInstance shadow field {field} with expected descriptor")
        if not re.search(r"\b" + field + r"\b", option_source):
            errors.append(f"SimpleOptionMixin does not use expected shadow {field}")
    if not re.search(r"\bvalueChanged\([^\n]*\);\s+descriptor: \(Ljava/lang/Object;\)V", bytecode("net.minecraft.client.OptionInstance$ValueUpdateListener")):
        errors.append("Missing OptionInstance update callback API")
    for owner, method, descriptor in (
        ("com.mojang.blaze3d.platform.Window", "getScreenWidth", "()I"),
        ("com.mojang.blaze3d.platform.Window", "getWidth", "()I"),
        ("com.mojang.blaze3d.platform.InputConstants", "grabOrReleaseMouse", "(Lcom/mojang/blaze3d/platform/Window;IDD)V"),
    ):
        signature = re.compile(r"\b" + method + r"\([^\n]*\);\s+descriptor: " + re.escape(descriptor) + r"(?:\s|$)")
        if not signature.search(bytecode(owner)):
            errors.append(f"Missing GUI window API: {owner}.{method}{descriptor}")

    for source_name in (
        "mixin/InGameHudMixin.java", "mixin/GuiRendererMixin.java",
        "mixin/KeyboardMixin.java", "mixin/MouseMixin.java",
        "mixin/GameRendererMixin.java", "mixin/CameraMixin.java",
        "mixin/AbstractBlockMixin.java", "mixin/AbstractBoatEntityMixin.java",
        "mixin/AbstractClientPlayerEntityMixin.java", "mixin/AbstractSignBlockEntityRendererMixin.java",
        "mixin/ArmorFeatureRendererMixin.java", "mixin/AttackRangeComponentMixin.java",
        "mixin/ArmorStandEntityRendererMixin.java", "mixin/BannerBlockEntityRendererMixin.java",
        "mixin/BeaconBlockEntityRendererMixin.java", "mixin/BeaconScreenMixin.java",
        "mixin/BiomeColorsMixin.java", "mixin/BlockCollisionSpliteratorMixin.java",
        "mixin/BlockColorsMixin.java", "mixin/BlockEntityRenderManagerMixin.java",
        "mixin/BlockItemMixin.java", "mixin/BlockMixin.java",
        "mixin/BookScreenMixin.java", "mixin/BookEditScreenMixin.java",
        "mixin/BossBarHudMixin.java",
        "mixin/BundleItemMixin.java", "mixin/BundleTooltipSubmenuHandlerMixin.java",
        "mixin/ChatHudMixin.java", "mixin/ChatScreenMixin.java",
        "mixin/ChatInputSuggestorMixin.java", "mixin/MessageHandlerMixin.java",
        "mixin/BlockModelRendererMixin.java", "mixin/BlockRenderManagerMixin.java",
        "mixin/BlockRenderLayersMixin.java", "mixin/FluidRendererMixin.java",
        "mixin/ClientConnectionMixin.java",
        "mixin/CapeFeatureRendererMixin.java", "mixin/ClientPlayerInteractionManagerMixin.java",
        "mixin/ClientWorldMixin.java", "mixin/CobwebBlockMixin.java",
        "mixin/ClientPlayerEntityMixin.java", "mixin/ChunkBorderDebugRendererMixin.java",
        "mixin/ChunkOcclusionDataBuilderMixin.java",
        "mixin/CompassStateMixin.java", "mixin/ConnectScreenMixin.java",
        "mixin/CrashReportMixin.java", "mixin/ScreenMixin.java",
        "mixin/ClientPlayNetworkHandlerMixin.java",
        "mixin/ContainerComponentMixin.java", "mixin/DisconnectedScreenMixin.java",
        "mixin/DrawContextMixin.java",
        "mixin/DownloaderMixin.java", "mixin/ElytraFeatureRendererMixin.java",
        "mixin/EndCrystalEntityModelMixin.java", "mixin/EndCrystalEntityRendererMixin.java",
        "mixin/EntityRendererMixin.java",
        "mixin/EntityMixin.java", "mixin/EntityFluidInteractionMixin.java",
        "mixin/EntityHitboxDebugRendererMixin.java",
        "mixin/FireworkRocketEntityMixin.java", "mixin/FishingBobberEntityMixin.java",
        "mixin/FogRendererMixin.java",
        "mixin/GameOptionsMixin.java", "mixin/HandledScreenMixin.java",
        "mixin/HeadFeatureRendererMixin.java", "mixin/HeldItemRendererMixin.java",
        "mixin/HungerManagerMixin.java",
        "mixin/InGameOverlayRendererMixin.java",
        "mixin/ItemMixin.java", "mixin/ItemRendererMixin.java",
        "mixin/ItemGroupsMixin.java", "mixin/ItemStackMixin.java",
        "mixin/KeyboardInputMixin.java", "mixin/LightningEntityRendererMixin.java",
        "mixin/LightmapTextureManagerMixin.java", "mixin/LightmapRenderStateExtractorMixin.java",
        "mixin/LivingEntityMixin.java",
        "mixin/LivingEntityRendererMixin.java",
        "mixin/MinecraftClientMixin.java",
        "mixin/GuiMixin.java",
        "mixin/MultiplayerScreenMixin.java", "mixin/MutableTextMixin.java",
        "mixin/ParticleManagerMixin.java",
        "mixin/PacketByteBufMixin.java",
        "mixin/PlayerEntityMixin.java",
        "mixin/PlayerEntityRendererMixin.java",
        "mixin/PlayerListEntryMixin.java", "mixin/PowderSnowBlockMixin.java",
        "mixin/PlayerInteractEntityC2SPacketMixin.java",
        "mixin/PlayerListHudMixin.java",
        "mixin/ProjectileUtilMixin.java",
        "mixin/RegistriesMixin.java", "mixin/RenderTickCounterDynamicMixin.java",
        "mixin/ShaderLoaderMixin.java", "mixin/ServerPlayerEntityMixin.java",
        "mixin/ServerResourcePackLoaderMixin.java", "mixin/ShadowPiecesCommandRendererMixin.java",
        "mixin/SkyRenderingMixin.java",
        "mixin/SlimeBlockMixin.java",
        "mixin/SoundSystemMixin.java",
        "mixin/StatusEffectInstanceMixin.java",
        "mixin/SplashTextResourceSupplierMixin.java", "mixin/StatusEffectFogModifierMixin.java",
        "mixin/StringHelperMixin.java", "mixin/SweetBerryBushBlockMixin.java",
        "mixin/TextRendererMixin.java", "mixin/TextVisitFactoryMixin.java",
        "mixin/TitleScreenMixin.java",
        "mixin/TooltipComponentMixin.java", "mixin/TooltipDisplayComponentMixin.java", "mixin/TransformationMixin.java",
        "mixin/TridentItemMixin.java",
        "mixin/WorldChunkMixin.java", "mixin/WorldBorderMixin.java",
        "mixin/WorldRendererMixin.java", "mixin/LevelExtractorMixin.java",
        "mixin/MapRendererMixin.java", "mixin/MobEntityMixin.java",
        "mixin/MobSpawnerBlockEntityRendererMixin.java",
        "mixin/EnchantingTableBlockEntityRendererMixin.java", "mixin/EntityRenderManagerMixin.java",
    ):
        source = (JAVA / source_name).read_text(encoding="utf-8")
        target = re.search(r"@Mixin\((?:value\s*=\s*)?([\w.]+)\.class", source).group(1)
        outer, _, nested = target.partition(".")
        qualified = re.search(r"import ([\w.]+\." + outer + r");", source).group(1)
        if nested:
            qualified += "$" + nested.replace(".", "$")
        target_code = bytecode(qualified)
        for method in re.findall(r'@(?:Inject|ModifyArg|ModifyArgs|ModifyVariable|ModifyReturnValue|ModifyExpressionValue|WrapOperation|WrapWithCondition|ModifyReceiver|Redirect)\(\s*method = "([^"]+)"', source):
            method, separator, descriptor = method.partition("(")
            target_method = qualified if method == "<init>" else method
            if separator and not re.search(r"\b" + re.escape(target_method) + r"\([^\n]*\)(?: throws [^;\n]+)?;\s+descriptor: " + re.escape("(" + descriptor) + r"(?:\s|$)", target_code):
                errors.append(f"{source_name}: missing target descriptor {qualified}.{method}({descriptor}")
            if method == "<init>":
                if qualified + "(" not in target_code:
                    errors.append(f"{source_name}: missing constructor on {qualified}")
            elif not re.search(r"\b" + re.escape(method) + r"\(", target_code):
                errors.append(f"{source_name}: missing target method {qualified}.{method}")
        for owner, method, descriptor in re.findall(r'target = "L([^;]+);([^("]+)(\([^"\n]+)"', source):
            # This validator's classpath is the Minecraft jar, not external libraries.
            if not owner.startswith(("net/minecraft/", "com/mojang/blaze3d/")):
                continue
            code = bytecode(owner.replace("/", "."))
            name = owner.replace("/", ".") if method == "<init>" else method
            signature = re.compile(r"\b" + re.escape(name) + r"\([^\n]*\)(?: throws [^;\n]+)?;\s+descriptor: " + re.escape(descriptor) + r"(?:\s|$)")
            # javap lists declared members only; invocation owners may inherit methods.
            found = bool(signature.search(code))
            seen = {owner.replace("/", ".")}
            pending = [code]
            while pending and not found and method != "<init>":
                declaration = pending.pop().splitlines()[1]
                # Remove generic arguments before following superclass and default interfaces.
                while re.search(r"<[^<>]*>", declaration):
                    declaration = re.sub(r"<[^<>]*>", "", declaration)
                parents = re.search(r"\b(?:extends|implements) (.+?)\s*\{", declaration)
                if not parents:
                    continue
                for parent in re.split(r",\s*|\s+implements\s+", parents.group(1)):
                    if parent in seen:
                        continue
                    seen.add(parent)
                    parent_code = bytecode(parent)
                    if signature.search(parent_code):
                        found = True
                        break
                    pending.append(parent_code)
            if not found:
                errors.append(f"{source_name}: missing invocation API {owner}.{method}{descriptor}")

    graphics = bytecode("net.minecraft.client.gui.GuiGraphicsExtractor")
    for descriptor in (
        "(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V",
        "(Lnet/minecraft/world/item/ItemStack;II)V",
        "(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
    ):
        if "descriptor: " + descriptor not in graphics:
            errors.append(f"Missing GUI text/item signature: {descriptor}")
    for owner, method, descriptor in (
        ("net.minecraft.client.KeyboardHandler", "keyPress", "(JILnet/minecraft/client/input/KeyEvent;)V"),
        ("net.minecraft.client.KeyboardHandler", "charTyped", "(JLnet/minecraft/client/input/CharacterEvent;)V"),
        ("net.minecraft.client.MouseHandler", "onButton", "(JLnet/minecraft/client/input/MouseButtonInfo;I)V"),
        ("net.minecraft.client.MouseHandler", "onScroll", "(JDD)V"),
    ):
        signature = re.compile(r"\b" + method + r"\([^\n]*\);\s+descriptor: " + re.escape(descriptor) + r"(?:\s|$)")
        if not signature.search(bytecode(owner)):
            errors.append(f"Input callback signature mismatch: {owner}.{method}{descriptor}")
    mapping = bytecode("net.minecraft.client.KeyMapping")
    for field in ("ALL", "key", "clickCount"):
        if not re.search(r"\b" + field + r";", mapping):
            errors.append(f"Missing KeyMapping accessor field: {field}")
    if not re.search(r"\brelease\(\)", mapping):
        errors.append("Missing KeyMapping invoker method: release")
    for source_name, owner in (
        ("mixin/CameraMixin.java", "net.minecraft.client.Camera"),
        ("mixin/GameRendererMixin.java", "net.minecraft.client.renderer.GameRenderer"),
    ):
        source = (JAVA / source_name).read_text(encoding="utf-8")
        code = bytecode(owner)
        for field in re.findall(r"@Shadow\s+(?:@Final\s+)?private\s+\w+\s+(\w+)\s*;", source):
            if not re.search(r"\b" + re.escape(field) + r";", code):
                errors.append(f"{source_name}: missing shadow field {field}")
    render_debug = subprocess.run(
        [javap, "-private", "-c", "-l", "-classpath", str(jars[0]), "net.minecraft.client.renderer.GameRenderer"],
        capture_output=True, text=True, check=True,
    ).stdout
    for name, descriptor in {
        "projectionMatrix": "Lorg/joml/Matrix4f;",
        "modelViewMatrix": "Lorg/joml/Matrix4fc;",
        "worldPartialTicks": "F",
        "bobStack": "Lcom/mojang/blaze3d/vertex/PoseStack;",
    }.items():
        if not re.search(r"\b" + name + r"\s+" + re.escape(descriptor), render_debug):
            errors.append(f"Missing render local metadata: {name} {descriptor}")
    if "// String hand" not in render_debug:
        errors.append("Missing render profiler section: hand")
    screen = bytecode("net.minecraft.client.gui.screens.Screen")
    for name in ("extractBackground", "extractRenderState", "extractPanorama", "onClose", "isPauseScreen", "resize"):
        if not re.search(r"\b" + name + r"\(", screen):
            errors.append(f"Missing Screen API: {name}")
    for name, members in {
        "KeyEvent": ("key", "scancode", "modifiers"),
        "CharacterEvent": ("codepoint", "codepointAsString"),
        "MouseButtonEvent": ("x", "y", "buttonInfo", "button", "modifiers"),
    }.items():
        code = bytecode("net.minecraft.client.input." + name)
        for member in members:
            if not re.search(r"\b" + member + r"\(\)", code):
                errors.append(f"Missing input API: {name}.{member}")
    for folder in (JAVA / "gui/widgets", JAVA / "gui/renderer"):
        for source in folder.rglob("*.java"):
            content = source.read_text(encoding="utf-8")
            if re.search(r"import net\.minecraft\.client\.(?:gui\.Click|input\.(?:KeyInput|CharInput));", content):
                errors.append(f"Old input import in migrated widgets: {source.relative_to(JAVA)}")
    for source in JAVA.rglob("*.java"):
        content = source.read_text(encoding="utf-8")
        if re.search(r"onRenderBefore\(DrawContext\b", content):
            errors.append(f"Old WidgetScreen render override: {source.relative_to(JAVA)}")
    return errors


if __name__ == "__main__":
    try:
        failures = validate()
    except (RuntimeError, OSError, subprocess.CalledProcessError) as error:
        print(f"ERROR: {error}", file=sys.stderr)
        sys.exit(1)
    for failure in failures:
        print(f"ERROR: {failure}", file=sys.stderr)
    if failures:
        sys.exit(1)
    print("Render API validation passed: GUI hooks, invocation descriptors, text/item APIs, screen/input APIs and widget migration checks.")
