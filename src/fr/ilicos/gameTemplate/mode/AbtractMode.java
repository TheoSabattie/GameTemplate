package fr.ilicos.gameTemplate.mode;

import fr.ilicos.gameTemplate.listener.ConnectionDisconnection;
import fr.ilicos.gameTemplate.utils.GameObject;
import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import org.bukkit.command.CommandExecutor;

/**
 * Created by ilicos, Th�o S. on 07/08/2015.
 */
public abstract class AbtractMode extends GameObject implements ConnectionDisconnection {
    protected CommandExecutor commandExecutor;

    public AbtractMode(){
        setupCommandExecutor();

        MainManager mainManager = MainManager.getInstance();
        mainManager.setConfigCommandExecutor(commandExecutor);
    }

    protected abstract void setupCommandExecutor();

    @Override
    public void onPlayerDisconnection(PlayerContainer playerContainer) {}

    @Override
    public void destroy(){}
}
