package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.MainManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class GameCommandExecutor extends AbstractCommandExecutor {
    @Override
    protected boolean onPlayerCommand(Player player, String[] args) {
        if (args.length == 0){
            Bukkit.broadcastMessage("Switching in Config Mode...");
            MainManager.getInstance().setupConfigMode();

            return true;
        }

        player.sendMessage("Bad Usage, to edit configuration : /config");

        return false;
    }
}
