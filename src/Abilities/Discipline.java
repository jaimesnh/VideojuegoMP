package Abilities;

import java.util.ArrayList;
import SystemGame.SystemGame;


public class Discipline extends SpecialAbility {
    private int cost;

    public Discipline(String name, int attack, int defense, int cost) {
        super(name, attack, defense);
        this.cost = cost;
    }

    public Discipline() {
        super();
    }

    public int abilityAttack(int blood) {
        if (blood >= this.cost) {
            return super.getAttack();
        } else {
            return 0;
        }
    }

    public String toString() {
        return super.toString() + String.format( " (" + "Coste: %d" + ")", cost);
    }

    public static String[] listAvailableDisciplines() {
        String[] disciplines = new String[SystemGame.disciplinesAvailable.size()];
        for (int i = 0; i < SystemGame.disciplinesAvailable.size(); i++) {
            Discipline discipline = SystemGame.disciplinesAvailable.get(i);
            disciplines[i] = discipline.toString();
        }

        return disciplines;
    }

    public static ArrayList<Discipline> loadFromArray(String[][] disciplinesList) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        for (String[] discip : disciplinesList) {
            disciplines.add(new Discipline(discip[0], Integer.parseInt(discip[1]), Integer.parseInt(discip[2]), Integer.parseInt(discip[3])));
        }
        return disciplines;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
