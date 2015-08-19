package fr.ilicos.gameTemplate.menu.iteminteractive;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public abstract class ItemInteractive extends ItemStack {
    protected ItemInteractive(String displayName, Material material, int count){
        super(material, count);
        setData(displayName);
    }

    protected ItemInteractive(String displayName, ItemStack itemStack){
        super(itemStack);
        setData(displayName);
    }

    private void setData(String displayName){
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(displayName);

        List<String> lores = new ArrayList<>();
        setupLores(lores);

        if (lores.size() != 0){
            meta.setLore(lores);
        }

        this.setItemMeta(meta);

        ItemInteractiveRegister.getInstance().addItemStackInteractive(this);
    }

    public abstract void onInteract(Player player);



    protected abstract void setupLores(List<String> lores);
}
