package fr.ilicos.gameTemplate.scheduler;

import org.bukkit.Bukkit;

/**
 * Created by ilicos, Théo S. on 09/08/2015.
 */
public class ChronoStarterScheduler extends AbstractScheduler {
    private final int COUNTER;
    private int counter;
    private final Delegate delegate;

    public static interface Delegate{
        void onChronoFinished();
    }

    public ChronoStarterScheduler (Delegate delegate, int delay){
        this.delegate = delegate;
        this.COUNTER = delay;
    }

    @Override
    public void run() {
        if (counter == COUNTER){
            Bukkit.broadcastMessage("Démarrage du chronomètre : " + counter + "s");
        } else if (counter%10 == 0 && counter > 0) {
            showChrono();
        } else if (counter <= 5 && counter > 0){
            showChrono();
        } else if (counter <= 0){
            stop();
            delegate.onChronoFinished();
            return;
        }

        counter--;
    }

    @Override
    public void start() {
        counter = COUNTER;
        super.start();
    }

    private void showChrono(){
        Bukkit.broadcastMessage("Chronomètre : " + counter + "s");
    }
}
