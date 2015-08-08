package fr.ilicos.gameTemplate;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 * When Player connects, PlayerContainer is create to. When player disconnects, PlayerContainer is destroy.
 */
public class PlayerContainer extends GameObject{
    private final Player player;

    private final static Map<Player, PlayerContainer> list = new HashMap<>();

    public void sendMessage(String message){
        player.sendMessage(message);
    }

    public static PlayerContainer getPlayerContainerFromPlayer(Player player){
        return list.get(player);
    }

    public static List<PlayerContainer> getPlayerContainers(){
        return new ArrayList<>(list.values());
    }

    public PlayerContainer(Player player){
        this.player = player;
        list.put(player, this);
    }

    @Override
    public void destroy() {
        list.remove(player);
    }
}
