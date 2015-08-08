package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.mode.config.ConfigMode;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class ConfigCommandExecutor extends AbstractCommandExecutor {
    private final ConfigMode configMode;

    public ConfigCommandExecutor(ConfigMode configMode){
        this.configMode = configMode;
    }

    @Override
    protected boolean onPlayerCommand(Player player, String[] args) {
        if (args.length > 0){
            /**
             * Search arguments and work with them
             */
            if (args[0] == "validate" && args.length == 1){
                configMode.validConfig(player);
                return true;
            }
        } else {
            /**
             * Bad usage, show all Commands
             */
            player.sendMessage("Bad Usage! List of commands : ");
            player.sendMessage("/config validate");
            //(...)
        }

        return false;
    }
}
