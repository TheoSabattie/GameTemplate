package fr.ilicos.gameTemplate;

import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.team.Team;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class GameTemplate extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        registerClassToDeserialization();
        MainManager.getInstance().init(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MainManager.getInstance().destroy();
    }

    private void registerClassToDeserialization(){
        Team.class.getClass();
        Config.class.getClass();
        Location.class.getClass();
    }
}
