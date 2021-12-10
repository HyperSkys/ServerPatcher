package dev.hyperskys.serverpatcher;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.hyperskys.serverpatcher.command.ServerPatcherCommand;
import dev.hyperskys.serverpatcher.patcher.log4j.nms.WrapperPlayClientChat;
import dev.hyperskys.serverpatcher.patcher.log4j.nms.WrapperPlayServerChat;
import dev.hyperskys.serverpatcher.patcher.log4j.tools.PatternChecker;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ServerPatcher extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Server.CHAT, PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                WrapperPlayServerChat wrapper = new WrapperPlayServerChat(event.getPacket());
                WrappedChatComponent component = wrapper.getMessage();
                if (component == null) return;
                String message = TextComponent.toPlainText(ComponentSerializer.parse(component.getJson()));

                if (PatternChecker.isExploit(message)) {
                    event.setCancelled(true);
                }
            }

            @Override
            public void onPacketReceiving(PacketEvent event) {
                WrapperPlayClientChat wrapper = new WrapperPlayClientChat(event.getPacket());
                if (PatternChecker.isExploit(wrapper.getMessage())) {
                    getLogger().severe( "[Patcher] The player " + event.getPlayer().getName() + " has attempted the log4j exploit and it has been blocked.");
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if(online.hasPermission("patcher.getAlerts") || online.isOp()) {
                            online.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&bPatcher&7] &fThe player &a"+ event.getPlayer().getName() + " &fhas attempted the log4j exploit."));
                        }
                    }
                    event.setCancelled(true);
                }
            }
        });

        Objects.requireNonNull(getCommand("serverpatcher")).setExecutor(new ServerPatcherCommand(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        ProtocolLibrary.getProtocolManager().removePacketListeners(this);
    }
}
