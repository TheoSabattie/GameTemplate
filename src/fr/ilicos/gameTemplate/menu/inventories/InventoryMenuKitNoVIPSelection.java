package fr.ilicos.gameTemplate.menu.inventories;

import fr.ilicos.gameTemplate.itemInteractive.iteminteractives.ItemKit;
import fr.ilicos.gameTemplate.kit.Kits;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class InventoryMenuKitNoVIPSelection extends InventoryMenuKitSelection {

    public InventoryMenuKitNoVIPSelection(Player player){
        super(player);
    }

    @Override
    protected void setupCaseNumber() {
        caseNumber = Kits.getNoVIPKits().size();
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
