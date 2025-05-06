package Character;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModifierTest {

    @Test
    void loadFromArray() {
        String[][] strengthArr1 = {
                { "TestStrength1", "1" },
                { "TestStrength2", "2" },
                { "TestStrength3", "3" }
        };

        String[][] strengthArr2 = new String[0][0];

        String[][] weaknessArr1 = {
                { "TestWeakness1", "1" },
                { "TestWeakness2", "2" },
                { "TestWeakness3", "3" }
        };
        String[][] weaknessArr2 = {
                { "TestWeakness", "1" },
                { "TestWeakness", "3" },
                { "Test", "2" }
        };

        ArrayList<String[][]> strengthDataInput = new ArrayList<>(List.of(strengthArr1, strengthArr2));
        ArrayList<String[][]> weaknessDataInput = new ArrayList<>(List.of(weaknessArr1,weaknessArr2));

        for (int j = 0; j < strengthDataInput.size(); j++) {
            ArrayList<Modifier> modifiers = Modifier.loadFromArray(strengthDataInput.get(j), weaknessDataInput.get(j));

            for (int i = 0; i < modifiers.size(); i++) {
                if (i < strengthDataInput.get(j).length) {
                    assertEquals(strengthDataInput.get(j)[i][0], modifiers.get(i).getName());
                } else {
                    assertEquals(weaknessDataInput.get(j)[i - strengthDataInput.get(j).length][0], modifiers.get(i).getName());
                }
            }
        }
    }
}