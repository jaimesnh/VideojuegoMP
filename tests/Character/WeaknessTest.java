package Character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaknessTest {

    @Test
    public void Weakness() {
        Weakness weakness = new Weakness();
        assertEquals(null, weakness.getName());
        assertEquals(0, weakness.getSensitivity());
    }

    @Test
    void testToString() {
        Weakness weakness = new Weakness();

        Object[][] dataInput = {
                { "Test1", 1 },
                { "               ", 2 },
                { "***************", 3 },
                { "Test4", 4 }
        };

        for (Object[] data : dataInput) {
            weakness = new Weakness((String) data[0], (int) data[1]);
            assertEquals(String.format("%s (-%d)", data[0], data[1]), weakness.toString());
        }
    }

    @Test
    void getSensitivity() {
        Weakness weakness = new Weakness("Test", 1);
        assertEquals(1, weakness.getSensitivity());
    }

    @Test
    void setSensitivity() {
        Weakness weakness = new Weakness("Test", 1);
        weakness.setSensitivity(2);
        assertEquals(2, weakness.getSensitivity());
    }
}