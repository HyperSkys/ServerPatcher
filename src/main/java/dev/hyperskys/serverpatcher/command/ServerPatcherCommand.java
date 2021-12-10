package dev.hyperskys.serverpatcher.command;

import dev.hyperskys.serverpatcher.ServerPatcher;
import dev.hyperskys.serverpatcher.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ServerPatcherCommand implements CommandExecutor {

    ServerPatcher serverPatcher;
    public ServerPatcherCommand(ServerPatcher serverPatcher) {
        this.serverPatcher = serverPatcher;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage; /serverpatcher <help>");
        } else {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(CC.translate("&8&m---------------[-&r &b&lServer&f&lPatcher &8&m-]---------------&r"));
                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&bDeveloper &7- &fHyperSkys"));
                sender.sendMessage(CC.translate("&bVersion &7- &fv1.0.5"));
                sender.sendMessage(CC.translate("&bDependency &7- &fProtocolLib v4.7.0"));
                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&8&m-----------------------------------------&r"));
            } else {
                sender.sendMessage(ChatColor.RED + "Usage; /serverpatcher <help>");
            }
        }

        return true;
    }
}
