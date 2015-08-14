package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scheduler.ChronoStarterScheduler;
import org.bukkit.Bukkit;

/**
 * Created by ilicos, Théo S. on 09/08/2015.
 */
public class WaitingChronoGame extends AbstractGame{
    private final ChronoStarterScheduler chronoStarter = new ChronoStarterScheduler(new ChronoStarterScheduler.Delegate() {
        @Override
        public void onChronoFinished() {
            gameMode.setupGame();
        }
    }, (int) Config.DELAY_BEFORE_START.getValue());

    public WaitingChronoGame(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {}

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {
        if (!gameMode.readyToBeginGame()){
            Bukkit.broadcastMessage("Delay stopped, there isn't enought players");
            gameMode.setupWaitingPlayersGame();
        }
    }

    @Override
    public void destroy() {
        chronoStarter.stop();
    }
}
