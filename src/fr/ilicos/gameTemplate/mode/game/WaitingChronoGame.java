package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scheduler.ChronoStarterScheduler;
import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.scoreboard.tab.TabWaitingChrono;
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
    }, (int) Config.DELAY_BEFORE_START.getValue(), TabWaitingChrono.getInstance());

    public WaitingChronoGame(GameMode gameMode) {
        super(gameMode);
        ScoreboardManager.getInstance().setTab(TabWaitingChrono.getInstance());
        chronoStarter.start();
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
        TabWaitingChrono.getInstance().destroy();
    }
}
