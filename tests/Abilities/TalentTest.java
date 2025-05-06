package Abilities;

import SystemGame.SystemGame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TalentTest {

    @Test
    void testToString() {
        Talent talent = new Talent();

        Object[][] dataInput = {
                { "Test1", 1, 1 },
                { "     ", 2, 2 },
                { "*****", 3, 3 },
                { "Test4", 4, 4 }
        };

        for (Object[] data : dataInput) {
            talent = new Talent((String) data[0], (int) data[1], (int) data[2]);

            assertEquals(data[0] + " (Ataque: " + data[1] + ", Defensa: " + data[2] + ")", talent.toString());
        }
    }

    @Test
    void listAvailableTalents() {
        String[][] talentsArr1 = {
                { "Test1", "1", "1", "1" },
                { "Test2", "2", "2", "2" },
                { "Test3", "3", "3", "3" }
        };

        String[][] talentsArr2 = {
                { "Test1", "1", "1", "1" },
                { "Test2", "2", "2", "2" },
                { "Test3", "3", "3", "3" },
                { "Test4", "4", "4", "4" }
        };

        String[][] talentsArr3 = {
                { "Test1", "1", "1", "1" },
                { "Test2", "2", "2", "2" },
                { "Test3", "3", "3", "3" },
                { "Test4", "4", "4", "4" },
                { "Test5", "5", "5", "5" },
        };

        String[][] talentsArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(talentsArr1, talentsArr2, talentsArr3, talentsArr4));

        for (String[][] talentsArr : dataInput) {
            ArrayList<Talent> talents = Talent.loadFromArray(talentsArr);
            SystemGame.talentsAvailable = talents;
            String[] availabletalents = Talent.listAvailableTalents();

            for (int i = 0; i < talentsArr.length; i++) {
                assertEquals(
                        talentsArr[i][0] + " (Ataque: " + talentsArr[i][1] + ", Defensa: " + talentsArr[i][2] + ")",
                        availabletalents[i]
                );
            }
        }
    }

    @Test
    void loadFromArray() {
        String[][] talentsArr1 = {
                { "Test1", "1", "1", "1" },
                { "Test2", "2", "2", "2" },
                { "Test3", "3", "3", "3" }
        };

        String[][] talentsArr2 = {
                { "Test1", "1", "1", "1" },
                { "Test2", "2", "2", "2" },
                { "Test3", "3", "3", "3" },
                { "Test4", "4", "4", "4" }
        };

        String[][] talentsArr3 = {
                { "Test1", "1", "1", "1" },
                { "Test2", "2", "2", "2" },
                { "Test3", "3", "3", "3" },
                { "Test4", "4", "4", "4" },
                { "Test5", "5", "5", "5" },
        };

        String[][] talentsArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(talentsArr1, talentsArr2, talentsArr3, talentsArr4));

        for (String[][] talentsArr : dataInput) {
            ArrayList<Talent> talents = Talent.loadFromArray(talentsArr);

            for (int i = 0; i < talentsArr.length; i++) {
                assertEquals(talentsArr[i][0], talents.get(i).getName());
                assertEquals(Integer.parseInt(talentsArr[i][1]), talents.get(i).getAttack());
                assertEquals(Integer.parseInt(talentsArr[i][2]), talents.get(i).getDefense());
            }
        }
    }
}