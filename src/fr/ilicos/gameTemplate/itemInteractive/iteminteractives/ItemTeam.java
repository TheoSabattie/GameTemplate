package fr.ilicos.gameTemplate.itemInteractive.iteminteractives;

import fr.ilicos.gameTemplate.itemInteractive.ItemInteractive;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class ItemTeam extends ItemInteractive {
    private final Team team;

    public ItemTeam(ItemStack itemStack, Team team){
        super(team.getID() + "-" + team.getTeamColor() + team.getTeamColor().name(), itemStack);
        this.team = team;
    }

    @Override
    public void onInteract(Player player) {
        team.addPlayer(player);
    }

    @Override
    protected void setupLores(List<String> lores) {

    }
}
