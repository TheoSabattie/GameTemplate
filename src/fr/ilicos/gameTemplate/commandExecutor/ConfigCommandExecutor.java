package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.mode.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class ConfigCommandExecutor extends AbstractCommandExecutor {
    private Config config;

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    protected boolean onPlayerCommand(Player player, String[] args) {
        if (args.length > 0){
            /**
             * Search arguments and work with them
             */
            if (args[0].equals("validation") && args.length == 1){
                onConfigValidation(player);
                return true;
            }
        }

        player.sendMessage("Bad Usage! List of commands : ");

        for (String invalidValue : config.getInvalidValues()){
            player.sendMessage("/config " + invalidValue + " {args...}");
        }

        player.sendMessage("/config validation");

        return false;
    }

    private void onConfigValidation(Player player) {
        if (config.isCompleted()){
            Bukkit.broadcastMessage("End of configuration, setup Game mode");
            MainManager.getInstance().setupGameMode();
        } else {
            player.sendMessage("Configuration is not completed, it misses : ");

            for (String valueName : config.getInvalidValues()){
                player.sendMessage(" - " + valueName);
            }
        }
    }
}
