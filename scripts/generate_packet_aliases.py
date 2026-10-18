"""Generate compatibility aliases from the old Yarn registry and cached mappings.

The output is a patch for review, never an automatic source overwrite.
Usage: python scripts/generate_packet_aliases.py --yarn PATH --official PATH --output PATH
"""
import argparse
from pathlib import Path
import re
import subprocess

parser = argparse.ArgumentParser(description=__doc__)
parser.add_argument('--yarn', required=True)
parser.add_argument('--official', required=True)
parser.add_argument('--output', required=True)
args = parser.parse_args()
official = {}
for line in Path(args.official).read_text(encoding='utf-8').splitlines():
    if line.startswith('c\t'):
        _, _, intermediary, named = line.split('\t')
        official[intermediary] = named
yarn = {}
for line in Path(args.yarn).read_text(encoding='utf-8').splitlines():
    if line.startswith('c\t'):
        _, named, _, intermediary = line.split('\t')
        yarn[named] = official.get(intermediary, '')
root = Path(__file__).resolve().parents[1]
upstream = (root / 'meteor-client/src/main/java/meteordevelopment/meteorclient/utils/network/PacketUtils.java').read_text(encoding='utf-8')
modern = dict(re.findall(r'builder.put\("([^"]+)", (\w+\.\w+)\);', upstream))
old = subprocess.check_output(['git', 'show', 'HEAD:src/main/java/com/hongminh54/marisaoptimize/utils/network/PacketUtils.java'], cwd=root, text=True, encoding='utf-8')
aliases = {}
for name, cls in re.findall(r'_PACKETS_R.put\("([^"]+)", ([\w.]+)\.class\);', old):
    mapped = yarn.get(cls.replace('.', '/'), '').replace('/', '.').rsplit('.', 1)[-1].replace('$', '.')
    if mapped in modern:
        aliases[name] = modern[mapped]
aliases.update({
    'PlayerMoveC2SPacket.Full': 'GamePacketTypes.SERVERBOUND_MOVE_PLAYER_POS_ROT',
    'PlayerMoveC2SPacket.LookAndOnGround': 'GamePacketTypes.SERVERBOUND_MOVE_PLAYER_ROT',
    'PlayerMoveC2SPacket.OnGroundOnly': 'GamePacketTypes.SERVERBOUND_MOVE_PLAYER_STATUS_ONLY',
    'PlayerMoveC2SPacket.PositionAndOnGround': 'GamePacketTypes.SERVERBOUND_MOVE_PLAYER_POS',
    'EntityS2CPacket.MoveRelative': 'GamePacketTypes.CLIENTBOUND_MOVE_ENTITY_POS',
    'EntityS2CPacket.Rotate': 'GamePacketTypes.CLIENTBOUND_MOVE_ENTITY_ROT',
    'EntityS2CPacket.RotateAndMoveRelative': 'GamePacketTypes.CLIENTBOUND_MOVE_ENTITY_POS_ROT',
})
imports = '\n'.join(line for line in upstream.splitlines() if line.startswith('import net.minecraft.network.protocol.') and line.endswith('PacketTypes;'))
source = '''/*
 * This file is part of the Marisa Optimize distribution.
 * Copyright (c) Meteor Development.
 */
package com.hongminh54.marisaoptimize.utils.network;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
''' + imports + '''
import java.util.Map;

/** Legacy Yarn names for loading pre-26.2 packet selections. */
final class LegacyPacketNames {
    static final Map<String, PacketType<? extends Packet<?>>> ALIASES = Map.ofEntries(
''' + ',\n'.join(f'        Map.entry("{name}", {value})' for name, value in sorted(aliases.items())) + '''
    );
    private LegacyPacketNames() {}
}
'''
patch = '*** Begin Patch\n*** Add File: src/main/java/com/hongminh54/marisaoptimize/utils/network/LegacyPacketNames.java\n' + ''.join('+' + line + '\n' for line in source.splitlines()) + '*** End Patch\n'
Path(args.output).write_text(patch, encoding='utf-8')
print(f'Generated {len(aliases)} compatibility aliases. Abstract packet base classes have no protocol type.')
