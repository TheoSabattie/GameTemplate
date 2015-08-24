package fr.ilicos.gameTemplate.scoreboard.tab;

import fr.ilicos.gameTemplate.mode.config.Config;
import fr.ilicos.gameTemplate.scoreboard.tab.description.Description;
import fr.ilicos.gameTemplate.scoreboard.tab.description.DescriptionOneColor;
import org.bukkit.ChatColor;

/**
 * Created by ilicos, Théo S. on 06/07/2015.
 */
public class TabConfig extends AbstractTab{
    private static TabConfig instance;

    public static TabConfig getInstance(){
        if (instance == null) {
            instance = new TabConfig();
        }

        return instance;
    }

    public TabConfig() {
        super("ConfigMode");

        Description description;

        for (Config config : Config.values()){
            description = new DescriptionOneColor((config.isValid())?ChatColor.GREEN:ChatColor.RED, config.name().toLowerCase());

            setDescriptionNumber(description, config.getValue());
            addDescription(config.name(), description);
        }
    }

    protected void setDescriptionNumber(Description description, Object value){
        if (value instanceof Integer){
            description.setNumber((int) value);
        }
    }

    public void onConfigChange(Config config, Object value){
        DescriptionOneColor description = (DescriptionOneColor) descriptions.get(config.name());

        description.setChatColor((config.isValid()) ? ChatColor.GREEN : ChatColor.RED);
        setDescriptionNumber(description, value);
        update();
    }

    @Override
    public void destroy() {
        super.destroy();
        instance = null;
    }
}
