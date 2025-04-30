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

            String[] options = { "Alter Max Health", "Alter Max Power", "Alter Max Rage", "Alter Initial Minions", "Exit" };
            int opt = MenuUtils.menu("Modify Lycanthrope", options);

            // Alter the attribute selected by the user, if the user decides to exit, break the loop
            if (opt < options.length) {
                alterAttr(opt);
            } else {
                break;
            }
        }
    }

    public static void alterAttr(int opt) {
        String[] attributes = { "Max Health", "Max Power", "Max Rage", "Initial Minions" };
        String msg = "Enter the new value for the " + attributes[opt] + "(Positive Value)";
        int value = MenuUtils.readInt(msg, 0, 1000);

        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_RAGE = value;
            case 4 -> INIT_MINIONS = value;
        }
    }

    public static void showAttributes() {
        String[] attributes = { "Max Health: " + MAX_HEALTH, "Max Power: " + MAX_POWER, "Max Rage: " + MAX_RAGE, "Initial Minions: " + INIT_MINIONS };

        MenuUtils.doc("Lycanthrope Attributes", attributes);
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
