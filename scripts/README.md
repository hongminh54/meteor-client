# Migration checks (Minecraft 26.2 / JDK 25)

Run from the repository root after Loom has prepared Minecraft:

```sh
./gradlew build -I scripts/check-serialization.init.gradle compileMigrationCommands compileMigrationEvents checkMigrationSerialization checkMigrationInventory checkMigrationFriends checkMigrationSlider checkMigrationAccount checkMigrationPackets
python scripts/validate_resources.py
python scripts/validate_render_api.py
python scripts/audit_mixin_targets.py
```

Set `JAVA_HOME` to JDK 25 for bytecode checks. These scripts use the local Loom cache.
The isolated tests use the real Minecraft API with narrowly scoped stubs for unrelated
client globals, disk/network executors and GUI layout. Account checks do not log in.
Packet checks bootstrap registries but send no packets.

The mixin audit checks explicit Minecraft/Blaze3D target classes, injection method
names/descriptors and shadow member names. It does **not** validate every invocation
instruction, local capture, inherited member, optional-mod hook or actual injection.
The render validator provides additional checks for selected GUI/render signatures.

## Audit fixes

- Move per-frame uniform/input handling from removed `RenderSystem.flipFrame` to
  `Minecraft.renderFrame`, after `LevelRenderer.endFrame`.
- Correct the firework `lifetime` shadow and remove an unused obsolete Minecraft
  framebuffer setter/shadow.
- Retain results from immutable vector operations in AutoWasp, Scaffold and Elytra
  Packet mode, and guard missing AutoWasp targets.
- Preserve 225 legacy concrete Yarn packet names when loading older profiles.
  Abstract packet bases have no concrete protocol type; attack is now separate
  from interaction and must be selected independently.
- Update CI to the 26.2 branch and JDK 25. Exclude the local upstream checkout.

## Runtime validation still required

Build and static checks are not a runtime certification. Before a stable release,
test client startup/mixin application, GUI/HUD and HiDPI rendering, ESP/Chams/outlines,
Freecam targeting/culling, custom anti-xray memory/filtering, inventory/menu actions,
fake players, account authentication and optional Sodium/Lithium/Baritone integrations.
GodMode cannot grant server-authoritative invulnerability on remote servers.

`plan_baseline_migration.py` and `generate_packet_aliases.py` are review-only migration
helpers requiring the local upstream checkout and historical mappings; normal builds
and tests do not require that checkout.
# Display items before joining a world (26.2)

Run `./gradlew.bat -I scripts/check-serialization.init.gradle checkMigrationDisplayItems`.
This bootstraps real Minecraft registries without binding world/server item components,
then checks display-only stacks, category icons, model/name components, counts and empty
icons. It also verifies the helper leaves the global item holder unbound. It does not
test rendering or joining a world. Never use `DisplayItemUtils` stacks for gameplay.
