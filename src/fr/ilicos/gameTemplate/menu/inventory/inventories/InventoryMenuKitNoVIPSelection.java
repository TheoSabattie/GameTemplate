package fr.ilicos.gameTemplate.menu.inventory.inventories;

import fr.ilicos.gameTemplate.kit.Kits;
import fr.ilicos.gameTemplate.menu.inventory.InventoryMenu;
import fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives.ItemKit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class InventoryMenuKitNoVIPSelection extends InventoryMenu {

    public InventoryMenuKitNoVIPSelection(Player player){
        super();
    }

    @Override
    protected void setupName() {
        name = "Kit selection";
    }

    @Override
    protected void setupCaseNumber() {
        caseNumber = 9;
    }

    @Override
    protected void setupItemStacks(Inventory inventory) {
        List<ItemKit> itemsKits = new ArrayList<>();
        List<Kits> kitsNoVip = Kits.getNoVIPKits();

        for (Kits kit : kitsNoVip){
            itemsKits.add(kit.getItemKit());
        }

        ItemKit[] items = new ItemKit[kitsNoVip.size()];
        itemsKits.toArray(items);
        inventory.setContents(items);
    }
}
