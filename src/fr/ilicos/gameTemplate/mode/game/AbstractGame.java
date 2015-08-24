package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.listener.ConnectionDisconnection;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.utils.Destroyable;
import org.bukkit.Location;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public abstract class AbstractGame extends Destroyable implements ConnectionDisconnection{
    protected final GameMode gameMode;
    protected Location spawn = (Location)Config.WAITING_ROOM_SPAWN.getValue();

    public AbstractGame(GameMode gameMode){
        this.gameMode = gameMode;
    }

    public Location getSpawn() {
        return spawn;
    }
}
