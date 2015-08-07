package fr.ilicos.gameTemplate;

import fr.ilicos.gameTemplate.mode.AbtractMode;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.mode.config.ConfigMode;
import fr.ilicos.gameTemplate.mode.game.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created by Theo  Sabattie on 07/08/2015.
 */
public class MainManager {
    private static MainManager instance = new MainManager();
    private GameTemplate plugin;
    private AbtractMode currentMode;

    public static MainManager getInstance() {
        return instance;
    }

    private MainManager() {
    }

    public void init(GameTemplate plugin){
        this.plugin = plugin;
        plugin.saveDefaultConfig();

        if (configIsCompleted(getConfig())){
            setupConfigMode();
        } else {
            setupGameMode();
        }
    }

    private Config getConfig(){
        Config config = (Config) getFileConfig().get("config");

        return (config == null)? new Config() : config;
    }

    private FileConfiguration getFileConfig(){
        FileConfiguration fileConfig = plugin.getConfig();
        fileConfig.options().copyDefaults(true);
        return fileConfig;
    }

    public void saveConfig(Config config){
        getFileConfig().set("config", config);
        plugin.saveConfig();
    }

    public boolean configIsCompleted(Config config) {
        /**
         * If some specific locations is need for team, you can't start game before set its
         */
        return true;
    }

    public void setupConfigMode (){
        destroyCurrentMode();
        currentMode = new ConfigMode(getConfig());
    }

    public void setupGameMode (){
        applyConfig();
        destroyCurrentMode();
        currentMode = new GameMode();
    }

    private void destroyCurrentMode(){
        if (currentMode != null){
            currentMode.destroy();
        }
    }

    private void applyConfig() {
        /**
         * Set all static variable on classes
         */
    }

    public void addListener (Listener listener){
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    public void removeListener (Listener listener){
        HandlerList.unregisterAll(listener);
    }

    public void setConfigCommandExecutor(CommandExecutor commandExecutor) {
        plugin.getCommand("config").setExecutor(commandExecutor);
    }
}
