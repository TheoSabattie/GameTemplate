package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.commandExecutor.GameCommandExecutor;
import fr.ilicos.gameTemplate.mode.AbtractMode;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class GameMode extends AbtractMode {
    private AbstractGame currentGame = new WaitingPlayersGame(this);

    public GameMode(){
        super();
    }

    @Override
    protected void setupCommandExecutor() {
        commandExecutor = new GameCommandExecutor();
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        currentGame.onPlayerConnection(playerContainer);
    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {
        currentGame.onPlayerDisconnection(playerContainer);
    }

    @Override
    public void destroy() {

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
        if (currentGame != null){
            currentGame.destroy();
        }
    }

    public boolean readyToBeginGame() {
        /**
         * If you need some conditions to start game, it's here!
         */
        return PlayerContainer.getPlayerContainers().size() >= MainManager.getInstance().getConfig().getMinPlayers();
    }
}
