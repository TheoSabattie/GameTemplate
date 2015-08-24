package fr.ilicos.gameTemplate.villager.villagers;

import fr.ilicos.gameTemplate.villager.CustomVillager;
import fr.ilicos.gameTemplate.villager.Offer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 20/08/2015.
 */
public class Architecte extends CustomVillager {
    public Architecte(Location location) {
        super(location);
        setName("Architecte");

        List<Offer> offers = new ArrayList<>();

        offers.add(new Offer(
                new ItemStack(Material.BRICK, 2),
                new ItemStack(Material.OBSIDIAN, 6),
                0, 9999));

        ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE, 1);
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);

        offers.add(new Offer(
                new ItemStack(Material.IRON_INGOT, 6),
                pickaxe,
                0, 9999));

        setOffers(offers);
    }
}
