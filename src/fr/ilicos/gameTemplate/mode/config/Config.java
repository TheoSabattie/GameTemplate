package fr.ilicos.gameTemplate.mode.config;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class Config implements ConfigurationSerializable {
    private int minPlayers;
    private int delayBeforeStart;

    public Config(){

    }

    public Map<String, Object> serialize() {
        HashMap data = new HashMap();
        data.put("minPlayers", this.minPlayers);
        data.put("delayBeforeStart", this.minPlayers);
        return data;
    }

    public static Config deserialize(Map<String, Object> args) {
        Config config = new Config();

        config.minPlayers       = (int) args.get("minPlayers");
        config.delayBeforeStart = (int) args.get("delayBeforeStart");

        return config;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getDelayBeforeStart() {
        return delayBeforeStart;
    }
}
