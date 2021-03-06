package fr.ilicos.gameTemplate.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public abstract class InventoryMenu{

    protected String name    = "";
    protected int caseNumber = 9;
    private final Inventory inventory;

    public InventoryMenu(){
        setupName();
        setupCaseNumber();
        int caseNumber = (int) Math.ceil((double)this.caseNumber / 9) * 9;
        inventory      = Bukkit.createInventory(null, caseNumber, name);

        setupItemStacks(inventory);
    }

    public InventoryMenu(Player player){
        this();
        player.openInventory(inventory);
    }

    protected abstract void setupName();
    protected abstract void setupCaseNumber();
    protected abstract void setupItemStacks(Inventory inventory);
}