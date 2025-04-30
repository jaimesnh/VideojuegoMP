package Equipment;

public abstract class Equipment {
    private String name;
    private int attack;
    private int defense;

    public Equipment() {}

    public Equipment(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack(){
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String toString() {
        return this.name + " ( Ataque: " + this.attack + ", Defensa: " + this.defense + " )";
    }
}
