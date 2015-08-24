package fr.ilicos.gameTemplate.itemInteractive.iteminteractives;

import fr.ethyngames.api.players.EthynGamesPlayer;
import fr.ethyngames.api.players.PlayerInfos;
import fr.ilicos.gameTemplate.itemInteractive.ItemInteractive;
import fr.ilicos.gameTemplate.menu.inventories.InventoryMenuKitNoVIPSelection;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ilicos, Théo S. on 16/08/2015.
 */
public class ItemKitSelection extends ItemInteractive {
    public ItemKitSelection(Material material) {
        super("Kit Selection", material, 1);
    }

    @Override
    public void onInteract(Player player) {
        EthynGamesPlayer ethynPlayer = PlayerInfos.get(player);

        /*if (ethynPlayer.isVip()){
            new InventoryMenuKitVIPSelection(player);
        } else {*/
            new InventoryMenuKitNoVIPSelection(player);
        //}

    }

    @Override
    protected void setupLores(List<String> lores) {
        lores.add("Choisissez votre kit!");
    }
}
