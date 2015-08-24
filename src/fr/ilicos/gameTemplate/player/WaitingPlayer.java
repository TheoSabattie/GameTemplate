package fr.ilicos.gameTemplate.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class WaitingPlayer extends PlayerContainer {
    private static final List<WaitingPlayer> list = new ArrayList<>();

    public static List<WaitingPlayer> getWaitingPlayers(){
        return new ArrayList<>(list);
    }

    public WaitingPlayer(Player player) {
        super(player);
        list.add(this);
        player.setGameMode(GameMode.ADVENTURE);
    }

    @Override
    public void destroy() {
        list.remove(this);
        super.destroy();
    }
}
