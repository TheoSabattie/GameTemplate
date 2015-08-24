package fr.ilicos.gameTemplate.scoreboard;

import fr.ilicos.gameTemplate.scoreboard.tab.AbstractTab;
import fr.ilicos.gameTemplate.utils.Destroyable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by ilicos, Théo S. on 05/07/2015.
 */
public class ScoreboardManager extends Destroyable{
    private static ScoreboardManager instance;

    public static ScoreboardManager getInstance(){
        if(instance == null){
            instance = new ScoreboardManager();
        }

        return instance;
    }

    private final Scoreboard scoreboard;
    private AbstractTab currentTab;

    private ScoreboardManager(){
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        for (Player player : Bukkit.getOnlinePlayers()){
            setScoreboardPlayer(player);
        }
    }

    public void setTab(AbstractTab tab){
        currentTab = tab;
        currentTab.init(scoreboard);
    }

    public void setScoreboardPlayer(Player player){
    	player.setScoreboard(scoreboard);
    }
    
    public Team createTeamScoreboard(fr.ilicos.gameTemplate.team.Team team){
        String prefix       = getTeamPrefix(team);
    	Team teamScoreBoard = scoreboard.registerNewTeam(prefix + team.getTeamColor().name());
    	teamScoreBoard.setPrefix(prefix);
    	return teamScoreBoard;
    }

    private String getTeamPrefix(fr.ilicos.gameTemplate.team.Team team){
        return team.getID() + "-" + team.getTeamColor();
    }

    public Team getTeam(fr.ilicos.gameTemplate.team.Team team){
        return scoreboard.getTeam(getTeamPrefix(team) + team.getTeamColor().name());
    }
    
    public void removeAllTeams(){
        for (Team team : scoreboard.getTeams()){
            team.unregister();
        }
    }

    private void destroyCurrentTab(){
        if (currentTab != null){
            currentTab.destroy();
        }
    }

    public void destroy(){
        destroyCurrentTab();
        removeAllTeams();
        instance = null;
    }
}
