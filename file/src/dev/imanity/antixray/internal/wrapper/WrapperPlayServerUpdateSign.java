// 
// Decompiled by Procyon v0.6.0
// 

package dev.imanity.antixray.internal.wrapper;

import io.fairyproject.libs.packetevents.event.PacketSendEvent;
import io.fairyproject.libs.packetevents.util.Vector3i;
import io.fairyproject.libs.packetevents.wrapper.PacketWrapper;

public class WrapperPlayServerUpdateSign extends PacketWrapper<WrapperPlayServerUpdateSign>
{
    private Vector3i blockPosition;
    private String[] components;
    
    public WrapperPlayServerUpdateSign(final PacketSendEvent packetSendEvent) {
        super(packetSendEvent);
    }
    
    public void read() {
        this.blockPosition = new Vector3i(this.readLong());
        for (int i = 0; i < 4; ++i) {
            if (this.components == null) {
                this.components = new String[4];
            }
            this.components[i] = this.readString(this.getMaxMessageLength());
        }
    }
    
    public void write() {
        this.writeLong(this.blockPosition.getSerializedPosition());
        for (int i = 0; i < 4; ++i) {
            String s = this.components[i];
            if (s == null) {
                s = "";
            }
            this.writeString(s);
        }
    }
    
    public Vector3i getBlockPosition() {
        return this.blockPosition;
    }
    
    public String[] getComponents() {
        return this.components;
    }
}
