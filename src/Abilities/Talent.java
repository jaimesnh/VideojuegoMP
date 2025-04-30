package Abilities;

import java.util.ArrayList;
import SystemGame.SystemGame;

public class Talent extends SpecialAbility{
    private int minimumRange;

    public Talent (String name, int attack, int defense){
        super(name, attack, defense);
    }

    public String toString() {
        return super.toString() ;
    }

    public static String[] listAvailableTalents() {
        String[] talents = new String[SystemGame.talentsAvailable.size()];
        for (int i = 0; i < SystemGame.talentsAvailable.size(); i++) {
            Talent talent = SystemGame.talentsAvailable.get(i);
            talents[i] = talent.toString();
        }

        return talents;
    }

    public static ArrayList<Talent> loadFromArray(String[][] talentsList) {
        ArrayList<Talent> talents = new ArrayList<>();
        for (String[] tlnt : talentsList) {
            talents.add(new Talent(tlnt[0], Integer.parseInt(tlnt[1]), Integer.parseInt(tlnt[2])));
        }
        return talents;
    }

}