package fr.ilicos.gameTemplate.listener;

import fr.ilicos.gameTemplate.ConnectionDisconnection;
import fr.ilicos.gameTemplate.PlayerContainer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 */
public abstract class ConnectionDisconnectionListener implements Listener, ConnectionDisconnection {
    @EventHandler
    public void onPlayerConnection(PlayerJoinEvent event){
        PlayerContainer playerContainer = new PlayerContainer(event.getPlayer());
        onPlayerConnection(playerContainer);
    }

    @EventHandler
    public void onPlayerDisconnection(PlayerQuitEvent event){
        PlayerContainer playerContainer = PlayerContainer.getPlayerContainerFromPlayer(event.getPlayer());
        onPlayerDisconnection(playerContainer);
        playerContainer.destroy();
    }
}
