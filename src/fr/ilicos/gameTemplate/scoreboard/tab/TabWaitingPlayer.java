package fr.ilicos.gameTemplate.scoreboard.tab;

import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scoreboard.tab.description.Description;
import fr.ilicos.gameTemplate.scoreboard.tab.description.DescriptionPlayerConnected;
import org.bukkit.Bukkit;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class TabWaitingPlayer extends AbstractTab {
    private static TabWaitingPlayer instance;

    private final static String PLAYERS_NUMBER = "player_number";

    public static TabWaitingPlayer getInstance(){
        if (instance == null){
            instance = new TabWaitingPlayer();
        }

        return instance;
    }

    protected TabWaitingPlayer() {
        super("MySupergame");

        addDescription("legend", new Description("Waiting player..."));
        addDescription(PLAYERS_NUMBER, new DescriptionPlayerConnected(Bukkit.getOnlinePlayers().size(), (int)Config.MIN_PLAYER.getValue()));
    }

    public DescriptionPlayerConnected getDescriptionPlayerConnected(){
        return (DescriptionPlayerConnected)descriptions.get(PLAYERS_NUMBER);

    }

    @Override
    public void destroy() {
        super.destroy();
        instance = null;
    }

    public void onPlayerConnection() {
        getDescriptionPlayerConnected().setPlayersNumber(PlayerContainer.getPlayersContainers().size());
        update();
    }

    public void onPlayerDisconnection() {
        getDescriptionPlayerConnected().setPlayersNumber(PlayerContainer.getPlayersContainers().size() - 1);
        update();
    }
}
