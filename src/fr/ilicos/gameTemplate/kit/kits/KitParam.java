package fr.ilicos.gameTemplate.kit.kits;

import fr.ilicos.gameTemplate.kit.Kit;
import fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives.ItemKitSelection;
import fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives.ItemTeamSelection;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class KitParam extends Kit {
    public KitParam() {
        super("");
    }

    @Override
    protected void setupKitContent(List<ItemStack> kitContent) {
        kitContent.add(new ItemTeamSelection(Material.BANNER));
        kitContent.add(new ItemKitSelection(Material.WOOD_HOE));
    }

    @Override
    protected void setupKitArmorContent(List<ItemStack> kitArmorContent) {

    }
}
