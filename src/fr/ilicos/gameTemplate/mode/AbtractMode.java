package fr.ilicos.gameTemplate.mode;

import fr.ilicos.gameTemplate.GameObject;
import fr.ilicos.gameTemplate.MainManager;
import org.bukkit.command.CommandExecutor;

/**
 * Created by Theo  Sabattie on 07/08/2015.
 */
public abstract class AbtractMode extends GameObject {
    protected CommandExecutor commandExecutor;

    public AbtractMode(){
        setupCommandExecutor();

        MainManager mainManager = MainManager.getInstance();
        mainManager.setConfigCommandExecutor(commandExecutor);
    }

    protected abstract void setupCommandExecutor();

    @Override
    public void destroy() {
        MainManager mainManager = MainManager.getInstance();
    }
}
