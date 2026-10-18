"""Check explicit Minecraft mixin targets against the cached official-name JAR.

This is a static smoke check, not a substitute for applying Mixins in a client.
Checks target class existence and named injection methods (including descriptors).
"""
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path
import os
import re
import subprocess

ROOT = Path(__file__).resolve().parents[1]
JAR = next(p for p in (ROOT / '.gradle/loom-cache/minecraftMaven/net/minecraft').glob('*/26.2/*.jar') if not p.name.endswith('-sources.jar'))
JAVAP = str(Path(os.environ['JAVA_HOME']) / 'bin' / ('javap.exe' if os.name == 'nt' else 'javap'))


def audit(path):
    text = path.read_text(encoding='utf-8')
    annotation = re.search(r'@Mixin\((.*?)\)\s*(?:public|abstract|class|interface)', text, re.S)
    if not annotation:
        return []
    imports = dict((name.rsplit('.', 1)[-1], name) for name in re.findall(r'import ([\w.]+);', text))
    target = re.search(r'(?:value\s*=\s*)?([\w.]+)\.class', annotation[1])
    if target:
        parts = target[1].split('.')
        name = imports.get(parts[0], parts[0]) + ''.join('$' + part for part in parts[1:])
    else:
        target = re.search(r'targets\s*=\s*"([\w.$]+)"', annotation[1])
        name = target[1] if target else ''
    if not name.startswith(('net.minecraft.', 'com.mojang.blaze3d.')):
        return []
    result = subprocess.run([JAVAP, '-p', '-s', '-classpath', str(JAR), name], capture_output=True, text=True)
    if result.returncode:
        return [f'{path.name}: missing class {name}']
    methods = set()
    lines = result.stdout.splitlines()
    for index, line in enumerate(lines):
        member = re.search(r'([\w.$<>]+)\([^;]*\);$', line.strip())
        if member:
            method = member[1].rsplit('.', 1)[-1]
            if member[1] == name:
                method = '<init>'
            methods.add(method)
            if index + 1 < len(lines) and 'descriptor:' in lines[index + 1]:
                methods.add(method + lines[index + 1].split('descriptor:', 1)[1].strip())
    errors = []
    # An interface with concrete/default methods is a standard interface mixin,
    # not a pure accessor mixin; it cannot target a concrete Minecraft class.
    if (re.search(r'\binterface\s+\w+', text)
            and re.search(r'\bdefault\s+', text)
            and not re.search(r'\binterface\s+' + re.escape(name) + r'\b', result.stdout)):
        errors.append(f'{path.name}: interface mixin with default methods targets class {name}')
    for shadow in re.finditer(r'@Shadow\b(?:(?!;|\{).)*?\b([\w$]+)\s*(?:\([^;]*\))?\s*;', text, re.S):
        member = shadow[1]
        if not re.search(r'\b' + re.escape(member) + r'(?:\(|;)', result.stdout):
            errors.append(f'{path.name}: {name} missing shadow member {member}')
    for match in re.finditer(r'\bmethod\s*=\s*("[^"]+"|\{[^}]+\})', text):
        for method in re.findall(r'"([^"]+)"', match[1]):
            if method == '<clinit>' or '*' in method:
                continue
            if method not in methods:
                errors.append(f'{path.name}: {name} missing injection method {method}')
    return errors


if __name__ == '__main__':
    files = list((ROOT / 'src/main/java/com/hongminh54/marisaoptimize/mixin').rglob('*.java'))
    with ThreadPoolExecutor(max_workers=8) as pool:
        errors = [error for result in pool.map(audit, files) for error in result]
    print('\n'.join(errors) if errors else f'Mixin target audit passed ({len(files)} source files scanned).')
    raise SystemExit(bool(errors))
