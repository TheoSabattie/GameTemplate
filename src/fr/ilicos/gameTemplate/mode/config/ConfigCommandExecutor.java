package fr.ilicos.gameTemplate.mode.config;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Theo  Sabattie on 07/08/2015.
 */
public class ConfigCommandExecutor implements CommandExecutor {
    private final ConfigMode configMode;

    public ConfigCommandExecutor(ConfigMode configMode){
        this.configMode = configMode;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;

            if (args.length > 0){
                /**
                 * Search arguments and work with them
                 */
                if (args[0] == "validate" && args.length == 1){
                    configMode.validConfig(player);
                }
            } else {
                /**
                 * Bad usage, show all Commands
                 */
                player.sendMessage("Bad Usage! List of commands : ");
                player.sendMessage("/config validate");
                //(...)
            }

        } else {
            Bukkit.getLogger().warning("Configuration editing must be in game!");
        }

        return false;
    }
}
