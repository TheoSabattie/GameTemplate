package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scheduler.ChronoStarterScheduler;
import org.bukkit.Bukkit;

/**
 * Created by ilicos, Th�o S. on 09/08/2015.
 */
public class WaitingChronoGame extends AbstractGame{
    private final ChronoStarterScheduler chronoStarter = new ChronoStarterScheduler(new ChronoStarterScheduler.Delegate() {
        @Override
        public void onChronoFinished() {
            gameMode.setupGame();
        }
    }, MainManager.getInstance().getConfig().getDelayBeforeStart());

    public WaitingChronoGame(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {}

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {
        if (!gameMode.readyToBeginGame()){
            chronoStarter.stop();
            Bukkit.broadcastMessage("Delay stopped, there isn't enought players");
            gameMode.setupWaitingPlayersGame();
        }
    }

    @Override
    public void destroy() {

    }
}