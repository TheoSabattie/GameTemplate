package fr.ilicos.gameTemplate.mode.config;

import fr.ilicos.gameTemplate.commandExecutor.ConfigCommandExecutor;
import fr.ilicos.gameTemplate.mode.AbtractMode;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import org.bukkit.ChatColor;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class ConfigMode extends AbtractMode {
    public ConfigMode(Config config) {
        super();
        ((ConfigCommandExecutor)commandExecutor).setConfig(config);
    }

    @Override
    protected void setupCommandExecutor() {
        commandExecutor = new ConfigCommandExecutor();
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        playerContainer.sendMessage("Game is not configurate! (" + ChatColor.AQUA + "/config" + ChatColor.WHITE + ")");
    }
}
