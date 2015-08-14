package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.mode.config.ArgType;
import fr.ilicos.gameTemplate.mode.config.Config;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class ConfigCommandExecutor extends AbstractCommandExecutor {
    private Config config;
    private final Map<String, CommandModel> commands = new HashMap<>();

    public void setConfig(Config config) {
        this.config = config;
    }


    public ConfigCommandExecutor(){
        CommandModel commandModel;

        for (Config config : Config.values()){
            commandModel = config.getCommandModel();
            commands.put(commandModel.getCommandLabel(), commandModel);
        }

        commandModel = new CommandModel("validation", 0, ArgType.NULL) {
            @Override
            protected boolean isSuccessCommand(String[] args, Player player) {
                if (Config.isCompleted()){
                    MainManager.getInstance().setupGameMode();
                } else {
                    player.sendMessage("They miss some values setting:");

                    for (Config config:Config.values()){
                        if (!config.isValid()){
                            player.sendMessage("- " + config.name().toLowerCase() + " (" + config.getCommandModel().getCommandLabel() + ")");
                        }
                    }
                }

                return false;
            }
        };

        commands.put(commandModel.getCommandLabel(), commandModel);
    }

    @Override
    protected boolean onPlayerCommand(Player player, String[] args) {
        if (args.length > 0){
            CommandModel commandModel = commands.get(args[0]);
            args = Arrays.copyOfRange(args, 1, args.length);

            if(commandModel != null){
                int argsNumber = commandModel.getArgsNumber();

                if (argsNumber == args.length){
                    ArgType argType = commandModel.getArgType();

                    if (argType.validArg(args)){
                        commandModel.executeCommand(args, player);

                        return true;
                    } else {
                        player.sendMessage("Error: Command need " + argType.name().toLowerCase() + " type");
                    }
                } else {
                    player.sendMessage("Error: Command need " + argsNumber + " argument(s) (only)!");
                }
            } else {
                player.sendMessage("Command does not exist");
                showListCommand(player);
            }
        } else {
            showListCommand(player);
        }

        return false;
    }

    private void showListCommand(Player player){
        player.sendMessage("list of command:");

        for (CommandModel commandModel2:commands.values()){
            if (commandModel2 != null){
                player.sendMessage("/config " + commandModel2.getCommandLabel() + " {"+commandModel2.getArgsNumber()+"arg(s)}");
            }
        }
    }

/*
    @Override
    protected boolean onPlayerCommand(Player player, String[] args) {
        if (args.length > 0){
            /**
             * Search arguments and work with them
             *//*
            if (args[0].equals("validation") && args.length == 1){
                onConfigValidation(player);
                return true;
            }

            if (args[0].equals(Config.DELAY_BEFORE_START) && args.length == 2){
                onDelayBeforeStart(player, args[1]);
                return true;
            }

            if (args[0].equals(Config.MIN_PLAYER) && args.length == 2){
                onMinPlayer(player, args[1]);
                return true;
            }

            if (args[0].equals(Config.TEAM_NUMBER) && args.length == 2){
                onTeamNumber(player, args[1]);
                return true;
            }

            if (args[0].equals(Config.TEAM_SPAWN) && args.length == 2){
                onTeamSpawn(player, args[1]);
                return true;
            }

            if (args[0].equals(Config.PLAYERS_TEAM_NUMBER) && args.length == 2){
                onPlayersTeamNumber(player, args[1]);
                return true;
            }


}

        player.sendMessage("Bad Usage! List of commands : ");

        for (String invalidValue : config.getInvalidValues()){
            player.sendMessage("/config " + invalidValue + " [arg]");
        }

        player.sendMessage("/config validation");

        return false;
    }

    private void onPlayersTeamNumber(Player player, String stringValue) {
        try {
            int value = Integer.parseInt(stringValue);

            if (config.setPlayersTeamNumber(value)){
                player.sendMessage(Config.PLAYERS_TEAM_NUMBER + " is set!");
            } else {
                messageToPlayerArgMustBePositive(player);
            }
        } catch (NumberFormatException nfe) {
            messageToPlayerArgMustBeInterger(player);
        }
    }

    private void onTeamSpawn(Player player, String stringValue) {
        try {
            int value = Integer.parseInt(stringValue);

            if(config.setTeamSpawn(value, player.getLocation())){
                player.sendMessage(Config.TEAM_SPAWN + " " + value + " is set!");
            } else {
                player.sendMessage("Id team does not exist");
            }
        } catch (NumberFormatException nfe) {
            messageToPlayerArgMustBeInterger(player);
        }
    }

    private void onTeamNumber(Player player, String stringValue) {
        try {
            int value = Integer.parseInt(stringValue);

            if (config.setTeamsNumber(value)){
                player.sendMessage(Config.TEAM_NUMBER + " is set!");
            } else {
                messageToPlayerArgMustBePositive(player);
            }
        } catch (NumberFormatException nfe) {
            messageToPlayerArgMustBeInterger(player);
        }
    }

    private void onMinPlayer(Player player, String stringValue) {
        try {
            int value = Integer.parseInt(stringValue);

            if(config.setMinPlayers(value)){
                player.sendMessage(Config.MIN_PLAYER + " is set!");
            } else {
                messageToPlayerArgMustBePositive(player);
            }
        } catch (NumberFormatException nfe) {
            messageToPlayerArgMustBeInterger(player);
        }
    }

    private void onDelayBeforeStart(Player player, String stringValue) {
        try {
            int value = Integer.parseInt(stringValue);

            if (config.setDelayBeforeStart(value)){
                player.sendMessage(Config.DELAY_BEFORE_START + " is set!");
            } else {
                messageToPlayerArgMustBePositive(player);
            }
        } catch (NumberFormatException nfe) {
            messageToPlayerArgMustBeInterger(player);
        }
    }

    private void onConfigValidation(Player player) {
        if (config.isCompleted()){
            Bukkit.broadcastMessage("End of configuration, setup Game mode");
            MainManager.getInstance().setupGameMode();
        } else {
            player.sendMessage("Configuration is not completed, it misses : ");

            for (String valueName : config.getInvalidValues()){
                player.sendMessage(" - " + valueName);
            }
        }
    }

    private void messageToPlayerArgMustBeInterger(Player player){
        player.sendMessage("Error: argument must be a integer!");
    }

    private void messageToPlayerArgMustBePositive(Player player) {
        player.sendMessage("Error: argument value must be positive");
    }*/
}
