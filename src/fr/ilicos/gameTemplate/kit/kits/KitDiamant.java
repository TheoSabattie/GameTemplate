package fr.ilicos.gameTemplate.kit.kits;

import fr.ilicos.gameTemplate.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public class KitDiamant extends Kit {
    public KitDiamant() {
        super("Diamant");
    }

    @Override
    protected void setupKitContent(List<ItemStack> kitContent) {
        kitContent.add(new ItemStack(Material.DIAMOND, 1));
    }

    @Override
    protected void setupKitArmorContent(List<ItemStack> kitArmorContent) {

    }
}
