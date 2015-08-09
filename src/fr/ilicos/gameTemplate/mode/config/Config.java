package fr.ilicos.gameTemplate.mode.config;

import fr.ilicos.gameTemplate.MainManager;
import fr.ilicos.gameTemplate.utils.MathNum;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 07/08/2015.
 */
public class Config implements ConfigurationSerializable {

    private int minPlayers;
    private int delayBeforeStart;

    public Config(){}

    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("minPlayers", this.getMinPlayers());
        data.put("delayBeforeStart", this.getDelayBeforeStart());
        return data;
    }

    public static Config deserialize(Map<String, Object> args) {
        Config config = new Config();

        config.setMinPlayers((int) args.get("minPlayers"));
        config.setDelayBeforeStart((int) args.get("delayBeforeStart"));

        return config;
    }

    public int getMinPlayers() {
        return minPlayers;
    }
    public boolean setMinPlayers(int minPlayers) {
        if (MathNum.positiveInt(minPlayers)){
            this.minPlayers = minPlayers;
            onValueChanged();
            return true;
        }
        return false;
    }

    public int getDelayBeforeStart() {
        return delayBeforeStart;
    }

    public boolean setDelayBeforeStart(int delayBeforeStart) {
        if (MathNum.positiveInt(delayBeforeStart)){
            this.delayBeforeStart = delayBeforeStart;
            onValueChanged();
            return true;
        }
        return false;
    }

    private void onValueChanged(){
        MainManager.getInstance().saveConfig(this);
    }

    public boolean isCompleted(){
        return getInvalidValues().size() == 0;
    }

    public List<String> getInvalidValues(){
        List<String> invalidValues = new ArrayList<>();

        if (delayBeforeStart <= 0){
            invalidValues.add("delayBeforeStart");
        }
        if (minPlayers <= 0){
            invalidValues.add("minPlayers");
        }

        return invalidValues;
    }
}
