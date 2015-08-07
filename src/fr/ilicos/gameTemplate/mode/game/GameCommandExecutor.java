package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.MainManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Theo  Sabattie on 07/08/2015.
 */
public class GameCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0){
            Bukkit.broadcastMessage("Switching in Config Mode...");
            MainManager.getInstance().setupConfigMode();

            return true;
        }

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            player.sendMessage("Bad Usage, to edit configuration : /config");
        } else {
            Bukkit.getLogger().warning("Configuration editing must be in game!");
        }

        return false;
    }
}
