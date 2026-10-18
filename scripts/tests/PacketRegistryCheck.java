import com.hongminh54.marisaoptimize.utils.network.PacketUtils;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import net.minecraft.network.protocol.game.GamePacketTypes;

public class PacketRegistryCheck {
    public static void main(String[] args) {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        for (var type : PacketUtils.PACKETS) {
            if (!type.equals(PacketUtils.getPacket(PacketUtils.getName(type)))) {
                throw new AssertionError("Packet name round trip: " + type);
            }
        }
        if (PacketUtils.getPacket("PlayerMoveC2SPacket.Full") != GamePacketTypes.SERVERBOUND_MOVE_PLAYER_POS_ROT) throw new AssertionError("Legacy movement alias");
        if (PacketUtils.getPacket("EntityVelocityUpdateS2CPacket") != GamePacketTypes.CLIENTBOUND_SET_ENTITY_MOTION) throw new AssertionError("Legacy velocity alias");
        if (PacketUtils.getPacket("PlayerInteractEntityC2SPacket") != GamePacketTypes.SERVERBOUND_INTERACT) throw new AssertionError("Legacy interact alias");
        if (PacketUtils.getPacket("invalid packet!") != null) throw new AssertionError("Invalid packet name");
        System.out.println("Packet registry checks passed: protocol round trips and legacy Yarn aliases; no packets sent.");
    }
}
