package fr.ilicos.gameTemplate.menu.inventories;

import fr.ilicos.gameTemplate.itemInteractive.iteminteractives.ItemTeam;
import fr.ilicos.gameTemplate.menu.InventoryMenu;
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
    protected void setupCaseNumber() {caseNumber = Team.getTeams().size();}

    @Override
    protected void setupItemStacks(Inventory inventory) {
        List<ItemTeam> itemTeams = new ArrayList<>();

        for (Team team : Team.getTeams()){
            itemTeams.add(team.getItemTeam());
        }

        ItemTeam[] items = new ItemTeam[itemTeams.size()];
        itemTeams.toArray(items);
        inventory.setContents(items);
    }
}
