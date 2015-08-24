package fr.ilicos.gameTemplate.scoreboard.tab.description;

import org.bukkit.ChatColor;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class DescriptionPlayerConnected extends DescriptionOneColor{
    private final int playersNeeded;

    public DescriptionPlayerConnected(int playersNumber, int playersNeeded) {
        super(String.valueOf(playersNumber));
        this.playersNeeded = playersNeeded;
        setupColor(playersNumber);
    }

    public void setPlayersNumber(int playersNumber){
        setText(String.valueOf(playersNumber));
        setupColor(playersNumber);
    }

    private void setupColor(int playersNumber){
        int ratio = playersNumber/playersNeeded;

        if (ratio < 0.5){
            setChatColor(ChatColor.RED);
        } else {
            setChatColor(ChatColor.GOLD);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "/" + playersNeeded;
    }
}
