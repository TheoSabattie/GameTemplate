package fr.ilicos.gameTemplate;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class GameTemplate extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        MainManager.getInstance().init(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MainManager.getInstance().destroy();
    }
}
