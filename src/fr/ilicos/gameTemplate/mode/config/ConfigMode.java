package fr.ilicos.gameTemplate.mode.config;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.commandExecutor.ConfigCommandExecutor;
import fr.ilicos.gameTemplate.mode.AbtractMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class ConfigMode extends AbtractMode {
    private final Config config;

    public ConfigMode(Config config) {
        super();
        this.config = config;
    }

    @Override
    protected void setupCommandExecutor() {
        commandExecutor = new ConfigCommandExecutor(this);
    }

    public void validConfig(Player player) {
        MainManager mainManager = MainManager.getInstance();

        if (mainManager.configIsCompleted(config)){
            mainManager.saveConfig(config);
            Bukkit.broadcastMessage("End of configuration, setup Game mode");
            mainManager.setupGameMode();
        } else {
            player.sendMessage("Configuration is not completed, they miss : ");
            /**
             * List of dependance configuration value
             */
        }
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        playerContainer.sendMessage("Game is not configurate! (" + ChatColor.AQUA + "/config" + ChatColor.WHITE + ")");
    }
}
