"""Generate (never apply) a patch for files unchanged from the pre-official baseline.

Only the two Minecraft migration commits are used, not subsequent upstream features.
Headers, Marisa namespace, branding URLs and established local interface names are retained.
Review the generated patch before applying it with the patch tool.
"""
from pathlib import Path
import difflib
import io
import re
import subprocess
import tarfile

ROOT = Path(__file__).resolve().parents[1]
LOCAL = ROOT / 'src/main/java/com/hongminh54/marisaoptimize'
PREFIX = 'src/main/java/meteordevelopment/meteorclient/'


def snapshot(ref):
    data = subprocess.run(['git', '-C', str(ROOT / 'meteor-client'), 'archive', ref, PREFIX], capture_output=True, check=True).stdout
    with tarfile.open(fileobj=io.BytesIO(data)) as archive:
        return {entry.name[len(PREFIX):]: archive.extractfile(entry).read().decode('utf-8')
                for entry in archive if entry.isfile() and entry.name.endswith('.java')}


def localize(text):
    text = text.replace('meteordevelopment.meteorclient', 'com.hongminh54.marisaoptimize').replace('MeteorClient', 'MarisaOptimize')
    # Preserve existing Marisa interfaces/accessors instead of creating duplicate mixins.
    names = {
        'KeyInputEvent': 'KeyEvent', 'KeyMappingAccessor': 'KeyBindingAccessor',
        'ClientPacketListenerAccessor': 'ClientPlayNetworkHandlerAccessor',
        'CreativeModeInventoryScreenAccessor': 'CreativeInventoryScreenAccessor',
        'CreativeModeTabsAccessor': 'ItemGroupsAccessor',
        'AbstractMountInventoryMenuAccessor': 'MountScreenHandlerAccessor',
        'ResourceLoadStateTrackerAccessor': 'ResourceReloadLoggerAccessor',
        'IOptionInstance': 'ISimpleOption', 'IMultiPlayerGameMode': 'IClientPlayerInteractionManager',
        'meteor$getClickCount': 'meteor$getTimesPressed', 'meteor$setClickCount': 'meteor$setTimesPressed',
    }
    for old, new in names.items():
        text = re.sub(r'\b' + re.escape(old) + r'\b', new, text)
    return text


def normalized(text):
    text = re.sub(r'/\*.*?\*/', '', localize(text), flags=re.S)
    imports = sorted(re.findall(r'^import .*;', text, re.M))
    text = re.sub(r'^import .*;', '', text, flags=re.M)
    return re.sub(r'\s+', '', text), imports


def main():
    import argparse
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--areas', nargs='+', required=True)
    parser.add_argument('--output', required=True)
    args = parser.parse_args()
    old = snapshot('9ffe8e4d6^')
    migrated = snapshot('e43739752')
    patch = ['*** Begin Patch\n']
    selected = []
    for path in sorted(LOCAL.rglob('*.java')):
        name = path.relative_to(LOCAL).as_posix()
        if not any(name.startswith(area.rstrip('/') + '/') for area in args.areas):
            continue
        if name not in old or name not in migrated:
            continue
        current = path.read_text(encoding='utf-8')
        if normalized(current) != normalized(old[name]):
            continue
        target = localize(migrated[name]).replace('\r\n', '\n')
        header = re.match(r'/\*.*?\*/', current, re.S)
        if header:
            target = re.sub(r'^/\*.*?\*/', lambda _: header.group(), target, count=1, flags=re.S)
        # Never import upstream branding URLs into matching files.
        target = target.replace('https://github.com/MeteorDevelopment/meteor-client', 'https://github.com/TheBlueSkyARL/marisa-optimize')
        if current.splitlines() == target.splitlines():
            continue
        # Patch records must stay separate even when a Java source lacks a final newline.
        # Otherwise difflib can concatenate a removed closing brace with an added line.
        diff = list(difflib.unified_diff(
            [line + '\n' for line in current.splitlines()],
            [line + '\n' for line in target.splitlines()], n=3))
        patch.append('*** Update File: ' + path.relative_to(ROOT).as_posix() + '\n')
        for line in diff[2:]:
            patch.append('@@\n' if line.startswith('@@') else line)
        selected.append(name)
    patch.append('*** End Patch\n')
    Path(args.output).write_text(''.join(patch), encoding='utf-8')
    print(f'Planned {len(selected)} files; patch: {args.output}')
    print('\n'.join(selected))


if __name__ == '__main__':
    main()
