package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.kit.kits.KitParam;
import fr.ilicos.gameTemplate.listener.ItemStackInteractListener;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public class WaitingPlayersGame extends AbstractGame {
    private final ItemStackInteractListener listener = new ItemStackInteractListener();

    public WaitingPlayersGame(GameMode gameMode) {
        super(gameMode);

        MainManager.getInstance().addListener(listener);

        KitParam kitParam = new KitParam();

        for (Player player: Bukkit.getOnlinePlayers()){
            player.getInventory().clear();
            kitParam.applyToPlayer(player);
        }

        launchChronoIfReady(); //prevention of reloading
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        launchChronoIfReady();
        KitParam kitParam = new KitParam();
        kitParam.applyToPlayer(playerContainer.getPlayer());
    }

    private void launchChronoIfReady(){
        if (gameMode.readyToBeginGame()){
            gameMode.setupWaitingChronoGame();
        }
    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {}

    @Override
    public void destroy() {
        super.destroy();
        MainManager.getInstance().removeListener(listener);
    }
}
