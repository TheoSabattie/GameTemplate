package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.commandExecutor.commands.*;
import fr.ilicos.gameTemplate.mode.config.ArgType;
import fr.ilicos.gameTemplate.mode.config.Config;
import org.bukkit.Bukkit;
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
                    MainManager mainManager = MainManager.getInstance();
                    mainManager.setupGameMode();
                    mainManager.getWorld().save();

                    Bukkit.broadcastMessage("Switching in Game Mode...");
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
        commandModel = new CommandArchitecte();
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
}
