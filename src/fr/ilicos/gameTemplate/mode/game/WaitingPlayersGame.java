package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.player.PlayerContainer;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public class WaitingPlayersGame extends AbstractGame {
    public WaitingPlayersGame(GameMode gameMode) {
        super(gameMode);
        launchChronoIfReady(); //prevention of reloading
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        launchChronoIfReady();
    }

    private void launchChronoIfReady(){
        if (gameMode.readyToBeginGame()){
            gameMode.setupWaitingChronoGame();
        }
    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {}
}
