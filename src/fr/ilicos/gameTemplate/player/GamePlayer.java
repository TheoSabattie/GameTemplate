package fr.ilicos.gameTemplate.player;

import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class GamePlayer extends WaitingTeamedPlayer{
    private static final List<GamePlayer> list = new ArrayList<>();

    public static List<GamePlayer> getGamePlayers(){
        return new ArrayList<>(list);
    }

    public GamePlayer(Player player, Team team) {
        super(player, team);
        ScoreboardManager.getInstance().getTeam(team).addEntry(player.getName());
        team.addPlayer(player);
        Location spawn = team.getSpawn();
        player.setBedSpawnLocation(spawn);
        player.teleport(spawn);
    }

    @Override
    public void destroy() {
        list.remove(this);
        super.destroy();
    }
}
