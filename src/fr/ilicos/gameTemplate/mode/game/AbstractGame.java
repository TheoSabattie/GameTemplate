package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.ConnectionDisconnection;
import fr.ilicos.gameTemplate.GameObject;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public abstract class AbstractGame extends GameObject implements ConnectionDisconnection{
    protected final GameMode gameMode;

    public AbstractGame(GameMode gameMode){
        this.gameMode = gameMode;
    }
}
