package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.kit.KitRegister;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.player.WaitingPlayer;
import fr.ilicos.gameTemplate.player.WaitingTeamedPlayer;
import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.scoreboard.tab.TabGame;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 09/08/2015.
 */
public class Game extends AbstractGame {
    public Game(GameMode gameMode) {
        super(gameMode);

        List<Team> teams = new ArrayList<>(Team.getTeams());

        for (WaitingPlayer waitingPlayer : WaitingPlayer.getWaitingPlayers()){
            for (Team team : teams){
                if (!team.isComplete()){
                    team.addPlayer(waitingPlayer.getPlayer());
                }
            }
        }

        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2) {
                return team1.size() - team2.size();
            }
        });

        Team maxPlayersTeam = teams.get(teams.size() - 1);
        Team minPlayersTeam = teams.get(0);

        while (Math.abs(maxPlayersTeam.size() - minPlayersTeam.size()) > 1){
            maxPlayersTeam.getPlayers().get(0).transformToWaitingTeamedPlayer(minPlayersTeam);

            Collections.sort(teams, new Comparator<Team>() {
                @Override
                public int compare(Team team1, Team team2) {
                    return team1.size() - team2.size();
                }
            });

            maxPlayersTeam = teams.get(teams.size() - 1);
            minPlayersTeam = teams.get(0);
        }

        for (WaitingPlayer waitingPlayer : WaitingPlayer.getWaitingPlayers()){
            waitingPlayer.transformToSpectator();
        }

        for (WaitingTeamedPlayer waitingTeamedPlayer : WaitingTeamedPlayer.getWaitingTeamedPlayers()){
            waitingTeamedPlayer.transformToGamePlayer();
        }

        Player player;

        for (PlayerContainer playerContainer : PlayerContainer.getPlayersContainers()){
            player = playerContainer.getPlayer();
            player.getInventory().clear();
            player.updateInventory();
        }

        Bukkit.getWorlds().get(0).setPVP(true);

        ScoreboardManager.getInstance().setTab(TabGame.getInstance());

        KitRegister.getInstance().applyAllKits();
    }

    @Override
    public void onPlayerConnection(PlayerContainer playerContainer) {
        playerContainer.transformToSpectator();
    }

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {

    }

    @Override
    public void destroy() {
        TabGame.getInstance().destroy();
    }
}
