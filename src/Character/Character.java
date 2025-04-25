package Character;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {


    private String name;
    private SpecialAbility specialAbility;
    private List<Weapon> weapons;
    private Armor armor;
    private int gold;
    private int health;
    private List<Equipment> power;
    private List<Minion> minions;
    private List<Modifier> modifier;

    // Constructor
    public Character(String name, SpecialAbility specialAbility, List<Weapon> weapons,
                     Armor armor, int gold, int health) {
        this.name = name;
        this.specialAbility = specialAbility;
        this.weapons = weapons != null ? weapons : new ArrayList<>();
        this.armor = armor;
        this.gold = gold;
        this.health = health;
        this.power = new ArrayList<>();
        this.minions = new ArrayList<>();
        this.modifier = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public SpecialAbility getSpecialAbility() {
        return specialAbility;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public Armor getArmor() {
        return armor;
    }

    public int getGold() {
        return gold;
    }

    public List<Equipment> getPower() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public List<Minion> getMinions() {
        return minions;
    }

    public List<Modifier> getModifier() {
        return modifier;
    }
}
