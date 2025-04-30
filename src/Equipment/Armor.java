package Equipment;

import java.util.ArrayList;

public class Armor extends Equipment{

    public Armor(String name, int attackModifier, int defenseModifier) {
        super(name, attackModifier, defenseModifier);
    }

    public static ArrayList<Armor> loadFromArray(String[][] armorsArray) {
        ArrayList<Armor> armors = new ArrayList();

        for(String[] armor : armorsArray) {
            armors.add(new Armor(armor[0], Integer.parseInt(armor[1]), Integer.parseInt(armor[2])));
        }

        return armors;
    }

}
