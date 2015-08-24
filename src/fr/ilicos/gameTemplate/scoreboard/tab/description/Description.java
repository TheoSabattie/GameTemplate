package fr.ilicos.gameTemplate.scoreboard.tab.description;

/**
 * Created by ilicos, Théo S. on 06/07/2015.
 */
public class Description {
    protected String text;
    private int number;

    public Description(String text, int number){
        this.text = text;
        this.number = number;
    }

    public Description(String text){
        this(text, 0);
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return text;
    }
}
