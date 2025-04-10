package Character;

public class Armor implements Equipment {
    private String name;
    private int defense;

    public Armor(String name, int defense) {
        if (defense < 1 || defense > 3) {
            throw new IllegalArgumentException("Defense value must be between 1 and 3");
        }
        this.name = name;
        this.defense = defense;
    }


    public String getName() {
        return name;
    }


    public int getAttack() {
        return 0;
    }


    public int getDefense() {
        return defense;
    }
}
