package fr.ilicos.gameTemplate.commandExecutor.commands;

import fr.ilicos.gameTemplate.villager.villagers.Architecte;
import org.bukkit.entity.Player;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class CommandArchitecte extends CommandVillager {
    public CommandArchitecte() {
        super("addArchitecte");
    }

    @Override
    protected boolean isSuccessCommand(String[] args, Player player) {
        new Architecte(player.getLocation());
        return true;
    }
}
