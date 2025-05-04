package Character;

import Abilities.*;
import User.Player;
import Minions.*;
import SystemGame.SystemGame;
import Utils.MenuUtils;
import Equipment.*;

public class Vampire extends Character {

    private int age;
    private int blood;
    private Discipline discipline;

    public static int MAX_BLOOD = 10;
    public static int MIN_BLOOD = 0;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    // Constructor
    public Vampire(Player player) {
        super(player);
        this.loadMinions();
        this.discipline = (Discipline) this.special;
    }

    public int calcAttackPower() {
        if (blood >= 5) {
            return calcBaseAttackPower() + 2;
        } else {
            return calcBaseAttackPower();
        }
    }

    public void loadMinions() {
        int health = 0;
        Minion[] minions = new Minion[INIT_MINIONS];

        for (int i = 0; i < INIT_MINIONS; i++) {
            int index = i % 2;
            if (index == 0) {
                minions[i] = SystemGame.ghoulsAvailable.get(i % SystemGame.ghoulsAvailable.size());
            } else {
                minions[i] = SystemGame.devilsAvailable.get(i % SystemGame.devilsAvailable.size());
            }

            health += minions[i].getHealth();
        }

        this.setMinionsHealth(health);
        this.setMinions(minions);
    }

    public void loadInitialValues() {
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);
        this.setAge(0);
        this.setBlood(MIN_BLOOD);
    }

    protected int calcSpecialAttack() {
        if (this.discipline == null) {
            return 0;
        }

        if (this.blood >= this.discipline.getCost()) {
            int value = this.discipline.abilityAttack(blood);
            this.blood -= this.discipline.getCost();
            return value;
        } else {
            return 0;
        }
    }

    public static void modifyAttributes() {
        while (true) {
            showAttributes();

            String[] options = { "Modificar Salud Máxima", "Modificar Poder Máximo", "Modificar Sangre Máxima", "Modificar Esbirros Iniciales", "Salir" };
            int opt = MenuUtils.menu("Modificar vampiro", options);

            if (opt < options.length) {
                alterAttr(opt);
            } else {
                break;
            }
        }
    }

    public static void alterAttr(int opt) {
        String[] attributes = { "Salud Máxima", "Poder Máximo", "Ira Máxima", "Esbirros Iniciales" };
        String msg = "Introduce el nuevo valor para " + attributes[opt] + " (Valor positivo)";
        int value = MenuUtils.readInt(msg, 0, 1000);

        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_BLOOD = value;
            case 4 -> INIT_MINIONS = value;
        }
    }

    public static void showAttributes() {
        String[] attributes = { "Salud Máxima : " + MAX_HEALTH, "Poder Máximo : " + MAX_POWER, "Sangre Máxima : " + MAX_BLOOD, "Minions iniciales: " + INIT_MINIONS};

        MenuUtils.doc("Atributos del Vampiro", attributes);
    }

    public void successAtack() {
        this.blood += 4;

        if (this.blood > MAX_BLOOD) {
            this.blood = MAX_BLOOD;
        }
    }

    // GETTERS/SETTERS
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

}
