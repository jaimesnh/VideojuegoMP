package Character;

import Abilities.*;
import User.Player;
import Minions.*;
import SystemGame.SystemGame;
import Utils.MenuUtils;
import Equipment.*;

public class Lycanthrope extends Character {

    private int rage;
    private Gift gift;

    public static int MAX_RAGE = 3;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    // Constructor
    public Lycanthrope(Player player) {
        super(player);
        rage = 0;
        this.loadMinions();
        this.gift = (Gift) this.special;
    }

    @Override
    public void loadMinions() {
        int health = 0;
        Minion[] minions = new Minion[INIT_MINIONS];

        for (int i = 0; i < INIT_MINIONS; i++) {
            int index = i % 3;
            if (index == 0) {
                minions[i] = SystemGame.ghoulsAvailable.get(i % SystemGame.ghoulsAvailable.size());
            } else if (index == 1) {
                minions[i] = SystemGame.humansAvailable.get(i % SystemGame.humansAvailable.size());
            } else {
                minions[i] = SystemGame.devilsAvailable.get(i % SystemGame.devilsAvailable.size());
            }

            health += minions[i].getHealth();
        }

        this.setMinionsHealth(health);
        this.setMinions(minions);
    }

    @Override
    public void loadInitialValues() {
        this.setHealth(MAX_HEALTH);
        this.setPower(MAX_POWER);
        this.setModifiers(new Modifier[2]);
        this.setMinions(new Minion[INIT_MINIONS]);
        this.setEquipment(new Equipment[3]);
        this.setRage(MAX_RAGE);
    }

    public static void modifyAttributes() {
        while (true) {
            showAttributes();

            String[] options = {"Modificar Salud Máxima", "Modificar Poder Máximo", "Modificar rabia Máxima", "Modificar Esbirros Iniciales", "Salir"};
            int opt = MenuUtils.menu("Modificar licántropo", options);

            // Alter the attribute selected by the user, if the user decides to exit, break the loop
            if (opt < options.length) {
                alterAttr(opt);
            } else {
                break;
            }
        }
    }

    public static void alterAttr(int opt) {
        String[] attributes = { "Salud Máxima", "Poder Máximo", "Ira Máxima", "Esbirros Iniciales"};
        String msg = "Introduce el nuevo valor para " + attributes[opt] + " (Valor positivo)";
        int value = MenuUtils.readInt(msg, 0, 1000);

        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_RAGE = value;
            case 4 -> INIT_MINIONS = value;
        }
    }

    public static void showAttributes() {
        String[] attributes = { "Salud Máxima: " + MAX_HEALTH, "Poder Máximo: " + MAX_POWER, "Rabia Máxima: " + MAX_RAGE, "Minions Iniciales: " + INIT_MINIONS };

        MenuUtils.doc("Atributos del licantropo", attributes);
    }

    // GETTERS/SETTERS
    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

}
