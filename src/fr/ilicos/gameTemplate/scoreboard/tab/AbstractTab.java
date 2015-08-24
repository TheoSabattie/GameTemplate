package fr.ilicos.gameTemplate.scoreboard.tab;

import fr.ilicos.gameTemplate.scoreboard.tab.description.Description;
import fr.ilicos.gameTemplate.utils.Destroyable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ilicos, Théo S. on 06/07/2015.
 */
public abstract class AbstractTab extends Destroyable{
    private static final String TYPE = "dummy";

    protected final Map<String, Description> descriptions = new HashMap<>();
    private Scoreboard scoreboard;

    private String name;
    private Objective tab;

    protected AbstractTab(String name){
        this.name = name;
    }

    public void init(Scoreboard scoreboard){
        this.scoreboard = scoreboard;
        update();
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setupNewTab(){
        if (tab != null){
            tab.unregister();
        }
        if (scoreboard != null){
            tab = scoreboard.registerNewObjective(name, TYPE);
        }
    }

    public void show(){
        if (tab != null){
            tab.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    private void setupDescription(Description description){
        if (tab != null){
            tab.getScore(description.toString()).setScore(description.getNumber());
        }
    }

    protected void addDescription(String name, Description description){
        descriptions.put(name, description);
        setupDescription(description);
    }

    protected void update(){
        setupNewTab();

        for (Description description : descriptions.values()){
            setupDescription(description);
        }

        show();
    }

    @Override
    public void destroy(){
        tab.unregister();
    }

}
