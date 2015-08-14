package fr.ilicos.gameTemplate.commandExecutor;

import fr.ilicos.gameTemplate.mode.config.ArgType;
import fr.ilicos.gameTemplate.mode.config.Config;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 14/08/2015.
 */
public class CommandConfigModel extends CommandModel {
    private Config config;

    public CommandConfigModel(String commandLabel, int argsNumber, ArgType argType) {
        super(commandLabel, argsNumber, argType);
    }

    public void setConfig(Config config){
        this.config = config;
        successMessage = "Saving change in " + config.name().toLowerCase() + " from the command " + getCommandLabel();
    }

    @Override
    protected boolean isSuccessCommand(String[] args, Player player) {
        if (config!= null){
            config.onArgs(args, player);

            return true;
        }

        return false;
    }
}
