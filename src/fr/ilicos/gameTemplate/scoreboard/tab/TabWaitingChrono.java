package fr.ilicos.gameTemplate.scoreboard.tab;

import fr.ilicos.gameTemplate.scoreboard.tab.description.Description;
import fr.ilicos.gameTemplate.scoreboard.tab.description.DescriptionChrono;

/**
 * Created by ilicos, Théo S. on 21/08/2015.
 */
public class TabWaitingChrono extends AbstractTab {
    private static TabWaitingChrono instance;
    private static final String CHRONO = "Chrono";

    public static TabWaitingChrono getInstance(){
        if (instance == null){
            instance = new TabWaitingChrono();
        }

        return instance;
    }

    protected TabWaitingChrono() {
        super("MySupergame");

        addDescription("legend", new Description("Waiting chrono..."));
        addDescription(CHRONO, new DescriptionChrono());
    }

    @Override
    public void destroy() {
        super.destroy();
        instance = null;
    }

    public void updateChrono(int counter) {
        DescriptionChrono description = (DescriptionChrono)descriptions.get(CHRONO);
        description.updateChrono(counter);
        update();
    }
}
