package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.listener.ConnectionDisconnection;
import fr.ilicos.gameTemplate.utils.Destroyable;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public abstract class AbstractGame extends Destroyable implements ConnectionDisconnection{
    protected final GameMode gameMode;

    public AbstractGame(GameMode gameMode){
        this.gameMode = gameMode;
    }
}
