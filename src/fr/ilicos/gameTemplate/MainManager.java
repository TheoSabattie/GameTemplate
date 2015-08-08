package fr.ilicos.gameTemplate;

import fr.ilicos.gameTemplate.listener.ConnectionDisconnectionListener;
import fr.ilicos.gameTemplate.mode.AbtractMode;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.mode.config.ConfigMode;
import fr.ilicos.gameTemplate.mode.game.GameMode;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scheduler.AbstractScheduler;
import fr.ilicos.gameTemplate.utils.GameObject;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class MainManager extends GameObject {
    private final Listener connectionDisconnectionListener = new ConnectionDisconnectionListener() {
        @Override
        public void onPlayerConnection(PlayerContainer playerContainer) {
            currentMode.onPlayerConnection(playerContainer);
        }

        @Override
        public void onPlayerDisconnection(PlayerContainer playerContainer) {
            currentMode.onPlayerDisconnection(playerContainer);
        }
    };

    private static MainManager instance;
    private GameTemplate plugin;
    private AbtractMode currentMode;

    public static MainManager getInstance() {
        if (instance == null){
            instance = new MainManager();
        }
        return instance;
    }

    private MainManager() {
        reloadPrevention();
    }

    private void reloadPrevention() {
        for (Player player : Bukkit.getOnlinePlayers()){
            new PlayerContainer(player);
        }
    }

    public void init(GameTemplate plugin){
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        addListener(connectionDisconnectionListener);

        if (configIsCompleted(getConfig())){
            setupConfigMode();
        } else {
            setupGameMode();
        }
    }

    public Config getConfig(){
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

    @Override
    public void destroy() {
        currentMode.destroy();
        removeListener(connectionDisconnectionListener);
        instance = null;
    }

    public void addRunTaskTimerScheduler(AbstractScheduler abstractScheduler, int beginDelay, int delayBetweenTasks) {
        abstractScheduler.runTaskTimer(plugin, beginDelay, delayBetweenTasks);
    }
}
