package fr.ilicos.gameTemplate.listener;

import fr.ilicos.gameTemplate.player.PlayerContainer;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public interface ConnectionDisconnection {
    void onPlayerConnection(PlayerContainer playerContainer);
    void onPlayerDisconnection(PlayerContainer playerContainer);
}
