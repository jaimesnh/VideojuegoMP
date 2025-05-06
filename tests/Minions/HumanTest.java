package Minions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    @Test
    public void testHuman() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.ALTA);
        assertEquals("TestHuman", human.getName());
        assertEquals(100, human.getHealth());
        assertEquals(LoyaltyEnum.ALTA, human.getLoyalty());
    }

    @Test
    void loadFromArray() {

        String[][] humanArr1 = {
                { "TestHuman1", "1", "ALTA"},
                { "TestHuman2", "2", "NORMAL"},
                { "TestHuman3", "3", "BAJA"} };

        String[][] humanArr2 = {
                { "TestHuman1", "1", "ALTA" },
                { "TestHuman2", "2", "NORMAL"},
                { "TestHuman3", "3", "BAJA" },
                { "TestHuman4", "4", "BAJA" } };

        String[][] humanArr3 = {
                { "TestHuman1", "1", "ALTA"  },
                { "TestHuman2", "2", "NORMAL" },
                { "TestHuman3", "3", "BAJA"  },
                { "TestHuman4", "4", "BAJA" },
                { "TestHuman5", "5", "ALTA" },
        };

        String[][] humanArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(humanArr1, humanArr2, humanArr3, humanArr4));

        for (String[][] humanArr : dataInput) {
            ArrayList<Human> human = Human.loadFromArray(humanArr);

            for (int i = 0; i < humanArr.length; i++) {
                assertEquals(humanArr[i][0], human.get(i).getName());
                assertEquals(Integer.parseInt(humanArr[i][1]), human.get(i).getHealth());
                assertEquals(LoyaltyEnum.valueOf(humanArr[i][2]), human.get(i).getLoyalty());

            }
        }
    }
}