package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.mode.config.ArgType;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 14/08/2015.
 */
public abstract class CommandModel {
    private final String commandLabel;
    private final int argsNumber;
    private final ArgType argType;
    protected String successMessage = "";

    public CommandModel(String commandLabel, int argsNumber, ArgType argType, String successMessage){
        this(commandLabel, argsNumber, argType);
        this.successMessage = successMessage;
    }

    public CommandModel(String commandLabel, int argsNumber, ArgType argType){
        this.commandLabel   = commandLabel;
        this.argsNumber     = argsNumber;
        this.argType        = argType;
    }

    protected abstract boolean isSuccessCommand(String args[], Player player);

    public String getCommandLabel() {
        return commandLabel;
    }

    public int getArgsNumber() {
        return argsNumber;
    }

    public ArgType getArgType() {
        return argType;
    }

    public void executeCommand(String[] args, Player player){
        if (isSuccessCommand(args, player)){
            player.sendMessage(successMessage);
        }
    }
}
