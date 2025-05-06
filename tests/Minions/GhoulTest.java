package Minions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GhoulTest {

    @Test
    public void TestGhoul() {
        Ghoul ghoul = new Ghoul("TestGhoul", 100, 10);
        assertEquals("TestGhoul", ghoul.getName());
        assertEquals(100, ghoul.getHealth());
        assertEquals(10, ghoul.getDependency());
    }

    @Test
    void loadFromArray() {

        String[][] ghoulArr1 = {
                { "TestGhoul1", "1", "4" },
                { "TestGhoul2", "2", "3" },
                { "TestGhoul3", "3", "3" } };

        String[][] ghoulArr2 = {
                { "TestGhoul1", "1", "2" },
                { "TestGhoul2", "2", "1" },
                { "TestGhoul3", "3", "2" },
                { "TestGhoul4", "4", "6" } };

        String[][] ghoulArr3 = {
                { "TestGhoul1", "1", "3" },
                { "TestGhoul2", "2", "4" },
                { "TestGhoul3", "3", "6" },
                { "TestGhoul4", "4", "8" },
                { "TestGhoul5", "5", "4" } };

        String[][] ghoulArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(ghoulArr1, ghoulArr2, ghoulArr3, ghoulArr4));

        for (String[][] ghoulArr : dataInput) {
            ArrayList<Ghoul> ghoul = Ghoul.loadFromArray(ghoulArr);

            for (int i = 0; i < ghoulArr.length; i++) {
                assertEquals(ghoulArr[i][0], ghoul.get(i).getName());
                assertEquals(Integer.parseInt(ghoulArr[i][1]), ghoul.get(i).getHealth());
                assertEquals(Integer.parseInt(ghoulArr[i][2]), ghoul.get(i).getDependency());

            }
        }
    }
}