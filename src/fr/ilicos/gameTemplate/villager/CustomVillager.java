package fr.ilicos.gameTemplate.villager;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.MerchantRecipe;
import net.minecraft.server.v1_8_R3.MerchantRecipeList;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 20/08/2015.
 */
public class CustomVillager {
    private final EntityVillager nmsVillager;

    public CustomVillager(Location location) {
        World nmsWorld = ((CraftWorld) location.getWorld()).getHandle();
        nmsVillager    = new EntityVillager(nmsWorld);

        nmsVillager.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        nmsWorld.addEntity(nmsVillager);
    }

    public void setName(String name){
        nmsVillager.setCustomName(name);
        nmsVillager.setCustomNameVisible(true);
    }

    private MerchantRecipeList getMercantRecipeList(){
        return nmsVillager.getOffers(null);
    }

    public void setOffers(List<Offer> offers){
        MerchantRecipeList list = getMercantRecipeList();
        list.clear();

        for (Offer offer : offers){
            addOffer(offer, list);
        }
    }

    public void addOffer(Offer offer){
        addOffer(offer, getMercantRecipeList());
    }

    public void addOffer(Offer offer, MerchantRecipeList list) {
        list.add(new MerchantRecipe(
                CraftItemStack.asNMSCopy(offer.getBuyItemStack1()),
                CraftItemStack.asNMSCopy(offer.getBuyItemStack2()),
                CraftItemStack.asNMSCopy(offer.getSellItemStack()),
                offer.getUsageNeeded(),
                offer.getMaxUsage()));
    }

    public List<Offer> getOffers(){
        MerchantRecipeList list = getMercantRecipeList();
        List<Offer> offers = new ArrayList<>();

        for (MerchantRecipe merchantRecipe : list){
            offers.add(new Offer(
                    CraftItemStack.asBukkitCopy(merchantRecipe.getBuyItem1()),
                    CraftItemStack.asBukkitCopy(merchantRecipe.getBuyItem2()),
                    CraftItemStack.asBukkitCopy(merchantRecipe.getBuyItem3()),
                    merchantRecipe.e(),
                    merchantRecipe.f())
            );
        }

        return offers;
    }

    public boolean removeOffer(Offer offerToRemove){
        List<Offer> offers = getOffers();

        for(Offer offer : offers){
            if (offer.equals(offerToRemove)){
                offers.remove(offer);
                setOffers(offers);
                return true;
            }

        }

        return false;
    }
}
