package fr.ilicos.gameTemplate.menu.inventories;

import fr.ilicos.gameTemplate.itemInteractive.iteminteractives.ItemKit;
import fr.ilicos.gameTemplate.kit.Kits;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 22/08/2015.
 */
public class InventoryMenuKitVIPSelection extends InventoryMenuKitSelection {
    public InventoryMenuKitVIPSelection(Player player) {
        super(player);
    }

    @Override
    protected void setupCaseNumber() {
        caseNumber = Kits.getVIPKits().size();
    }

    @Override
    protected void setupItemStacks(Inventory inventory) {
        List<ItemKit> itemsKits = new ArrayList<>();
        List<Kits> kitsVip = Kits.getVIPKits();

        for (Kits kit : kitsVip){
            itemsKits.add(kit.getItemKit());
        }

        ItemKit[] items = new ItemKit[kitsVip.size()];
        itemsKits.toArray(items);
        inventory.setContents(items);
    }
}
