package Character;

import Abilities.Talent;
import Minions.*;
import SystemGame.SystemGame;
import User.Player;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class HunterTest {

    @BeforeEach
    public void setUp() {
        ArrayList<Ghoul> ghoulsAvailable = new ArrayList<>();
        ghoulsAvailable.add(new Ghoul("Ghoul 1", 1, 1));
        ghoulsAvailable.add(new Ghoul("Ghoul 2", 2, 2));
        ghoulsAvailable.add(new Ghoul("Ghoul 3", 3, 3));

        ArrayList<Human> humansAvailable = new ArrayList<>();
        humansAvailable.add(new Human("Humano 1", 1, LoyaltyEnum.ALTA));
        humansAvailable.add(new Human("Humano 2", 2, LoyaltyEnum.BAJA));
        humansAvailable.add(new Human("Humano 3", 3, LoyaltyEnum.NORMAL));

        ArrayList<Devil> devilsAvailable = new ArrayList<>();
        devilsAvailable.add(new Devil("Demonio 1", 1, "Pacto 1"));
        devilsAvailable.add(new Devil("Demonio 2", 2, "Pacto 2"));
        devilsAvailable.add(new Devil("Demonio 3", 3, "Pacto 3"));

        SystemGame.ghoulsAvailable = ghoulsAvailable;
        SystemGame.humansAvailable = humansAvailable;
        SystemGame.devilsAvailable = devilsAvailable;
    }

    @Test
    public void Hunter() {
        assertThrows(NullPointerException.class, () -> new Hunter(null));

        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));

        assertNull(hunter.getTalent());
        assertTrue(hunter.getMinionsHealth() > 0);
        assertNotNull(hunter.getMinions());
    }

    @Test
    void loadMinions() {
        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        hunter.loadMinions();

        assertTrue(hunter.getMinionsHealth() > 0);
        assertNotNull(hunter.getMinions());
    }

    @Test
    void loadInitialValues() {
        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        hunter.loadInitialValues();

        assertEquals(hunter.getHealth(), Hunter.MAX_HEALTH);
        assertEquals(hunter.getPower(), Hunter.MAX_POWER);
        assertEquals(2, hunter.getModifiers().length);
        assertEquals(hunter.getMinions().length, Hunter.INIT_MINIONS);
        assertEquals(3, hunter.getEquipment().length);
        assertEquals(hunter.getWillpower(), Hunter.MAX_WILLPOWER);
    }

    @Test
    void modifyAttributes() {
        tests.TestingUtils.setInput(" ", "1", "-5", " ", "200", " ", "5");

        Hunter.modifyAttributes();

        assertEquals(200, Hunter.MAX_HEALTH);
    }

    @Test
    void alterAttr() {
        int opt = 2;
        int value = 5;

        tests.TestingUtils.setInput(String.valueOf(value));

        // Call the method
        Hunter.alterAttr(opt);

        // Assert the attributes are modified correctly
        assertEquals(value, Hunter.MAX_POWER);
    }

    @Test
    void showAttributes() {
        tests.TestingUtils.setInput(" ");

        assertDoesNotThrow(Hunter::showAttributes);
    }

    @Test
    void getWillpower() {
        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        assertEquals(Hunter.MAX_WILLPOWER, hunter.getWillpower());
    }

    @Test
    void setWillpower() {
        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        hunter.setWillpower(5);
        assertEquals(5, hunter.getWillpower());
    }

    @Test
    void getTalent() {
        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        assertNull(hunter.getTalent());
    }

    @Test
    void setTalent() {
        Hunter hunter = new Hunter(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        hunter.setTalent(new Talent());
        assertNotNull(hunter.getTalent());
    }
}