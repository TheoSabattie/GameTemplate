package fr.ilicos.gameTemplate.menu.inventories;

import fr.ilicos.gameTemplate.menu.InventoryMenu;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 22/08/2015.
 */
public abstract class InventoryMenuKitSelection extends InventoryMenu {
    protected InventoryMenuKitSelection(Player player) {
        super(player);
    }

    @Override
    protected void setupName() {
        name = "Kits selection";
    }
}
