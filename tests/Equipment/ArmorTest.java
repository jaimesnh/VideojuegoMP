package Equipment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    @Test
    public void Armor() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assertEquals("TestArmor", armor.getName());
        assertEquals(100, armor.getAttack());
        assertEquals(100, armor.getDefense());
    }

    @Test
    void loadFromArray() {
        String[][] ArmorArr1 = { { "Test1", "1", "1" }, { "Test2", "2", "2" }, { "Test3", "3", "3" } };

        String[][] ArmorArr2 = { { "Test1", "1", "1" }, { "Test2", "2", "2" }, { "Test3", "3", "3" }, { "Test4", "4", "4" } };

        String[][] ArmorArr3 = { { "Test1", "1", "1" }, { "Test2", "2", "2" }, { "Test3", "3", "3" }, { "Test4", "4", "4" }, { "Test5", "5", "5" } };

        String[][] ArmorArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(ArmorArr1, ArmorArr2, ArmorArr3, ArmorArr4));

        for (String[][] armorArr : dataInput) {
            ArrayList<Armor> armors = Armor.loadFromArray(armorArr);

            for (int i = 0; i < armorArr.length; i++) {
                assertEquals(armorArr[i][0], armors.get(i).getName());
                assertEquals(Integer.parseInt(armorArr[i][1]), armors.get(i).getAttack());
                assertEquals(Integer.parseInt(armorArr[i][2]), armors.get(i).getDefense());
            }
        }
    }
}