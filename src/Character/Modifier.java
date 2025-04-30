package Character;

import java.util.ArrayList;

public abstract class Modifier {

    private String name;

    // Constructor
    public Modifier(String name) {
        this.name = name;
    }


    public static ArrayList<Modifier> loadFromArray(String[][] strengths, String[][] weaknesses) {
        ArrayList<Modifier> modifiers = new ArrayList<>();
        for (String[] modifier : strengths) {
            modifiers.add(new Strength(modifier[0], Integer.parseInt(modifier[1])));
        }
        for (String[] modifier : weaknesses) {
            modifiers.add(new Weakness(modifier[0], Integer.parseInt(modifier[1])));
        }

        return modifiers;
    }

    public String toString() {
        return this.name;
    }

    // GETTERS/SETTERS
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}