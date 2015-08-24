package fr.ilicos.gameTemplate.player;

import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.team.Team;
import fr.ilicos.gameTemplate.team.TeamColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class WaitingTeamedPlayer extends PlayerContainer {
    protected final Team team;
    private static final List<WaitingTeamedPlayer> list = new ArrayList<>();

    public static List<WaitingTeamedPlayer> getWaitingTeamedPlayers(){
        return new ArrayList<>(list);
    }

    public WaitingTeamedPlayer(Player player, Team team) {
        super(player);
        this.team = team;
        list.add(this);
        ScoreboardManager.getInstance().getTeam(team).addEntry(player.getName());
    }

    @Override
    public void destroy() {
        list.remove(this);
        ScoreboardManager.getInstance().getTeam(team).removeEntry(player.getName());
        team.removePlayer(this);
        super.destroy();
    }

    public GamePlayer transformToGamePlayer(){
        return new GamePlayer(destroyAndGivePlayer(), team);
    }

    @Override
    public String getChatFormat() {
        TeamColor teamColor = team.getTeamColor();
        return team.getID()+"-"+teamColor+teamColor.name() + " " + super.getChatFormat();
    }
}
