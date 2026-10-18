"""Validate Marisa resource wiring without starting Minecraft or requiring Gradle."""

import json
from pathlib import Path
import re
import sys


ROOT = Path(__file__).resolve().parents[1]
RESOURCES = ROOT / "src/main/resources"
JAVA = ROOT / "src/main/java"


def validate():
    errors = []
    documents = {}
    for path in RESOURCES.rglob("*.json"):
        try:
            documents[path] = json.loads(path.read_text(encoding="utf-8"))
        except (ValueError, UnicodeError) as error:
            errors.append(f"{path.relative_to(ROOT)}: {error}")

    metadata = documents.get(RESOURCES / "fabric.mod.json", {})
    for name in [metadata.get("icon"), metadata.get("accessWidener"), *metadata.get("mixins", [])]:
        if not name or not (RESOURCES / name).is_file():
            errors.append(f"Metadata references missing resource: {name}")

    for name in metadata.get("mixins", []):
        config = documents.get(RESOURCES / name, {})
        package = config.get("package", "").replace(".", "/")
        for entry in config.get("client", []) + config.get("mixins", []) + config.get("server", []):
            path = JAVA / package / (entry.replace(".", "/") + ".java")
            if not path.is_file():
                errors.append(f"{name}: missing mixin source {entry}")
        plugin = config.get("plugin")
        if plugin and not (JAVA / (plugin.replace(".", "/") + ".java")).is_file():
            errors.append(f"{name}: missing mixin plugin {plugin}")

    shaders = RESOURCES / "assets/marisa-optimize/shaders"
    expected_inputs = {
        "blur.vert": ["Position"],
        "passthrough.vert": ["Position"],
        "post-process/base.vert": ["Position"],
        "pos_color.vert": ["Position", "Color"],
        "pos_tex_color.vert": ["Position", "Texture", "Color"],
        "text.vert": ["Position", "Texture", "Color"],
    }
    for name, expected in expected_inputs.items():
        path = shaders / name
        if not path.is_file():
            errors.append(f"Missing shader: {name}")
            continue
        source = path.read_text(encoding="utf-8")
        inputs = re.findall(r"layout\s*\(location\s*=\s*(\d+)\)\s*in\s+\w+\s+(\w+)\s*;", source)
        actual = [value for _, value in sorted(inputs, key=lambda item: int(item[0]))]
        if actual != expected:
            errors.append(f"{name}: expected vertex inputs {expected}, got {actual}")
        if name in ("blur.vert", "passthrough.vert", "post-process/base.vert"):
            if not re.search(r"in\s+vec2\s+Position\s*;", source):
                errors.append(f"{name}: fullscreen Position must match the POS2 vertex format")

    pipeline = JAVA / "com/hongminh54/marisaoptimize/renderer/MeteorRenderPipelines.java"
    pipeline_source = pipeline.read_text(encoding="utf-8")
    locations = re.findall(r'\.withLocation\(MarisaOptimize.identifier\("([^"\n]+)"\)\)', pipeline_source)
    for name in set(locations):
        if locations.count(name) > 1:
            errors.append(f"Duplicate render pipeline location: {name}")
    for name in re.findall(r'\.with(?:Vertex|Fragment)Shader\(MarisaOptimize.identifier\("([^"\n]+)"\)\)', pipeline_source):
        if not (RESOURCES / "assets/marisa-optimize" / name).is_file():
            errors.append(f"Pipeline references missing shader: {name}")

    return errors


if __name__ == "__main__":
    failures = validate()
    for failure in failures:
        print(f"ERROR: {failure}", file=sys.stderr)
    if failures:
        sys.exit(1)
    print("Resource validation passed: JSON, metadata, mixin sources and shader inputs.")
