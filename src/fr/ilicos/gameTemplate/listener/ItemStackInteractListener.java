package fr.ilicos.gameTemplate.listener;

import fr.ilicos.gameTemplate.itemInteractive.ItemInteractive;
import fr.ilicos.gameTemplate.itemInteractive.ItemInteractiveRegister;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public class ItemStackInteractListener implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        HumanEntity humanEntity = event.getWhoClicked();

        if (humanEntity instanceof Player){
            Player player        = (Player) humanEntity;
            ItemStack itemStack  = event.getCurrentItem();
            ItemInteractive item = ItemInteractiveRegister.getInstance().getItemInteractiveFromItem(itemStack);

            if (item != null){
                event.setCancelled(true);
                item.onInteract(player);
            } else {
                player.sendMessage("pas repéré dans la map");
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEvent (PlayerInteractEvent event){
        Player player       = event.getPlayer();
        ItemStack itemStack = player.getItemInHand();

        if (itemStack != null){
            ItemInteractive item = ItemInteractiveRegister.getInstance().getItemInteractiveFromItem(itemStack);
            Action action        = event.getAction();


            if (item != null && (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)){
                event.setCancelled(true);
                item.onInteract(player);
            }
        }
    }

    @EventHandler
    public void onInventoryDragEvent (InventoryDragEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItemEvent (PlayerDropItemEvent event){
        event.setCancelled(true);
    }
}
