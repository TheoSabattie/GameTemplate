package fr.ilicos.gameTemplate.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

/**
 * Created by ilicos, Théo S. on 18/08/2015.
 */
public enum TeamColor {
    BLUE(ChatColor.BLUE, (new Wool(DyeColor.BLUE)).toItemStack(1)),
    RED(ChatColor.RED, (new Wool(DyeColor.RED)).toItemStack(1)),
    GREEN(ChatColor.GREEN, (new Wool(DyeColor.GREEN)).toItemStack(1)),
    YELLOW(ChatColor.YELLOW, (new Wool(DyeColor.YELLOW)).toItemStack(1));

    private ChatColor chatColor;
    private ItemStack itemSelection;

    public ChatColor getChatColor(){
        return chatColor;
    }

    public ItemStack getItemSelection(){return itemSelection;}

    TeamColor(ChatColor chatColor, ItemStack itemSelection){
        this.chatColor = chatColor;
        this.itemSelection = itemSelection;
    }

    public Team createTeam(int ID){
        return new Team(this, ID);
    }


    @Override
    public String toString() {
        return chatColor.toString();
    }
}
