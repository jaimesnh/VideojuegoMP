package Character;

import Abilities.Discipline;
import Minions.Devil;
import Minions.Ghoul;
import Minions.Human;
import Minions.LoyaltyEnum;
import SystemGame.SystemGame;
import User.Player;
import Utils.TestingUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class VampireTest {

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
    void calcAttackPower() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        vampire.setBlood(5);
        assertEquals(2, vampire.calcAttackPower());
    }

    @Test
    void loadMinions() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        vampire.loadMinions();

        assertTrue(vampire.getMinionsHealth() > 0);
        assertNotNull(vampire.getMinions());
    }

    @Test
    void loadInitialValues() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        vampire.loadInitialValues();

        assertEquals(vampire.getHealth(), Vampire.MAX_HEALTH);
        assertEquals(vampire.getPower(), Vampire.MAX_POWER);
        assertEquals(2, vampire.getModifiers().length);
        assertEquals(vampire.getMinions().length, Vampire.INIT_MINIONS);
        assertEquals(3, vampire.getEquipment().length);
        assertEquals(0, vampire.getAge());
    }

    @Test
    void modifyAttributes() {
        TestingUtils.setInput(" ", "1", "-5", " ", "200", " ", "5");

        Vampire.modifyAttributes();

        assertEquals(200, Vampire.MAX_HEALTH);
    }

    @Test
    void alterAttr() {
        int opt = 2;
        int value = 5;

        TestingUtils.setInput(String.valueOf(value));

        Vampire.alterAttr(opt);

        assertEquals(value, Vampire.MAX_POWER);
    }

    @Test
    void showAttributes() {
        TestingUtils.setInput(" ");

        assertDoesNotThrow(Vampire::showAttributes);
    }

    @Test
    void successAtack() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        Vampire.MAX_BLOOD = 10;
        vampire.setBlood(5);

        vampire.successAtack();
        assertEquals(9, vampire.getBlood());
    }

    @Test
    void getAge() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        assertEquals(0, vampire.getAge());
    }

    @Test
    void setAge() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        vampire.setAge(5);
        assertEquals(5, vampire.getAge());
    }

    @Test
    void getBlood() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        assertEquals(Vampire.MIN_BLOOD, vampire.getBlood());
    }

    @Test
    void setBlood() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        vampire.setBlood(5);
        assertEquals(5, vampire.getBlood());
    }

    @Test
    void getDiscipline() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        assertNull(vampire.getDiscipline());
    }

    @Test
    void setDiscipline() {
        Vampire vampire = new Vampire(new Player("Jugador 1", "P1", "1234", "ID-F1"));
        vampire.setDiscipline(null);
        assertNull(vampire.getDiscipline());

        Discipline discipline = new Discipline("Disciplina 1", 1, 1, 1);
        vampire.setDiscipline(discipline);
        assertNotNull(vampire.getDiscipline());
        assertEquals(discipline, vampire.getDiscipline());
    }
}