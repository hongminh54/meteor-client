import com.hongminh54.marisaoptimize.utils.render.color.Color;
import com.hongminh54.marisaoptimize.utils.render.color.SettingColor;
import com.hongminh54.marisaoptimize.settings.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Set;

/** Runs without a game client against the real Minecraft 26.2 NBT implementation. */
public class SerializationCheck {
    private enum Mode { First, Second }
    private static void check(boolean condition, String description) {
        if (!condition) throw new AssertionError(description);
    }

    public static void main(String[] args) throws Exception {
        Color original = new Color(12, 34, 56, 78);
        CompoundTag tag = original.toTag();
        check(tag.keySet().equals(Set.of("r", "g", "b", "a")), "Color schema changed");
        check(original.equals(new Color().fromTag(tag)), "Color round trip failed");
        check(new Color().fromTag(new CompoundTag()).equals(new Color(0, 0, 0, 0)), "Missing-key defaults changed");

        CompoundTag malformed = new CompoundTag();
        malformed.putInt("r", -10);
        malformed.putInt("g", 400);
        malformed.putString("b", "not an integer");
        malformed.putInt("a", 999);
        check(new Color().fromTag(malformed).equals(new Color(0, 255, 0, 255)), "Validation/default fallback failed");

        SettingColor setting = new SettingColor(15, 90, 220, 160, true);
        CompoundTag settingTag = setting.toTag();
        check(settingTag.keySet().equals(Set.of("r", "g", "b", "a", "rainbow")), "SettingColor schema changed");
        check(setting.equals(new SettingColor().fromTag(settingTag)), "SettingColor round trip failed");
        check(!new SettingColor().fromTag(new CompoundTag()).rainbow, "Rainbow default changed");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        NbtIo.writeCompressed(settingTag, bytes);
        CompoundTag restored = NbtIo.readCompressed(new ByteArrayInputStream(bytes.toByteArray()), NbtAccounter.unlimitedHeap());
        check(setting.equals(new SettingColor().fromTag(restored)), "Compressed NBT round trip failed");
        check(new Color(original.getPacked()).equals(original), "ARGB packing changed");
        // Minecraft text colors store RGB only; alpha belongs to our render color.
        check(original.toTextColor().getValue() == (original.getPacked() & 0xFFFFFF), "TextColor RGB conversion failed");
        checkSettings();
        checkBlockPositionSetting();
        checkChat();
        checkWindowConfig();
        checkCoordinates();
        checkCommandParsers();
        checkMovementApi();
        System.out.println("Serialization checks passed: schemas, defaults, clamping, color packing and compressed NBT round trips.");
    }

    private static void checkBlockPositionSetting() {
        var origin = new net.minecraft.core.BlockPos(-31, 72, 2048);
        var setting = new BlockPosSetting.Builder().name("position").defaultValue(origin).build();
        CompoundTag tag = setting.toTag();
        check(java.util.Arrays.equals(tag.getIntArray("value").orElseThrow(), new int[] {-31, 72, 2048}), "Block position schema changed");
        var restored = new BlockPosSetting.Builder().name("position").defaultValue(net.minecraft.core.BlockPos.ZERO).build();
        restored.fromTag(tag);
        check(restored.get().equals(origin), "Block position NBT round trip failed");
        check(restored.parse("10,-20,30") && restored.get().equals(new net.minecraft.core.BlockPos(10, -20, 30)), "Block position parser changed");
        check(!restored.parse("10, invalid, 30"), "Invalid block position accepted");
        restored.reset();
        check(restored.get().equals(net.minecraft.core.BlockPos.ZERO), "Block position reset changed");
    }

    private static void checkMovementApi() {
        var armorIndices = new java.util.HashSet<Integer>();
        for (var slot : net.minecraft.world.entity.EquipmentSlotGroup.ARMOR) {
            if (slot.getType() == net.minecraft.world.entity.EquipmentSlot.Type.HUMANOID_ARMOR) armorIndices.add(slot.getIndex());
        }
        check(armorIndices.equals(java.util.Set.of(0, 1, 2, 3)), "Drop armor slot indices changed");
        double[][] expected = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        for (int i = 0; i < expected.length; i++) {
            var forward = net.minecraft.world.phys.Vec3.directionFromRotation(0, i * 90).normalize();
            check(Math.abs(forward.x - expected[i][0]) < 1e-6 && Math.abs(forward.z - expected[i][1]) < 1e-6,
                "Horizontal clip yaw convention changed");
            check(Math.abs(forward.y) < 1e-6, "Horizontal clip gained vertical movement");
        }
        var position = new net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Pos(-12.5, 64.25, 30, false, true);
        check(position.getX(0) == -12.5 && position.getY(0) == 64.25 && position.getZ(0) == 30, "Position packet coordinates changed");
        check(!position.isOnGround() && position.horizontalCollision(), "Position packet flags changed");
        var status = new net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.StatusOnly(true, false);
        check(status.isOnGround() && !status.horizontalCollision(), "Status packet flags changed");
        check(!status.hasPosition() && position.hasPosition(), "Movement packet presence flags changed");
        System.out.println("Movement API checks passed: cardinal yaw vectors and movement packet coordinates/flags; no packets sent or server behavior tested.");
    }

    private static void checkCommandParsers() throws Exception {
        var nbt = com.hongminh54.marisaoptimize.commands.arguments.CompoundNbtTagArgumentType.create();
        var reader = new com.mojang.brigadier.StringReader("  {foo:bar,nested:{value:15},color:'$a',literal:'$$'} trailing");
        var tag = nbt.parse(reader);
        check(tag.getStringOr("foo", "").equals("bar"), "Compound string changed");
        check(tag.getCompoundOrEmpty("nested").getIntOr("value", 0) == 15, "Nested compound changed");
        check(tag.getStringOr("color", "").equals("§a"), "Color escape changed");
        check(tag.getStringOr("literal", "").equals("$"), "Literal dollar escape changed");
        check(reader.getRemaining().equals(" trailing"), "Compound parser consumed next argument");
        for (String invalid : new String[] {"", "{foo:bar", "{foo:}"}) {
            try {
                nbt.parse(new com.mojang.brigadier.StringReader(invalid));
                throw new AssertionError("Accepted invalid compound: " + invalid);
            } catch (com.mojang.brigadier.exceptions.CommandSyntaxException expected) {
                // Malformed SNBT must remain a Brigadier syntax error.
            }
        }
        var direction = com.hongminh54.marisaoptimize.commands.arguments.DirectionArgumentType.create();
        for (var value : net.minecraft.core.Direction.values()) {
            check(direction.parse(new com.mojang.brigadier.StringReader(value.getSerializedName())) == value, "Direction parse changed");
        }
        try {
            direction.parse(new com.mojang.brigadier.StringReader("sideways"));
            throw new AssertionError("Accepted invalid direction");
        } catch (com.mojang.brigadier.exceptions.CommandSyntaxException expected) {
        }
        System.out.println("Command parser checks passed: nested SNBT, color escapes, argument boundary, invalid inputs and six directions; player/registry lookup compile-checked only.");
    }

    private static void checkCoordinates() throws Exception {
        var parser = com.hongminh54.marisaoptimize.commands.arguments.BlockPosArgumentType.blockPos();
        var absolute = parser.parse(new com.mojang.brigadier.StringReader("12 -64 30"));
        check(!absolute.isXRelative() && !absolute.isYRelative() && !absolute.isZRelative(), "Absolute coordinate flags changed");
        var relative = parser.parse(new com.mojang.brigadier.StringReader("~0.5 ~1 ~-5"));
        check(relative.isXRelative() && relative.isYRelative() && relative.isZRelative(), "Relative coordinate flags changed");
        var local = parser.parse(new com.mojang.brigadier.StringReader("^1 ^ ^-5"));
        check(local.isXRelative() && local.isYRelative() && local.isZRelative(), "Local coordinate flags changed");
        for (String invalid : new String[] {"1 2", "^1 ~2 ^3", "~ ~", "1 nope 3"}) {
            try {
                parser.parse(new com.mojang.brigadier.StringReader(invalid));
                throw new AssertionError("Accepted invalid coordinate: " + invalid);
            } catch (com.mojang.brigadier.exceptions.CommandSyntaxException expected) {
                // Syntax validation does not need a player or world.
            }
        }
        var chunk = new net.minecraft.world.level.ChunkPos(Math.floorDiv(-1, 16), Math.floorDiv(32, 16));
        check(chunk.x() == -1 && chunk.z() == 2, "Block-to-chunk conversion changed");
        System.out.println("Coordinate checks passed: absolute/relative/local syntax, invalid inputs and negative chunk conversion; player resolution not tested.");
    }

    private static void checkWindowConfig() {
        var config = new com.hongminh54.marisaoptimize.gui.utils.WindowConfig();
        config.fromTag(new CompoundTag());
        check(config.expanded && config.x == -1 && config.y == -1, "Missing window fields changed defaults");
        config.expanded = false;
        config.x = 123.5;
        config.y = 456.25;
        CompoundTag tag = config.toTag();
        check(tag.keySet().equals(Set.of("expanded", "x", "y")), "Window config schema changed");
        var restored = new com.hongminh54.marisaoptimize.gui.utils.WindowConfig().fromTag(tag);
        check(!restored.expanded && restored.x == 123.5 && restored.y == 456.25, "Window config round trip failed");
        CompoundTag partial = new CompoundTag();
        partial.putDouble("x", 42);
        restored.fromTag(partial);
        check(!restored.expanded && restored.x == 42 && restored.y == 456.25, "Partial window config reset untouched values");
        System.out.println("Window config checks passed: schema, defaults, partial updates and NBT round trip.");
    }

    private static void checkChat() throws Exception {
        var formatter = com.hongminh54.marisaoptimize.utils.player.ChatUtils.class.getDeclaredMethod("formatMsg", String.class, net.minecraft.ChatFormatting.class);
        formatter.setAccessible(true);
        var text = (net.minecraft.network.chat.Component) formatter.invoke(null, "normal (highlight)bright(default) plain", net.minecraft.ChatFormatting.GRAY);
        check(text.getString().equals("normal bright plain"), "Chat formatting changed message content");
        check(text.getSiblings().get(1).getStyle().getColor().equals(net.minecraft.network.chat.TextColor.fromLegacyFormat(net.minecraft.ChatFormatting.WHITE)), "Highlight color failed");
        var unknown = (net.minecraft.network.chat.Component) formatter.invoke(null, "keep (unknown) text", net.minecraft.ChatFormatting.GRAY);
        check(unknown.getString().equals("keep (unknown) text"), "Unknown formatting token was lost");
        var click = new com.hongminh54.marisaoptimize.utils.misc.text.MeteorClickEvent("#goto 1 2 3");
        // Resolving Action initializes Minecraft dialog registries and needs a bootstrapped game.
        check(click.value.equals("#goto 1 2 3"), "Client click event payload changed");
        var coords = com.hongminh54.marisaoptimize.utils.player.ChatUtils.formatCoords(new net.minecraft.world.phys.Vec3(1, 2, 3));
        check(coords.getString().equals("1, 2, 3"), "Coordinate text changed");
        System.out.println("Chat checks passed: formatting, unknown tokens, coordinates and client click payload (action API compile-checked only).");
    }

    private static void checkSettings() throws Exception {
        BoolSetting bool = new BoolSetting.Builder().name("enabled").defaultValue(true).build();
        bool.set(false);
        check(bool.toTag().keySet().equals(Set.of("name", "value")), "Setting schema changed");
        bool.reset();
        check(bool.get(), "Boolean reset failed");
        CompoundTag disabled = new CompoundTag();
        disabled.putBoolean("value", false);
        bool.fromTag(disabled);
        check(!bool.get(), "Boolean load failed");

        IntSetting integer = new IntSetting.Builder().name("count").defaultValue(3).min(1).max(10).build();
        integer.set(7);
        CompoundTag intTag = integer.toTag();
        integer.reset();
        integer.fromTag(intTag);
        check(integer.get() == 7, "Integer round trip failed");
        intTag.putInt("value", 100);
        integer.fromTag(intTag);
        check(integer.get() == 7, "Invalid integer bypassed validation");

        DoubleSetting decimal = new DoubleSetting.Builder().name("scale").defaultValue(1.0).build();
        decimal.set(2.5);
        CompoundTag doubleTag = decimal.toTag();
        decimal.reset();
        decimal.fromTag(doubleTag);
        check(decimal.get() == 2.5, "Double round trip failed");

        EnumSetting<Mode> mode = new EnumSetting.Builder<Mode>().name("mode").defaultValue(Mode.First).build();
        mode.set(Mode.Second);
        CompoundTag enumTag = mode.toTag();
        mode.reset();
        mode.fromTag(enumTag);
        check(mode.get() == Mode.Second, "Enum round trip failed");
        enumTag.putString("value", "unknown");
        mode.fromTag(enumTag);
        check(mode.get() == Mode.Second, "Unknown enum changed value");

        SettingColor defaultColor = new SettingColor(1, 2, 3, 4, true);
        ColorSetting color = new ColorSetting.Builder().name("color").defaultValue(defaultColor).build();
        color.get().set(90, 80, 70, 60);
        check(defaultColor.r == 1, "Color setting mutated its default");
        CompoundTag colorTag = color.toTag();
        color.reset();
        color.fromTag(colorTag);
        check(color.get().r == 90 && color.get().a == 60 && color.get().rainbow, "Color setting round trip failed");
        Vector3dSetting vector = new Vector3dSetting.Builder().name("position").defaultValue(new org.joml.Vector3d(1, 2, 3)).build();
        vector.set(4, 5, 6);
        CompoundTag vectorTag = vector.toTag();
        vector.reset();
        vector.fromTag(vectorTag);
        check(vector.get().equals(new org.joml.Vector3d(4, 5, 6)), "Vector round trip failed");
        vector.fromTag(new CompoundTag());
        check(vector.get().x == 4, "Missing vector changed current value");

        // The production group constructor is package-private.
        var constructor = SettingGroup.class.getDeclaredConstructor(String.class, boolean.class);
        constructor.setAccessible(true);
        SettingGroup group = constructor.newInstance("General", true);
        group.add(integer);
        group.add(bool);
        group.add(new BoolSetting.Builder().name("unchanged").defaultValue(true).build());
        CompoundTag groupTag = group.toTag();
        check(groupTag.keySet().equals(Set.of("name", "sectionExpanded", "settings")), "Group schema changed");
        check(groupTag.getListOrEmpty("settings").size() == 2, "Group should save changed settings only");
        integer.reset();
        bool.reset();
        group.sectionExpanded = false;
        group.fromTag(groupTag);
        check(integer.get() == 7 && !bool.get() && group.sectionExpanded, "Group round trip failed");
        CompoundTag unknown = new CompoundTag();
        unknown.putString("name", "removed-setting");
        groupTag.getListOrEmpty("settings").add(unknown);
        group.fromTag(groupTag);
        check(integer.get() == 7, "Unknown setting affected existing values");
        System.out.println("Settings checks passed: primitive/enum/color round trips, schema, reset, validation and mutable defaults.");
    }
}
