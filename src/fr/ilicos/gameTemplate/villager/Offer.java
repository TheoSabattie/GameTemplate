package fr.ilicos.gameTemplate.villager;

import org.bukkit.inventory.ItemStack;

/**
 * Created by ilicos, Théo S. on 20/08/2015.
 */
public class Offer {
    private final int usageNeeded;
    private final int maxUsage;
    private final ItemStack sellItemStack;
    private final ItemStack buyItemStack2;
    private final ItemStack buyItemStack1;

    public Offer(ItemStack buyItemStack1, ItemStack buyItemStack2, ItemStack sellItemStack, int usageNeeded, int maxUsage){
        this.buyItemStack1 = buyItemStack1;
        this.buyItemStack2 = buyItemStack2;
        this.sellItemStack = sellItemStack;
        this.usageNeeded   = usageNeeded;
        this.maxUsage      = maxUsage;
    }

    public Offer(ItemStack buyItemStack1, ItemStack sellItemStack, int usageNeeded, int maxUsage){
        this(buyItemStack1, null, sellItemStack, usageNeeded, maxUsage);
    }

    public ItemStack getBuyItemStack1() {
        return buyItemStack1;
    }

    public ItemStack getBuyItemStack2() {
        return buyItemStack2;
    }

    public ItemStack getSellItemStack() {
        return sellItemStack;
    }

    public int getMaxUsage() {
        return maxUsage;
    }

    public int getUsageNeeded() {
        return usageNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (usageNeeded != offer.usageNeeded) return false;
        if (maxUsage != offer.maxUsage) return false;
        if (!sellItemStack.equals(offer.sellItemStack)) return false;
        if (buyItemStack2 != null ? !buyItemStack2.equals(offer.buyItemStack2) : offer.buyItemStack2 != null)
            return false;
        return buyItemStack1.equals(offer.buyItemStack1);
    }

    @Override
    public int hashCode() {
        int result = usageNeeded;
        result = 31 * result + maxUsage;
        result = 31 * result + sellItemStack.hashCode();
        result = 31 * result + (buyItemStack2 != null ? buyItemStack2.hashCode() : 0);
        result = 31 * result + buyItemStack1.hashCode();
        return result;
    }
}
