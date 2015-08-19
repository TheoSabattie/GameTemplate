package fr.ilicos.gameTemplate.mode.config;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.commandExecutor.CommandConfigModel;
import fr.ilicos.gameTemplate.team.Team;
import fr.ilicos.gameTemplate.team.TeamColor;
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
        public boolean onArgs(String[] args, Player player) {
            super.onArgs(args, player);

            try{
                int newTeamSize = Integer.parseInt(args[0]);
                List<Team> teams = (List) TEAMS.getValue();

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
                    for (TeamColor teamColor : TeamColor.values()){
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
                TEAM_NUMBER.setValue(newTeamSize);
                return true;
            }catch (NumberFormatException ignored){}

            return false;
        }
    },
    PLAYERS_TEAM_NUMBER(new CommandConfigModel("setPlayersTeamNumber", 1, ArgType.POSITIVE_INT)),
    TEAMS(new CommandConfigModel("setSpawnTeam", 1, ArgType.INT)){
        @Override
        public boolean onArgs(String[] args, Player player) {
            try {
                int teamID = Integer.parseInt(args[0]);
                List<Team> teams = (List) TEAMS.getValue();
                List<Integer> teamsID = new ArrayList<>();

                if (teams != null){
                    for (Team team : teams){
                        teamsID.add(team.getID());

                        if (team.getID() == teamID){
                            team.setSpawn(player.getLocation());
                            TEAMS.setValue(teams);
                            return true;
                        }
                    }
                } else {
                    player.sendMessage("Error: TeamNumber is not set, you must set it before configure spawn.");
                    return false;
                }

                player.sendMessage("Error: ID not found, list of IDs : " + teamsID.toString());

            }catch (NumberFormatException ignored){}

            return false;
        }

        @Override
        public Object getValue() {
            return MainManager.getInstance().getFileConfig().getList(this.name().toLowerCase());
        }

        @Override
        public boolean isValid() {
            List<Team> teams = (List) TEAMS.getValue();

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

    public boolean onArgs(String[] args, Player player){
        if(commandModel.getArgType() == ArgType.INT
                || commandModel.getArgType() == ArgType.POSITIVE_INT
                || commandModel.getArgType() == ArgType.NEGATIVE_INT){
            setValue(Integer.parseInt(args[0]));
        } else {
            setValue(args[0]);
        }

        return true;
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