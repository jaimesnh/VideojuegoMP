package Character;
import src.abilities.SpecialAbility;
import src.equipment.*;
import src.minions.*;
// Import statements
import src.modifiers.*;
import src.users.Player;

public abstract class Character {

    private String name;
    private int health;
    private int power;
    private Modifier[] modifiers;
    private Minion[] minions;
    private int minionsHealth;
    protected Equipment[] equipment;
    protected SpecialAbility special;
    Load initial
    values for
    the character

    public abstract void loadInitialValues();

    // CARGAR MINIOMS
    public abstract void loadMinions();

    // MODIFICAR ATRIBUTOS DE CHARACTER
    public static void modifyAttributes() {
    }

    public int getHit(Character attacker) {
        // COGER: PODER ATAQUE DE ATACANTE; PDOER DE DEFENSA DEL PERSONAJE
        int damage = attacker.getAttackPower();
        int defense = getDefensePower();

        // VALOR DE ATAQUE FINAL
        int finalAttackValue = damage - defense;

        // SI DAÑO ES NEGATIVO O CERO --> NO RECIBE DAÑO
        if (finalAttackValue <= 0) {
            return 0;
        }
        // QUITAR VIDA A LOS MINIOMS ANTES QUE AL PERSONAJE
        if (minionsHealth > 0) {
            if (minionsHealth >= finalAttackValue) {
                minionsHealth -= finalAttackValue;
                finalAttackValue = 0;
            } else {
                finalAttackValue -= minionsHealth;
                minionsHealth = 0;
            }
        }

        // SALUD RESTANTE DEL PERSONAJE DESPUES DE RECIVIR EL DAÑO
        int remainingHealth = health - finalAttackValue;

        // SI ES NEGATIVA LA SALUD, SALUD=0
        if (remainingHealth < 0) {
            health = 0;
        } else {
            health = remainingHealth;
        }

        // DAÑO RECIVIDO
        return finalAttackValue;
    }


    public boolean isDead() {
        return health == 0;
    }

    public void assignEquipment(Player player) {
        Equipment[] weapons = player.getWeapons();
        this.equipment[0] = weapons[0];
        this.equipment[1] = weapons[1];
        this.equipment[2] = player.getArmor();
    }

    public void assignModifiers(Player player) {
        this.modifiers = player.getModifiers();
    }

    public void assignSpecial(Player player) {
        this.special = player.getSpecialAbility();
    }

    // NUMERO ALEATORIO ENTRE 1 Y 6
    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    // PODER DE ATQUE DEL PEROSNAJE
    private int getAttackPower() {
        int success = 0;
        int attackPower = this.calcAttackPower();
        for (int i = 0; i < attackPower; i++) {
            int roll = this.rollDice();
            if (roll >= 5) {
                success++;
            }
        }

        return success;
    }

    // DEFENSA DEL PERSONAJE
    private int getDefensePower() {
        int success = 0;
        int defensePower = calcDefensePower();
        for (int i = 0; i < defensePower; i++) {
            int roll = rollDice();
            if (roll >= 5) {
                success++;
            }
        }

        return success;
    }

    // PODER DE ATAQUE DEL EQUIPO
    private int calcEquipmentAttack() {
        int cumPower = 0;
        for (Equipment e : this.equipment) {
            if (e == null) {
                continue;
            }
            cumPower += e.getAttack();
        }

        return cumPower;
    }

    // PODER DE DEFENSA DE LA DEFENSA
    private int calcEquipmentDefense() {
        int cumDefense = 0;
        for (Equipment e : this.equipment) {
            if (e == null) {
                continue;
            }
            cumDefense += e.getDefense();
        }

        return cumDefense;
    }

    // PODER DE ATAQUE DE LOS MODIFICADORES
    private int calcModifiersAttack() {
        int sum = 0;
        for (Modifier m : this.modifiers) {
            if (m == null) {
                continue;
            }
            if (m instanceof Strength) {
                Strength s = (Strength) m;
                sum += s.getEffectiveness();
            }
        }
        return sum;
    }

    // PODER DE ATAQUE DE LOS MODIFICADORES
    private int calcModifiersDefense() {
        int sum = 0;
        for (Modifier m : this.modifiers) {
            if (m == null) {
                continue;
            }
            if (m instanceof Weakness) {
                Weakness w = (Weakness) m;
                sum -= w.getSensitivity();
            }
        }
        return sum;
    }

    // PODER DE DEFENSA DE LOS MINIOMS
    private int calcMinionsDefense() {
        return this.minionsHealth;
    }

    // PODER DE ATAQUE DE LAS HABILIDADES ESPECIALES
    protected int calcSpecialAttack() {
        if (this.special == null) {
            return 0;
        }

        return this.special.abilityAttack();
    }

    protected int calcSpecialDefense() {
        if (this.special != null) {
            return 0;
        }

        return this.special.abilityDefense();
    }

    //PODER DE ATAQUE BASE DEL PERSONAJE
    protected int calcBaseAttackPower() {
        int cumAtt = 0;

        cumAtt += calcEquipmentAttack();
        cumAtt += calcModifiersAttack();
        cumAtt += calcSpecialAttack();

        return cumAtt;
    }

    // PODER DE DEFENSA BASE DEL PERSONAJE
    protected int calcBaseDefensePower() {
        int cumDef = 0;

        cumDef += calcEquipmentDefense();
        cumDef += calcModifiersDefense();
        cumDef += calcMinionsDefense();
        cumDef += calcSpecialDefense();
        return cumDef;
    }

    // PODER DE ATAQUE TOTAL DEL PERSONAJE
    protected int calcAttackPower() {
        return calcBaseAttackPower();
    }

    // PODER DE DEFENSA TOTAL DEL PERSONAJE
    protected int calcDefensePower() {
        return calcBaseDefensePower();
    }

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Modifier[] getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifier[] modifiers) {
        this.modifiers = modifiers;
    }

    public Minion[] getMinions() {
        return minions;
    }

    public void setMinions(Minion[] minions) {
        this.minions = minions;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment[] equipment) {
        this.equipment = equipment;
    }

    public SpecialAbility getSpecial() {
        return special;
    }

    public void setSpecial(SpecialAbility special) {
        this.special = special;
    }

    public int getMinionsHealth() {
        return minionsHealth;
    }

    public void setMinionsHealth(int minionsHealth) {
        this.minionsHealth = minionsHealth;
    }


    public Character(Player player) {
        this.loadInitialValues();
        // Assign player's weapons and armor to the character
        this.assignEquipment(player);
        this.assignModifiers(player);
        this.assignSpecial(player);
    }
}