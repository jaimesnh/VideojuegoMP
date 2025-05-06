package Character;


import Abilities.Gift;
import Abilities.Talent;
import Equipment.Armor;
import Equipment.Equipment;
import Minions.Devil;
import Minions.Ghoul;
import Minions.Human;
import Minions.LoyaltyEnum;
import SystemGame.SystemGame;

import User.Player;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


class CharacterTest {

    @BeforeEach
    void setUp() {
        ArrayList<Ghoul> ghoulsAvailable = new ArrayList<>();
        ghoulsAvailable.add(new Ghoul("Ghoul 1", 1, 1));
        ghoulsAvailable.add(new Ghoul("Ghoul 2", 2, 2));
        ghoulsAvailable.add(new Ghoul("Ghoul 3", 3, 3));

        ArrayList<Human> humansAvailable = new ArrayList<>();
        humansAvailable.add(new Human("Human 1", 1, LoyaltyEnum.ALTA));
        humansAvailable.add(new Human("Human 2", 2, LoyaltyEnum.BAJA));
        humansAvailable.add(new Human("Human 3", 3, LoyaltyEnum.NORMAL));

        ArrayList<Devil> devilsAvailable = new ArrayList<>();
        devilsAvailable.add(new Devil("Devil 1", 1, "Covenant 1"));
        devilsAvailable.add(new Devil("Devil 2", 2, "Covenant 2"));
        devilsAvailable.add(new Devil("Devil 3", 3, "Covenant 3"));

        SystemGame.ghoulsAvailable = ghoulsAvailable;
        SystemGame.humansAvailable = humansAvailable;
        SystemGame.devilsAvailable = devilsAvailable;
    }

    @Test
    @Disabled
    void loadMinions() {}

    @Test
    @Disabled
    void modifyAttributes() {}

    @Test
    void getHit() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Player player2 = new Player("Jugador 2", "P2", "1234", "ID-F2");
        Hunter c1 = new Hunter(player);
        Lycanthrope c2 = new Lycanthrope(player2);
        c1.setSpecial(new Talent());
        c2.setSpecial(new Gift());

        c1.getHit(c2);
        assertEquals(5, c1.getHealth());
    }

    @Test
    void isDead() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        // Player is alive
        assertFalse(c1.isDead());

        // Player is dead
        c1.setHealth(0);
        assertTrue(c1.isDead());
    }

    @Test
    void assignEquipment() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Equipment e1 = new Armor("Armadura 1", 1, 1);
        Equipment e2 = new Armor("Armadura 2", 2, 2);
        Equipment e3 = new Armor("Armadura 3", 3, 3);

        c1.setEquipment(new Equipment[] {e1, e2, e3});

        // Checking if equipment was assigned correctly
        assertEquals(3, c1.getEquipment().length);
        assertEquals(e1, c1.getEquipment()[0]);
        assertEquals(e2, c1.getEquipment()[1]);
        assertEquals(e3, c1.getEquipment()[2]);
    }

    @Test
    void assignModifiers() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Strength[] modifiers = new Strength[] {
                new Strength("Fuerza 1", 1),
        };

        // Checking if modifiers were assigned correctly
        c1.setModifiers(modifiers);

        assertEquals(1, c1.getModifiers().length);
        assertEquals(modifiers[0], c1.getModifiers()[0]);
    }

    @Test
    void assignSpecial() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Talent special = new Talent();

        // Checking if special was assigned correctly
        c1.setSpecial(special);

        assertEquals(special, c1.getSpecial());
    }

    @Test
    void getName() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        c1.setName("Cazador");
        assertEquals("Cazador", c1.getName());
    }

    @Test
    void setName() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        c1.setName("Cazador");
        assertEquals("Cazador", c1.getName());
    }

    @Test
    void getHealth() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        c1.setHealth(10);
        assertEquals(10, c1.getHealth());
    }

    @Test
    void setHealth() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        c1.setHealth(10);
        assertEquals(10, c1.getHealth());
    }

    @Test
    void getPower() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        c1.setPower(10);
        assertEquals(10, c1.getPower());
    }

    @Test
    void setPower() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        c1.setPower(10);
        assertEquals(10, c1.getPower());
    }

    @Test
    void getModifiers() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Strength[] modifiers = new Strength[] {
                new Strength("Fuerza 1", 1),
                new Strength("Fuerza 2", 2),
                new Strength("Fuerza 3", 3),
        };

        c1.setModifiers(modifiers);

        // Checking if modifiers were assigned correctly
        assertEquals(3, c1.getModifiers().length);
        assertEquals(modifiers[0], c1.getModifiers()[0]);
        assertEquals(modifiers[1], c1.getModifiers()[1]);
        assertEquals(modifiers[2], c1.getModifiers()[2]);
    }

    @Test
    void setModifiers() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Strength[] modifiers = new Strength[] {
                new Strength("Fuerza 1", 1),
                new Strength("Fuerza 2", 2),
                new Strength("Fuerza 3", 3),
        };

        // Checking if modifiers were assigned correctly
        c1.setModifiers(modifiers);

        assertEquals(3, c1.getModifiers().length);
        assertEquals(modifiers[0], c1.getModifiers()[0]);
        assertEquals(modifiers[1], c1.getModifiers()[1]);
        assertEquals(modifiers[2], c1.getModifiers()[2]);
    }

    @Test
    void getMinions() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Ghoul[] minions = new Ghoul[] {
                new Ghoul("Ghoul 1", 1, 1),
                new Ghoul("Ghoul 2", 2, 2),
                new Ghoul("Ghoul 3", 3, 3),
        };

        c1.setMinions(minions);

        // Checking if minions were assigned correctly
        assertEquals(3, c1.getMinions().length);
        assertEquals(minions[0], c1.getMinions()[0]);
        assertEquals(minions[1], c1.getMinions()[1]);
        assertEquals(minions[2], c1.getMinions()[2]);
    }

    @Test
    void setMinions() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Ghoul[] minions = new Ghoul[] {
                new Ghoul("Ghoul 1", 1, 1),
                new Ghoul("Ghoul 2", 2, 2),
                new Ghoul("Ghoul 3", 3, 3),
        };

        // Checking if minions were assigned correctly
        c1.setMinions(minions);

        assertEquals(3, c1.getMinions().length);
        assertEquals(minions[0], c1.getMinions()[0]);
        assertEquals(minions[1], c1.getMinions()[1]);
        assertEquals(minions[2], c1.getMinions()[2]);
    }

    @Test
    void getEquipment() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Equipment[] equipment = new Equipment[] {
                new Armor("Armadura 1", 1, 1),
                new Armor("Armadura 2", 2, 2),
                new Armor("Armadura 3", 3, 3),
        };

        c1.setEquipment(equipment);

        // Checking if equipment was assigned correctly
        assertEquals(3, c1.getEquipment().length);
        assertEquals(equipment[0], c1.getEquipment()[0]);
        assertEquals(equipment[1], c1.getEquipment()[1]);
        assertEquals(equipment[2], c1.getEquipment()[2]);
    }

    @Test
    void setEquipment() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Equipment[] equipment = new Equipment[] {
                new Armor("Armadura 1", 1, 1),
                new Armor("Armadura 2", 2, 2),
                new Armor("Armadura 3", 3, 3),
        };

        // Checking if equipment was assigned correctly
        c1.setEquipment(equipment);

        assertEquals(3, c1.getEquipment().length);
        assertEquals(equipment[0], c1.getEquipment()[0]);
        assertEquals(equipment[1], c1.getEquipment()[1]);
        assertEquals(equipment[2], c1.getEquipment()[2]);
    }

    @Test
    void getSpecial() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Talent special = new Talent();

        c1.setSpecial(special);

        assertEquals(special, c1.getSpecial());
    }

    @Test
    void setSpecial() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Talent special = new Talent();

        c1.setSpecial(special);

        assertEquals(special, c1.getSpecial());
    }

    @Test
    void getMinionsHealth() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);
        Ghoul[] minions = new Ghoul[] {
                new Ghoul("Ghoul 1", 1, 1),
                new Ghoul("Ghoul 2", 2, 2),
                new Ghoul("Ghoul 3", 3, 3),
        };

        c1.setMinions(minions);

        // Checking if minions health was calculated correctly
        assertEquals(6, c1.getMinionsHealth());
    }

    @Test
    void setMinionsHealth() {
        Player player = new Player("Jugador 1", "P1", "1234", "ID-F1");
        Hunter c1 = new Hunter(player);

        // Checking if minions health was calculated correctly
        c1.setMinionsHealth(6);
        assertEquals(6, c1.getMinionsHealth());
    }
}