package Character;

import Abilities.Gift;
import Minions.*;
import SystemGame.SystemGame;
import static org.junit.jupiter.api.Assertions.*;

import User.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class LycanthropeTest {

    @BeforeEach
    public void setUp() {
        ArrayList<Ghoul> GhoulsAvailable = new ArrayList<>();
        GhoulsAvailable.add(new Ghoul("Ghoul 1", 1, 1));
        GhoulsAvailable.add(new Ghoul("Ghoul 2", 2, 2));
        GhoulsAvailable.add(new Ghoul("Ghoul 3", 3, 3));

        ArrayList<Human> HumansAvailable = new ArrayList<>();
        HumansAvailable.add(new Human("Humano 1", 1, LoyaltyEnum.ALTA));
        HumansAvailable.add(new Human("Humano 2", 2, LoyaltyEnum.BAJA));
        HumansAvailable.add(new Human("Humano 3", 3, LoyaltyEnum.NORMAL));

        ArrayList<Devil> devilsAvailable = new ArrayList<>();
        devilsAvailable.add(new Devil("Demonio 1", 1, "Pacto 1"));
        devilsAvailable.add(new Devil("Demonio 2", 2, "Pacto 2"));
        devilsAvailable.add(new Devil("Demonio 3", 3, "Pacto 3"));

        SystemGame.ghoulsAvailable = GhoulsAvailable;
        SystemGame.humansAvailable = HumansAvailable;
        SystemGame.devilsAvailable = devilsAvailable;
    }

    @Test
    public void testLycanthrope() {
        assertThrows(NullPointerException.class, () -> new Lycanthrope(null));

        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));

        assertNull(lycanthrope.getGift());
        assertTrue(lycanthrope.getMinionsHealth() > 0);
        assertNotNull(lycanthrope.getMinions());
    }

    @Test
    void loadMinions() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        lycanthrope.loadMinions();

        assertTrue(lycanthrope.getMinionsHealth() > 0);
        assertNotNull(lycanthrope.getMinions());
    }

    @Test
    void loadInitialValues() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        lycanthrope.loadInitialValues();

        assertEquals(lycanthrope.getHealth(), Lycanthrope.MAX_HEALTH);
        assertEquals(lycanthrope.getPower(), Lycanthrope.MAX_POWER);
        assertEquals(2, lycanthrope.getModifiers().length);
        assertEquals(lycanthrope.getMinions().length, Lycanthrope.INIT_MINIONS);
        assertEquals(3, lycanthrope.getEquipment().length);
        assertEquals(lycanthrope.getRage(), Lycanthrope.MAX_RAGE);
    }

    @Test
    void modifyAttributes() {
        tests.TestingUtils.setInput(" ", "1", "-5", " ", "200", " ", "5");

        Lycanthrope.modifyAttributes();

        assertEquals(200, Lycanthrope.MAX_HEALTH);
    }

    @Test
    void alterAttr() {
        int opt = 2;
        int value = 5;

        tests.TestingUtils.setInput(String.valueOf(value));

        Lycanthrope.alterAttr(opt);

        assertEquals(value, Lycanthrope.MAX_POWER);
    }

    @Test
    void showAttributes() {
        tests.TestingUtils.setInput(" ");

        assertDoesNotThrow(Lycanthrope::showAttributes);
    }

    @Test
    void getRage() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        lycanthrope.setRage(1);
        assertEquals(1, lycanthrope.getRage());
    }

    @Test
    void setRage() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        lycanthrope.setRage(1);
        assertEquals(1, lycanthrope.getRage());
    }

    @Test
    void getGift() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        assertNull(lycanthrope.getGift());
    }

    @Test
    void setGift() {
        Lycanthrope lycanthrope = new Lycanthrope(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        lycanthrope.setGift(new Gift());
        assertNotNull(lycanthrope.getGift());
    }
}