package fr.ilicos.gameTemplate.kit;

import fr.ilicos.gameTemplate.kit.kits.*;
import fr.ilicos.gameTemplate.itemInteractive.iteminteractives.ItemKit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 17/08/2015.
 */
public enum Kits {
    DIAMANT(new KitDiamant(), new ItemStack(Material.DIAMOND), false);

    private final Kit kit;
    private final ItemKit itemKit;
    private final boolean isVIP;

    public Kit getKit(){
        return kit;
    }
    public ItemKit getItemKit(){
        return itemKit;
    }

    public static List<Kits> getVIPKits(){
        List<Kits> vipKits = new ArrayList<>();

        for (Kits kit : values()){
            if (kit.isVIP){
                vipKits.add(kit);
            }
        }

        return vipKits;
    }

    public static List<Kits> getNoVIPKits(){
        List<Kits> noVipKits = new ArrayList<>();

        for (Kits kit : values()){
            if (!kit.isVIP){
                noVipKits.add(kit);
            }
        }

        return noVipKits;
    }

    Kits(Kit kit, ItemStack itemStack, boolean isVIP){
        this.kit = kit;
        this.itemKit = new ItemKit(itemStack, kit);
        this.isVIP = isVIP;
    }
}
