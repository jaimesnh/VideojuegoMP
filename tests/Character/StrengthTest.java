package Character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrengthTest {

    @Test
    public void Strength() {
        Strength strength = new Strength();
        assertEquals(null, strength.getName());
        assertEquals(0, strength.getPower());
    }

    @Test
    void testToString() {
        Strength strength = new Strength();

        Object[][] dataInput = {
                { "Test1", 1 },
                { "               ", 2 },
                { "***************", 3 },
                { "Test4", 4 }
        };

        for (Object[] data : dataInput) {
            strength = new Strength((String) data[0], (int) data[1]);
            assertEquals(String.format("%s (+%d)", data[0], data[1]), strength.toString());
        }
    }

    @Test
    void getPower() {
        Strength strength = new Strength("Test", 1);
        assertEquals(1, strength.getPower());
    }

    @Test
    void setPower() {
        Strength strength = new Strength("Test", 1);
        strength.setPower(2);
        assertEquals(2, strength.getPower());
    }
}