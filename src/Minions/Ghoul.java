package Minions;

import java.util.ArrayList;

public class Ghoul extends Minion {

    private int dependency;

    public Ghoul() {
        super();
    }

    // Constructor
    public Ghoul(String name, int health, int dependency) {
        super(name, health);
        this.dependency = dependency;
    }

    public static ArrayList<Ghoul> loadFromArray(String[][] ghoulsList) {
        ArrayList<Ghoul> ghouls = new ArrayList<>();
        for (String[] ghoul : ghoulsList) {
            ghouls.add(new Ghoul(ghoul[0], Integer.parseInt(ghoul[1]), Integer.parseInt(ghoul[2])));
        }
        return ghouls;
    }


    // GETTERS/SETTERS
    public int getDependency() {
        return dependency;
    }

    public void setDependency(int dependency) {
        this.dependency = dependency;
    }


}
