package Character;

import SystemGame.SystemGame;
import Abilities.Talent;
import Equipment.Equipment;
import Minions.Minion;
import User.Player;
import Utils.MenuUtils;

public class Hunter extends Character {
    private int willpower;
    private Talent talent;
    public static int MAX_WILLPOWER = 3;
    public static int MAX_HEALTH = 5;
    public static int MAX_POWER = 5;
    public static int MIN_POWER = 1;
    public static int INIT_MINIONS = 3;

    public Hunter(Player player) {
        super(player);
        this.loadMinions();
        this.talent = (Talent)this.special;
    }

    public void loadMinions() {
        int health = 0;
        Minion[] minions = new Minion[INIT_MINIONS];

        for(int i = 0; i < INIT_MINIONS; ++i) {
            int index = i % 3;
            if (index == 0) {
                minions[i] = (Minion)SystemGame.ghoulsAvailable.get(i % SystemGame.ghoulsAvailable.size());
            } else if (index == 1) {
                minions[i] = (Minion)SystemGame.humansAvailable.get(i % SystemGame.humansAvailable.size());
            } else {
                minions[i] = (Minion)SystemGame.devilsAvailable.get(i % SystemGame.devilsAvailable.size());
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
        this.setWillpower(MAX_WILLPOWER);
    }

    public static void modifyAttributes() {
        while(true) {
            showAttributes();
            String[] options = new String[]{"Alter Max Health", "Alter Max Power", "Alter Max Willpower", "Alter Initial Minions", "Exit"};
            int opt = MenuUtils.menu("Modify Hunter", options);
            if (opt >= options.length) {
                return;
            }

            alterAttr(opt);
        }
    }

    public static void alterAttr(int opt) {
        String[] attributes = new String[]{"Max Health", "Max Power", "Max Willpower", "Initial Minions"};
        String msg = "Enter the new value for the " + attributes[opt] + "(Positive Value)";
        int value = MenuUtils.readInt(msg, 0, 1000);
        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_WILLPOWER = value;
            case 4 -> INIT_MINIONS = value;
        }

    }

    public static void showAttributes() {
        String[] attributes = new String[]{"Max Health: " + MAX_HEALTH, "Max Power: " + MAX_POWER, "Max Willpower: " + MAX_WILLPOWER, "Initial Minions: " + INIT_MINIONS};
        MenuUtils.doc("Hunter Attributes", attributes);
    }

    public int getWillpower() {
        return this.willpower;
    }

    public void setWillpower(int willpower) {
        this.willpower = willpower;
    }

    public Talent getTalent() {
        return this.talent;
    }

    public void setTalent(Talent talent) {
        this.talent = talent;
    }
}

