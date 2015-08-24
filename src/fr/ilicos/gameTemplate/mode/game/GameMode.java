package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.commandExecutor.GameCommandExecutor;
import fr.ilicos.gameTemplate.listener.GameListener;
import fr.ilicos.gameTemplate.mode.AbtractMode;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class GameMode extends AbtractMode {
    private AbstractGame currentGame = new WaitingPlayersGame(this);
    private final GameListener listener = new GameListener();

    public GameMode(){
        super();

        Location location = (Location)Config.WAITING_ROOM_SPAWN.getValue();

        for (Player player : Bukkit.getOnlinePlayers()){
            player.teleport(location);
        }

        for (Team team : Team.getTeams()){
            ScoreboardManager.getInstance().createTeamScoreboard(team);
        }

        MainManager.getInstance().addListener(listener);
    }

    @Override
    protected void setupCommandExecutor() {
        commandExecutor = new GameCommandExecutor();
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        playerContainer.transformToWaitingPlayer();
        currentGame.onPlayerConnection(playerContainer);
        playerContainer.getPlayer().teleport(currentGame.getSpawn());
    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {
        currentGame.onPlayerDisconnection(playerContainer);
    }

    @Override
    public void destroy() {
        MainManager.getInstance().removeListener(listener);
    }

    public void setupWaitingChronoGame() {
        destroyCurrentGame();
        currentGame = new WaitingChronoGame(this);
    }

    public void setupWaitingPlayersGame(){
        destroyCurrentGame();
        currentGame = new WaitingPlayersGame(this);
    }

    public void setupGame(){
        destroyCurrentGame();
        currentGame = new Game(this);
    }

    private void destroyCurrentGame() {
        if (currentGame != null) {
            currentGame.destroy();
        }
    }

    public boolean readyToBeginGame() {
        /**
         * If you need some conditions to start game, it's here!
         */
        return PlayerContainer.getPlayersContainers().size() >= (int) Config.MIN_PLAYER.getValue();
    }
}
