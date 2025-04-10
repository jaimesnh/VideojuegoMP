package Character;

public class Weapon implements Equipment {
    private String name;
    private int attack;
    private boolean oneHand;

    public Weapon(String name, int attack, boolean oneHand) {
        if (attack < 1 || attack > 3) {
            throw new IllegalArgumentException("Attack value must be between 1 and 3");
        }
        this.name = name;
        this.attack = attack;
        this.oneHand = oneHand;
    }


    public String getName() {
        return name;
    }


    public int getAttack() {
        return attack;
    }


    public int getDefense() {
        return 0;
    }

    public boolean isOneHand() {
        return oneHand;
    }
}
