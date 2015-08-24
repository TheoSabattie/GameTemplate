package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.kit.kits.KitParam;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.scoreboard.tab.TabWaitingPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public class WaitingPlayersGame extends AbstractGame {


    public WaitingPlayersGame(GameMode gameMode) {
        super(gameMode);

        ScoreboardManager.getInstance().setTab(TabWaitingPlayer.getInstance());

        KitParam kitParam = new KitParam();
        Player player;

        for (PlayerContainer playerContainer : PlayerContainer.getPlayersContainers()){
            player = playerContainer.getPlayer();
            player.getInventory().clear();
            kitParam.applyToPlayer(player);
            playerContainer.transformToWaitingPlayer();
        }

        Bukkit.getWorlds().get(0).setPVP(false);
        launchChronoIfReady();
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        launchChronoIfReady();
        KitParam kitParam = new KitParam();
        kitParam.applyToPlayer(playerContainer.getPlayer());
        TabWaitingPlayer.getInstance().onPlayerConnection();
    }

    private void launchChronoIfReady(){
        if (gameMode.readyToBeginGame()){
            gameMode.setupWaitingChronoGame();
        }
    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {
        TabWaitingPlayer.getInstance().onPlayerDisconnection();
    }

    @Override
    public void destroy() {
        super.destroy();
        TabWaitingPlayer.getInstance().destroy();
    }
}
