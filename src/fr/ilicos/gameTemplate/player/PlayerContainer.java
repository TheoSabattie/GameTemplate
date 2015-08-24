package fr.ilicos.gameTemplate.player;

import fr.ilicos.gameTemplate.team.Team;
import fr.ilicos.gameTemplate.utils.Destroyable;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 08/08/2015.
 * When Player connects, PlayerContainer is create to. When player disconnects, PlayerContainer is destroy.
 */
public class PlayerContainer extends Destroyable {
    protected final Player player;
    private final static Map<Player, PlayerContainer> list = new HashMap<>();

    public void sendMessage(String message){
        player.sendMessage(message);
    }

    public static PlayerContainer getPlayerContainerFromPlayer(Player player){
        return list.get(player);
    }

    public static List<PlayerContainer> getPlayersContainers(){
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

    public Player getPlayer() {
        return player;
    }

    public Spectator transformToSpectator(){
        return new Spectator(destroyAndGivePlayer());
    }

    public WaitingPlayer transformToWaitingPlayer(){
        return new WaitingPlayer(destroyAndGivePlayer());
    }

    public PlayerContainer transformToPlayerContainer(){
        return new PlayerContainer(destroyAndGivePlayer());
    }

    public WaitingTeamedPlayer transformToWaitingTeamedPlayer(Team team){
        return new WaitingTeamedPlayer(destroyAndGivePlayer(), team);
    }

    protected Player destroyAndGivePlayer(){
        Player player = this.player;
        destroy();
        return player;
    }

    public String getChatFormat() {
        return "%1$s→" + ChatColor.GRAY + "%2$s";
    }
}
