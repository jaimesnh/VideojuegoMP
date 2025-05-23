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
            String[] options = new String[]{"Modificar salud máxima", "Modificar poder máximo", "Modificar voluntad máxima", "Modificar esbirros iniciales", "Salir"};
            int opt = MenuUtils.menu("Modificar cazador", options);
            if (opt >= options.length) {
                return;
            }

            alterAttr(opt);
        }
    }

    public static void alterAttr(int opt) {
        String[] attributes = new String[]{"Salud máxima", "Poder máximo", "Voluntad Máxima", "Esbirros iniciales"};
        String msg = "Inserta el nuevo valor para " + attributes[opt] + "(Valor positivo)";
        int value = MenuUtils.readInt(msg, 0, 1000);
        switch (opt) {
            case 1 -> MAX_HEALTH = value;
            case 2 -> MAX_POWER = value;
            case 3 -> MAX_WILLPOWER = value;
            case 4 -> INIT_MINIONS = value;
        }

    }

    public static void showAttributes() {
        String[] attributes = new String[]{"Salud máxima: " + MAX_HEALTH, "Poder máximo: " + MAX_POWER, "Voluntad máxima: " + MAX_WILLPOWER, "Esbirros iniciales: " + INIT_MINIONS};
        MenuUtils.doc("Atributos del cazador", attributes);
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

