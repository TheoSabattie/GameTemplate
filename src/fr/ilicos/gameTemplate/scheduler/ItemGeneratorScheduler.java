package fr.ilicos.gameTemplate.scheduler;

import fr.ilicos.gameTemplate.itemGenerator.AbstractGenerator;

/**
 * Created by ilicos, Théo S. on 23/08/2015.
 */
public class ItemGeneratorScheduler extends AbstractScheduler {
    private final AbstractGenerator generator;

    public ItemGeneratorScheduler(AbstractGenerator generator){
        this.generator = generator;
    }

    @Override
    public void run() {
        generator.generate();
    }

    @Override
    protected void setupStick() {
        stick = 120;
    }
}
