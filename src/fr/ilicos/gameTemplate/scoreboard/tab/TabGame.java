package fr.ilicos.gameTemplate.scoreboard.tab;

import fr.ilicos.gameTemplate.scoreboard.tab.description.DescriptionTeam;
import fr.ilicos.gameTemplate.team.Team;

/**
 * Created by ilicos, Théo S. on 06/07/2015.
 */
public class TabGame extends AbstractTab{
    private static TabGame instance;

    public static TabGame getInstance(){
        if (instance == null){
            instance = new TabGame();
        }

        return instance;
    }

    public TabGame() {
        super("MySupergame");

        for (Team team : Team.getTeams()){
            addDescription(String.valueOf(team.getID()), new DescriptionTeam(team));
        }
    }



    @Override
    public void destroy() {
        super.destroy();
        instance = null;
    }
}
