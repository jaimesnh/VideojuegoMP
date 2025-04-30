package Equipment;

import java.util.ArrayList;

public class Weapon extends Equipment {
    private int handsRequired;

    public Weapon(int handsRequired, String name, int attack, int defense) {
        super(name, attack, defense);
        this.handsRequired = handsRequired;
    }

    public String toString() {
        String a = super.toString();
        return a + " (Manos necesarias: " + this.handsRequired + ")";
    }

    public static ArrayList<Weapon> loadFromArray(String[][] arr) {
        ArrayList<Weapon> weapons = new ArrayList();

        for(String[] weapon : arr) {
            weapons.add(new Weapon(weapon[0], Integer.parseInt(weapon[1]), Integer.parseInt(weapon[2]), Integer.parseInt(weapon[3])));
        }

        return weapons;
    }

    public int getHandsRequired() {
        return this.handsRequired;
    }

    public void setHandsRequired(int handsRequired) {
        this.handsRequired = handsRequired;
    }
}
