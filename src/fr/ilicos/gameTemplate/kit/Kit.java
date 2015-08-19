package fr.ilicos.gameTemplate.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public abstract class Kit {
    private final List<ItemStack> kitContent = new ArrayList<>();
    private final List<ItemStack> kitArmorContent = new ArrayList<>();
    private final String name;


    protected Kit(String name){
        this.name = name;
        setupKitContent(kitContent);
        setupKitArmorContent(kitArmorContent);
    }

    public void applyToPlayer(Player player) {
        PlayerInventory playerInventory = player.getInventory();

        ItemStack[] kitContent = new ItemStack[this.kitContent.size()];
        ItemStack[] kitArmorContent = new ItemStack[this.kitArmorContent.size()];
        this.kitContent.toArray(kitContent);
        this.kitArmorContent.toArray(kitArmorContent);

        playerInventory.setContents(kitContent);
        playerInventory.setArmorContents(kitArmorContent);
    }

    protected abstract void setupKitContent(List<ItemStack> kitContent);
    protected abstract void setupKitArmorContent(List<ItemStack> kitArmorContent);

    public String getName() {
        return name;
    }
}
