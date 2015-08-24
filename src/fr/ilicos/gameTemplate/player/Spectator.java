package fr.ilicos.gameTemplate.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class Spectator extends PlayerContainer {
    public Spectator(Player player) {
        super(player);
        player.setGameMode(GameMode.SPECTATOR);
    }
}
