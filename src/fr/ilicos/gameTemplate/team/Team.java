package fr.ilicos.gameTemplate.team;

import fr.ilicos.gameTemplate.player.PlayerContainer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 12/08/2015.
 */
public class Team implements ConfigurationSerializable {
    private static final String SPAWN = "spawn";
    private static final String CHAT_COLOR = "chatColor";
    private static final String ID_TEAM = "id";

    private static int maxPlayers = 1;

    public static void setMaxPlayers(int newMaxPlayers){
        maxPlayers = newMaxPlayers;
    }

    private final List<PlayerContainer> PlayerContainers = new ArrayList<>();
    private Location spawn;
    private ChatColor chatColor;
    private final int ID;

    public Team(ChatColor chatColor, int ID){
        this.ID = ID;
        this.chatColor = chatColor;
    }

    public Team(ChatColor chatColor, int ID, Location spawn){
        this(chatColor, ID);
        this.spawn = spawn;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location location) {
        this.spawn = location;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public void setChatColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public int getID() {
        return ID;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put(CHAT_COLOR, chatColor.name());
        data.put(SPAWN, spawn);
        data.put(ID_TEAM, ID);

        return data;
    }

    public static Team deserialize(Map<String, Object> args) {
        return new Team(ChatColor.valueOf((String)args.get(CHAT_COLOR)), (int)args.get(ID_TEAM), (Location)args.get(SPAWN));
    }

    public static enum TeamColor{
        BLUE(ChatColor.BLUE),
        RED(ChatColor.RED),
        GREEN(ChatColor.GREEN),
        YELLOW(ChatColor.YELLOW);

        private ChatColor chatColor;

        public ChatColor getChatColor(){
            return chatColor;
        }

        TeamColor(ChatColor chatColor){
            this.chatColor = chatColor;
        }

        public Team createTeam(int ID){
            return new Team(chatColor, ID);
        }
    }
}
