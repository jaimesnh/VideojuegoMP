package Abilities;

public abstract class SpecialAbility {

    private String name;
    private int attack;
    private int defense;

    public SpecialAbility() {}

    public SpecialAbility(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;

    }
    public String toString() {
        return String.format("%s, ataque:%d, defensa:%d", name, attack, defense);
    }

    public int abilityAttack() {
        return this.attack;
    }

    public int abilityDefense() {
        return this.defense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
