package fr.ilicos.gameTemplate.commandExecutor.commands;

import fr.ilicos.gameTemplate.mode.config.ArgType;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public abstract class CommandVillager extends CommandModel {
    public CommandVillager(String commandLabel) {
        super(commandLabel, 0, ArgType.NULL, "Villageois ajouté");
    }
}
