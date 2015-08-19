package fr.ilicos.gameTemplate.kit;

import fr.ilicos.gameTemplate.kit.kits.*;
import fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives.ItemKit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 17/08/2015.
 */
public enum Kits {
    DIAMANT(new KitDiamant(), new ItemStack(Material.DIAMOND));

    private final Kit kit;
    private final ItemKit itemKit;

    public Kit getKit(){
        return kit;
    }
    public ItemKit getItemKit(){
        return itemKit;
    }

    Kits(Kit kit, ItemStack itemStack){
        this.kit = kit;
        this.itemKit = new ItemKit(itemStack, kit);
    }
}
