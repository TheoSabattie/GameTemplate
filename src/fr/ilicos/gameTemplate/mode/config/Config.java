package fr.ilicos.gameTemplate.mode.config;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.commandExecutor.CommandConfigModel;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public enum Config{
    MIN_PLAYER(new CommandConfigModel("setMinPlayer", 1, ArgType.POSITIVE_INT)),
    DELAY_BEFORE_START(new CommandConfigModel("setDelayBeforeStart", 1, ArgType.POSITIVE_INT)),
    TEAM_NUMBER(new CommandConfigModel("setTeamNumber", 1, ArgType.POSITIVE_INT)){
        @Override
        public void onArgs(String[] args, Player player) {
            super.onArgs(args, player);

            try{
                int newTeamSize = Integer.parseInt(args[0]);
                List<Team> teams = (List<Team>) TEAMS.getValue();

                if (teams == null){
                    teams = new ArrayList<>();
                }

                int nextTeamID = 0;

                for (Team team : teams){
                    if (team.getID() > nextTeamID){
                        nextTeamID = team.getID() + 1;
                    }
                }

                while (teams.size() < newTeamSize){
                    for (Team.TeamColor teamColor : Team.TeamColor.values()){
                        teams.add(teamColor.createTeam(nextTeamID));
                        nextTeamID++;

                        if (teams.size() == newTeamSize){
                            break;
                        }
                    }
                }

                while (teams.size() > newTeamSize){
                    teams.remove(teams.size() - 1);
                }

                TEAMS.setValue(teams);
            }catch (NumberFormatException ignored){}
        }
    },
    PLAYERS_TEAM_NUMBER(new CommandConfigModel("setPlayersTeamNumber", 1, ArgType.POSITIVE_INT)),
    TEAMS(new CommandConfigModel("setSpawnTeam", 1, ArgType.INT)){
        @Override
        public void onArgs(String[] args, Player player) {
            try {
                int teamID = Integer.parseInt(args[0]);
                List<Team> teams = (List<Team>) TEAMS.getValue();

                for (Team team : teams){
                    if (team.getID() == teamID){
                        team.setSpawn(player.getLocation());
                        TEAMS.setValue(teams);
                        return;
                    }
                }
            }catch (NumberFormatException ignored){}
        }

        @Override
        public Object getValue() {
            return MainManager.getInstance().getFileConfig().getList(this.name().toLowerCase());
        }

        @Override
        public boolean isValid() {
            List<Team> teams = (List<Team>) TEAMS.getValue();

            for (Team team : teams){
                if (team.getSpawn() == null){
                    return false;
                }
            }

            return true;
        }
    };

    private final CommandConfigModel commandModel;

    public CommandConfigModel getCommandModel(){
        return commandModel;
    }

    public void onArgs(String[] args, Player player){
        setValue(args[0]);
    }

    private void setValue(Object object){
        MainManager mainManager = MainManager.getInstance();
        mainManager.getFileConfig().set(this.name().toLowerCase(), object);
        mainManager.saveConfig();
    }

    public Object getValue(){
        return MainManager.getInstance().getFileConfig().get(this.name().toLowerCase());
    }

    public boolean isValid(){
        return getValue() != null;
    }

    public static boolean isCompleted(){
        for (Config config: values()){
            if (!config.isValid()){
                return false;
            }
        }
        return true;
    }

    Config(CommandConfigModel commandModel){
        this.commandModel = commandModel;
        commandModel.setConfig(this);
    }
}