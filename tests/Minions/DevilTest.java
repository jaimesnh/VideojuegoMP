package Minions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DevilTest {

    @Test
    void loadFromArray() {
        String[][] devilArr = {
                { "TestDevil1", "1", "Pacto1"},
                { "TestDevil2", "2", "Pacto2"},
                { "TestDevil3", "3", "Pacto3"}
        };


        ArrayList<Devil> devil_list = Devil.loadFromArray(devilArr);

        for (int i = 0; i < devilArr.length; i++) {
            assertEquals(devilArr[i][0], devil_list.get(i).getName());
            assertEquals(Integer.parseInt(devilArr[i][1]), devil_list.get(i).getHealth());
            assertEquals(devilArr[i][2], devil_list.get(i).getCovenant());
        }
    }

    @Test
    void getCovenant() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setCovenant("Pacto1");
        assertEquals("Pacto1", devil.getCovenant());
    }

    @Test
    void setCovenant() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setCovenant("Pacto2");
        assertEquals("Pacto2", devil.getCovenant());
    }

    @Test
    void getMinions() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setMinions(minions);
        assertEquals(minions, devil.getMinions());
    }

    @Test
    void setMinions() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setMinions(minions);
        assertEquals(minions, devil.getMinions());
    }

    @Test
    void getHealth() {
        Devil devil = new Devil();
        ArrayList<Minion> minions = new ArrayList<>();
        minions.add(new Ghoul("Minion1", 100, 10));
        minions.add(new Ghoul("Minion2", 200, 10));

        devil.setHealth(500);
        devil.setMinions(minions);
        assertEquals(800, devil.getHealth());
    }
}