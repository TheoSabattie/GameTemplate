package fr.ilicos.gameTemplate.team;

import fr.ilicos.gameTemplate.itemInteractive.iteminteractives.ItemTeam;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 12/08/2015.
 */
public class Team implements ConfigurationSerializable {
    private static final String SPAWN = "spawn";
    private static final String TEAM_COLOR = "chatColor";
    private static final String ID_TEAM = "id";
    private static final String ITEM_SELECTION = "itemSelection";

    private static List<Team> teams = new ArrayList<>();
    public static void setTeams(List<Team> teams2){
        teams = teams2;
    }

    public static List<Team> getTeams(){
        return teams;
    }

    public int size(){
        return players.size();
    }

    private static int maxPlayers = 1;

    public static void setMaxPlayers(int newMaxPlayers){
        maxPlayers = newMaxPlayers;
    }

    private final List<PlayerContainer> players = new ArrayList<>();
    private final int ID;
    private Location spawn;
    private TeamColor teamColor;
    private final ItemTeam itemTeam;

    public Team(TeamColor teamColor, int ID, Location spawn){
        this(teamColor, ID);
        this.spawn = spawn;
    }

    public Team(TeamColor teamColor, int ID){
        this.teamColor = teamColor;
        this.ID = ID;
        itemTeam = new ItemTeam(teamColor.getItemSelection(), this);
    }

    public void addPlayer(Player player){
        PlayerContainer playerContainer = PlayerContainer.getPlayerContainerFromPlayer(player);

        if (!players.contains(playerContainer)){
            if (!isComplete()){
                player.sendMessage("Vous avez rejoint l'équipe " + getTeamColor() + getTeamColor().name());
                players.add(playerContainer.transformToWaitingTeamedPlayer(this));
            } else {
                player.sendMessage("Équipe pleine!");
            }
        } else {
            player.sendMessage("Vous êtes déjà dans l'équipe " + getTeamColor() + getTeamColor().name());
        }
    }

    public void removePlayer(PlayerContainer playerContainer){
        players.remove(playerContainer);
    }

    public boolean isComplete(){
        return (size() >= maxPlayers);
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location location) {
        this.spawn = location;
    }

    public int getID() {
        return ID;
    }
    public List<PlayerContainer> getPlayers(){
        return new ArrayList<>(players);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put(TEAM_COLOR, teamColor.name());
        data.put(SPAWN, spawn);
        data.put(ID_TEAM, ID);

        return data;
    }

    public static Team deserialize(Map<String, Object> args) {
        return new Team(TeamColor.valueOf((String)args.get(TEAM_COLOR)), (int)args.get(ID_TEAM), (Location)args.get(SPAWN));
    }

    public ItemTeam getItemTeam() {
        return itemTeam;
    }
}
