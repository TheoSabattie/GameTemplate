package fr.ilicos.gameTemplate.mode.config;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Theo  Sabattie on 07/08/2015.
 */
public class Config implements ConfigurationSerializable {
    private float exempleMaxArrow;

    public Config(){

    }

    public Map<String, Object> serialize() {
        HashMap data = new HashMap();
        data.put("exempleMaxArrow", this.exempleMaxArrow);
        return data;
    }

    public static Config deserialize(Map<String, Object> args) {
        Config config = new Config();
        config.exempleMaxArrow = (float) args.get("exempleMaxArrow");
        return config;
    }
}
