package fr.ilicos.gameTemplate.commandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public abstract class AbstractCommandExecutor implements CommandExecutor {
    protected AbstractCommandExecutor(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player){
            return onPlayerCommand((Player)commandSender, args);
        }

        return onConsoleCommand(args);
    }

    protected abstract boolean onPlayerCommand(Player player, String[] args);

    protected boolean onConsoleCommand(String[] args){
        Bukkit.getLogger().warning("Configuration editing must be in game!");

        return false;
    }
}
