package fr.ilicos.gameTemplate.listener;

import fr.ilicos.gameTemplate.player.PlayerContainer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class GameListener extends ItemStackInteractListener {
    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent event){
        String eventMessage = event.getMessage();
        Player player = event.getPlayer();
        PlayerContainer playerContainer =  PlayerContainer.getPlayerContainerFromPlayer(player);
        event.setFormat(playerContainer.getChatFormat());
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event){
        if (event.getBlock().getType() != Material.OBSIDIAN){
            event.setCancelled(true);
        }
    }
}
