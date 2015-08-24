package fr.ilicos.gameTemplate.itemGenerator;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ilicos, Théo S. on 23/08/2015.
 */
public abstract class AbstractGenerator {
    public abstract void generate();

    protected void generateItemToLocation(ItemStack item, Location spawn){
        spawn.getWorld().dropItemNaturally(spawn, item);
    }
}
