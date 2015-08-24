package fr.ilicos.gameTemplate.scoreboard.tab.description;

import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.ChatColor;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class DescriptionTeam extends DescriptionOneColor {
    private static enum StateTeam {
        HAVE_BED(ChatColor.GREEN),
        BED_LOSE(ChatColor.GOLD),
        DIE(ChatColor.RED);

        private final ChatColor chatColor;

        StateTeam(ChatColor chatColor){
            this.chatColor = chatColor;
        }

        @Override
        public String toString() {
            return chatColor + name().toLowerCase();
        }
    }

    private StateTeam stateTeam = StateTeam.HAVE_BED;

    public DescriptionTeam(Team team) {
        super(team.getTeamColor().getChatColor(), team.getID() + "-" + team.getTeamColor().name());
    }

    @Override
    public String toString() {
        return super.toString() + " " + stateTeam;
    }
}
