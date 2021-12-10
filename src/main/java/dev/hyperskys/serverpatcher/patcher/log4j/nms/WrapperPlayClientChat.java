package dev.hyperskys.serverpatcher.patcher.log4j.nms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public class WrapperPlayClientChat extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Client.CHAT;

    public WrapperPlayClientChat(PacketContainer packet) {
        super(packet, TYPE);
    }

    public String getMessage() {
        return handle.getStrings().read(0);
    }

}
