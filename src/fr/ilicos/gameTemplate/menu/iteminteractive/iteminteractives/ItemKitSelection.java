package fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives;

import fr.ilicos.gameTemplate.menu.inventory.inventories.InventoryMenuKitNoVIPSelection;
import fr.ilicos.gameTemplate.menu.iteminteractive.ItemInteractive;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class ItemKitSelection extends ItemInteractive {
    public ItemKitSelection(Material material) {
        super("Kit Selection", material, 1);
    }

    @Override
    public void onInteract(Player player) {
        new InventoryMenuKitNoVIPSelection(player);
        //TODO: VIP or not VIP
    }

    @Override
    protected void setupLores(List<String> lores) {
        lores.add("Choisissez votre kit!");
    }
}
