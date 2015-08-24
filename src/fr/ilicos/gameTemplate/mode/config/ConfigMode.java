package fr.ilicos.gameTemplate.mode.config;

import fr.ilicos.gameTemplate.commandExecutor.ConfigCommandExecutor;
import fr.ilicos.gameTemplate.mode.AbtractMode;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.scoreboard.tab.TabConfig;
import org.bukkit.ChatColor;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class ConfigMode extends AbtractMode {
    public ConfigMode() {
        super();

        ScoreboardManager.getInstance().setTab(TabConfig.getInstance());
    }

    @Override
    protected void setupCommandExecutor() {
        commandExecutor = new ConfigCommandExecutor();
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        playerContainer.sendMessage("Game is not configurate! (" + ChatColor.AQUA + "/config" + ChatColor.WHITE + ")");
    }

    @Override
    public void destroy() {
        super.destroy();
        TabConfig.getInstance().destroy();
    }
}
