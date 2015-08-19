package fr.ilicos.gameTemplate.kit;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 15/08/2015.
 */
public class KitRegister {
    private static KitRegister instance;

    public static KitRegister getInstance(){
        if (instance == null){
            instance = new KitRegister();
        }
        return instance;
    }

    private KitRegister(){}

    private final Map<Player, Kit> register = new HashMap<>();

    public void regist(Player player, Kit kit){
        register.put(player, kit);
    }

    public void applyAllKits(){
        for (Player player: register.keySet()){
            register.get(player).applyToPlayer(player);
        }

        register.clear();
    }
}
