package fr.ilicos.gameTemplate;

import fr.ilicos.gameTemplate.listener.ConnectionDisconnectionListener;
import fr.ilicos.gameTemplate.mode.AbtractMode;
import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.mode.config.ConfigMode;
import fr.ilicos.gameTemplate.mode.game.GameMode;
import fr.ilicos.gameTemplate.player.PlayerContainer;
import fr.ilicos.gameTemplate.scheduler.AbstractScheduler;
import fr.ilicos.gameTemplate.scoreboard.ScoreboardManager;
import fr.ilicos.gameTemplate.team.Team;
import fr.ilicos.gameTemplate.utils.Destroyable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.List;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class MainManager extends Destroyable {
    private static final String WORLD_NAME = "world";

    private final Listener connectionDisconnectionListener = new ConnectionDisconnectionListener() {
        @Override
        public void onPlayerConnection(PlayerContainer playerContainer) {
            currentMode.onPlayerConnection(playerContainer);
            ScoreboardManager.getInstance().setScoreboardPlayer(playerContainer.getPlayer());
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
        cleanData();
    }

    private void reloadPrevention() {
        PlayerContainer playerContainer;

        for (Player player : Bukkit.getOnlinePlayers()){
            playerContainer = new PlayerContainer(player);
            ScoreboardManager.getInstance().setScoreboardPlayer(playerContainer.getPlayer());
        }
    }

    public void init(GameTemplate plugin){
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        addListener(connectionDisconnectionListener);
        getWorld().setAutoSave(false);

        if (Config.isCompleted()){
            setupGameMode();
        } else {
            setupConfigMode();
        }
    }

    public FileConfiguration getFileConfig(){
        FileConfiguration fileConfig = plugin.getConfig();
        fileConfig.options().copyDefaults(true);
        return fileConfig;
    }

    public void saveConfig(){
        plugin.saveConfig();
    }

    public void setupConfigMode (){
        destroyCurrentMode();
        currentMode = new ConfigMode(/*getConfig()*/);
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
        Location location = (Location)Config.WAITING_ROOM_SPAWN.getValue();
        getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        Team.setMaxPlayers((int)Config.PLAYERS_TEAM_NUMBER.getValue());
        Team.setTeams((List) Config.TEAMS.getValue());

        /**
         * If you want remove Craft Recipe and add your recipe :
         */
        /*plugin.getServer().clearRecipes();
        ItemStack diamondSword = new ItemStack(Material.STONE_SWORD, 1);

        ShapedRecipe diamondSwordRecipe = new ShapedRecipe(diamondSword);
        diamondSwordRecipe.shape("o", "|");

        diamondSwordRecipe.setIngredient('|', Material.STICK);
        diamondSwordRecipe.setIngredient('o', Material.COBBLESTONE);
        plugin.getServer().addRecipe(diamondSwordRecipe);
        */
    }

    public World getWorld(){
        return Bukkit.getWorld(WORLD_NAME);
    }

    private void cleanData(){
        File playerFilesDir = new File(getWorld().getWorldFolder().getAbsolutePath() + "/playerdata");
        if(playerFilesDir.isDirectory()){
            String[] playerDats = playerFilesDir.list();

            for (int i = 0; i < playerDats.length; i++) {
                File datFile = new File(playerFilesDir, playerDats[i]);
                datFile.delete();
            }
        }
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
