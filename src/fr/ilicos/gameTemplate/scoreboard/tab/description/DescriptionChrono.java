package fr.ilicos.gameTemplate.scoreboard.tab.description;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class DescriptionChrono extends Description{
    private final int MINUTE_TO_SECONDS = 60;

    public DescriptionChrono() {
        super("");
    }


    public void updateChrono(int counter) {
        text = (int)Math.floor(counter/MINUTE_TO_SECONDS) + ":" + counter%MINUTE_TO_SECONDS;
    }
}
