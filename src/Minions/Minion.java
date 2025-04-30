package Minions;

public abstract class Minion {

    private String name;
    private int health;

    // Constructor
    public Minion(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // GETTERS/SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
