package fr.ilicos.gameTemplate.menu.iteminteractive;

import fr.ilicos.gameTemplate.utils.Destroyable;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class ItemInteractiveRegister extends Destroyable{
    private static ItemInteractiveRegister instance;
    public static ItemInteractiveRegister getInstance(){
        if (instance == null){
            instance = new ItemInteractiveRegister();
        }

        return instance;
    }

    private ItemInteractiveRegister(){}

    private final Set<ItemInteractive> list = new HashSet<>();

    void addItemStackInteractive(ItemInteractive item){
        list.add(item);
    }

    public ItemInteractive getItemInteractiveFromItem(ItemStack itemStack){
        for (ItemInteractive item : list){
            if (item.equals(itemStack)){
                return item;
            }
        }

        return null;
    }

    @Override
    public void destroy() {
        instance = null;
    }
}
