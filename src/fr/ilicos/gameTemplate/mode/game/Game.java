package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.player.PlayerContainer;

/**
 * Created by ilicos, Théo S. on 09/08/2015.
 */
public class Game extends AbstractGame {
    public Game(GameMode gameMode) {
        super(gameMode);
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {

    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {

    }

    @Override
    public void destroy() {

    }
}
