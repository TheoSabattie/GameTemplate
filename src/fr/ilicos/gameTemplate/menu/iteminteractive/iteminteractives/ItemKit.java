package fr.ilicos.gameTemplate.menu.iteminteractive.iteminteractives;

import fr.ilicos.gameTemplate.kit.Kit;
import fr.ilicos.gameTemplate.kit.KitRegister;
import fr.ilicos.gameTemplate.menu.iteminteractive.ItemInteractive;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 18/08/2015.
 */
public class ItemKit extends ItemInteractive {
    private final Kit kit;

    public ItemKit(ItemStack itemStack, Kit kit) {
        super(kit.getName(), itemStack);
        this.kit = kit;
    }

    @Override
    public void onInteract(Player player) {
        KitRegister.getInstance().regist(player, kit);
        player.sendMessage("Vous avez sélectionné le kit " + kit.getName());
    }

    @Override
    protected void setupLores(List<String> lores) {

    }
}
