package fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives;

import fr.ilicos.gameTemplate.menu.inventory.inventories.InventoryMenuTeamSelection;
import fr.ilicos.gameTemplate.menu.iteminteractive.ItemInteractive;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public class ItemTeamSelection extends ItemInteractive {
    public ItemTeamSelection(Material material){
        super("Team selection", material, 1);
    }

    @Override
    public void onInteract(Player player) {
        new InventoryMenuTeamSelection(player);
    }

    @Override
    protected void setupLores(List<String> lores) {
        lores.add("Choisissez votre team!");
    }
}