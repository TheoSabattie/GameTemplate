package fr.ilicos.gameTemplate.commandExecutor.commands;

import fr.ilicos.gameTemplate.mode.config.ArgType;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.scoreboard.tab.TabConfig;
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
            if (config.onArgs(args, player)){
                TabConfig.getInstance().onConfigChange(config, config.getValue());
                return true;
            }
        }

        return false;
    }
}
