package fr.ilicos.gameTemplate.scheduler;

import fr.ilicos.gameTemplate.MainManager;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by ilicos, Théo S. on 09/08/2015.
 */
public abstract class AbstractScheduler extends BukkitRunnable {
    private int stick;

    public AbstractScheduler(){
        setupStick();
    }

    protected void setupStick(){
        stick = 20;
    }

    public void stop(){
        cancel();
    }

    public void start(){
        MainManager.getInstance().addRunTaskTimerScheduler(this, 0, stick);
    }

}
