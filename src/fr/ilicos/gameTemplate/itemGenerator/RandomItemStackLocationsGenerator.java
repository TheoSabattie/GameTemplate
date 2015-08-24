package fr.ilicos.gameTemplate.itemGenerator;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by ilicos, Théo S. on 23/08/2015.
 */
public abstract class RandomItemStackLocationsGenerator extends AbstractGenerator {
    private Map<Location, List<ItemStack>> spawners = new HashMap<>();
    private List<ItemStack> items;

    public RandomItemStackLocationsGenerator(List<ItemStack> items, List<Location> spawners){
        setItems(items);
        setSpawners(spawners);
    }

    protected RandomItemStackLocationsGenerator(){}

    protected void setSpawners(List<Location> spawners){
        this.spawners = new HashMap<>();

        for (Location spawner : spawners){
            this.spawners.put(spawner, new ArrayList<ItemStack>());
        }
    }

    protected void setItems(List<ItemStack> items){
        this.items = items;
    }

    @Override
    public void generate() {
        List<ItemStack> items;
        ItemStack item;

        for (Location spawner : spawners.keySet()){
            items = spawners.get(spawner);

            if (items.isEmpty()){
                items = new ArrayList<>(this.items);
                Collections.shuffle(items);
                spawners.put(spawner, items);
            }

            item = items.get(0);
            items.remove(item);
            generateItemToLocation(item, spawner);
        }
    }
}
