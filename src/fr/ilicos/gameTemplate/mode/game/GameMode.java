package fr.ilicos.gameTemplate.mode.game;

import fr.ilicos.gameTemplate.mode.AbtractMode;

/**
 * Created by Theo  Sabattie on 07/08/2015.
 */
public class GameMode extends AbtractMode {
    public GameMode(){
        super();
    }

    @Override
    protected void setupCommandExecutor() {
        commandExecutor = new GameCommandExecutor();
    }

}
