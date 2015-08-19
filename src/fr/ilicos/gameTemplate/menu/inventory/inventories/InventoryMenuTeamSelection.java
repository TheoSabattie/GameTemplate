package fr.ilicos.gameTemplate.menu.inventory.inventories;

import fr.ilicos.gameTemplate.menu.inventory.InventoryMenu;
import fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives.ItemTeam;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public class InventoryMenuTeamSelection extends InventoryMenu {
    public InventoryMenuTeamSelection(Player player) {
        super(player);
    }

    @Override
    protected void setupName() {
        name = "Team selection";
    }

    @Override
    protected void setupCaseNumber() {
        caseNumber = 9;
    }

    @Override
    protected void setupItemStacks(Inventory inventory) {
        List<ItemTeam> itemTeams = new ArrayList<>();

        for (Team team : (List<Team>) Config.TEAMS.getValue()){
            itemTeams.add(team.getItemTeam());
        }

        ItemTeam[] items = new ItemTeam[itemTeams.size()];
        itemTeams.toArray(items);
        inventory.setContents(items);
    }
}
