package dev.hyperskys.serverpatcher.patcher.log4j.nms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.ChatType;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import java.util.Arrays;

public class WrapperPlayServerChat extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Server.CHAT;

    public WrapperPlayServerChat(PacketContainer packet) {
        super(packet, TYPE);
    }

    public WrappedChatComponent getMessage() {
        return handle.getChatComponents().read(0);
    }

    public ChatType getChatType() {
        return handle.getChatTypes().read(0);
    }

    @Deprecated
    public byte getPosition() {
        Byte position = handle.getBytes().readSafely(0);
        if (position != null) {
            return position;
        } else {
            return getChatType().getId();
        }
    }

    @Deprecated
    public void setPosition(byte value) {
        handle.getBytes().writeSafely(0, value);

        if (EnumWrappers.getChatTypeClass() != null)
        {
            Arrays.stream(ChatType.values()).filter(t -> t.getId() == value).findAny()
                    .ifPresent(t -> handle.getChatTypes().writeSafely(0, t));
        }
    }
}
