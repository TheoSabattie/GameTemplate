package fr.ilicos.gameTemplate.scoreboard.tab.description;

import org.bukkit.ChatColor;

/**
 * Created by ilicos, Théo S. on 20/08/2015.
 */
public class DescriptionOneColor extends Description {
    private ChatColor chatColor;

    public DescriptionOneColor(ChatColor chatColor, String text, int number) {
        super(text, number);
        this.chatColor = chatColor;
    }

    public DescriptionOneColor(ChatColor chatColor, String text) {
        this(chatColor, text, 0);
    }

    public DescriptionOneColor(String text) {
        this(ChatColor.WHITE, text);
    }

    public void setChatColor(ChatColor chatColor){
        this.chatColor = chatColor;
    }

    @Override
    public String toString() {
        return chatColor + text;
    }
}
