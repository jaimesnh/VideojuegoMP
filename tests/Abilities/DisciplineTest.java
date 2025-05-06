package Abilities;

import SystemGame.SystemGame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisciplineTest {

    @Test
    void abilityAttack() {
        Discipline discipline = new Discipline();

        Object[][] dataInput = {
                { "Test1", 1, 1, 1 },
                { "     ", 2, 2, 2 },
                { "*****", 3, 3, 3 },
                { "Test4", 4, 4, 4 }
        };

        for (Object[] data : dataInput) {
            discipline = new Discipline((String) data[0], (int) data[1], (int) data[2], (int) data[3]);
            int blood = 3;

            if (blood >= (int) data[3]) {
                assertEquals(data[1], discipline.abilityAttack(blood));
            } else {
                assertEquals(0, discipline.abilityAttack(blood));
            }
        }
    }

    @Test
    void testToString() {
        Discipline discipline = new Discipline();

        Object[][] dataInput = {
                { "Test1", 1, 1, 1 },
                { "     ", 2, 2, 2 },
                { "*****", 3, 3, 3 },
                { "Test4", 4, 4, 4 }
        };

        for (Object[] data : dataInput) {
            discipline = new Discipline((String) data[0], (int) data[1], (int) data[2], (int) data[3]);

            assertEquals(data[0] + " (Ataque: " + data[1] + ", Defensa: " + data[2] + ") (Coste: " + data[3] + ")", discipline.toString());
        }
    }

    @Test
    void listAvailableDisciplines() {
        String[][] disciplinesArr1 = {
                { "TestDiscipline1", "1", "1", "1" },
                { "TestDiscipline2", "2", "2", "2" },
                { "TestDiscipline3", "3", "3", "3" }
        };

        String[][] disciplinesArr2 = {
                { "TestDiscipline1", "1", "1", "1" },
                { "TestDiscipline2", "2", "2", "2" },
                { "TestDiscipline3", "3", "3", "3" },
                { "TestDiscipline4", "4", "4", "4" }
        };

        String[][] disciplinesArr3 = {
                { "TestDiscipline1", "1", "1", "1" },
                { "TestDiscipline2", "2", "2", "2" },
                { "TestDiscipline3", "3", "3", "3" },
                { "TestDiscipline4", "4", "4", "4" },
                { "TestDiscipline5", "5", "5", "5" },
        };

        String[][] disciplinesArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(disciplinesArr1, disciplinesArr2, disciplinesArr3, disciplinesArr4));

        for (String[][] disciplinesArr : dataInput) {
            ArrayList<Discipline> disciplines = Discipline.loadFromArray(disciplinesArr);
            SystemGame.disciplinesAvailable = disciplines;
            String[] availableDisciplines = Discipline.listAvailableDisciplines();

            for (int i = 0; i < disciplinesArr.length; i++) {
                assertEquals(
                        disciplinesArr[i][0] + " (Ataque: " + disciplinesArr[i][1] + ", Defensa: " + disciplinesArr[i][2] + ") (Coste: " + disciplinesArr[i][3] + ")",
                        availableDisciplines[i]
                );
            }
        }
    }

    @Test
    void loadFromArray() {
        String[][] disciplinesArr1 = {
                { "TestDiscipline1", "1", "1", "1" },
                { "TestDiscipline2", "2", "2", "2" },
                { "TestDiscipline3", "3", "3", "3" }
        };

        String[][] disciplinesArr2 = {
                { "TestDiscipline1", "1", "1", "1" },
                { "TestDiscipline2", "2", "2", "2" },
                { "TestDiscipline3", "3", "3", "3" },
                { "TestDiscipline4", "4", "4", "4" }
        };

        String[][] disciplinesArr3 = {
                { "TestDiscipline1", "1", "1", "1" },
                { "TestDiscipline2", "2", "2", "2" },
                { "TestDiscipline3", "3", "3", "3" },
                { "TestDiscipline4", "4", "4", "4" },
                { "TestDiscipline5", "5", "5", "5" },
        };

        String[][] disciplinesArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(disciplinesArr1, disciplinesArr2, disciplinesArr3, disciplinesArr4));

        for (String[][] disciplinesArr : dataInput) {
            ArrayList<Discipline> disciplines = Discipline.loadFromArray(disciplinesArr);

            for (int i = 0; i < disciplinesArr.length; i++) {
                assertEquals(disciplinesArr[i][0], disciplines.get(i).getName());
                assertEquals(Integer.parseInt(disciplinesArr[i][1]), disciplines.get(i).getAttack());
                assertEquals(Integer.parseInt(disciplinesArr[i][2]), disciplines.get(i).getDefense());
                assertEquals(Integer.parseInt(disciplinesArr[i][3]), disciplines.get(i).getCost());
            }
        }
    }
}