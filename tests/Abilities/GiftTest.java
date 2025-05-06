package Abilities;

import SystemGame.SystemGame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiftTest {

    @Test
    void testToString() {
        Gift Gift = new Gift();

        Object[][] dataInput = { { "TestGift1", 1, 1, 1 }, { "               ", 2, 2, 2 }, { "***************", 3, 3, 3 }, { "TestGift4", 4, 4, 4 } };

        for (Object[] data : dataInput) {
            Gift = new Gift((String) data[0], (int) data[1], (int) data[2], (int) data[3]);

            assertEquals(data[0] + " (Ataque: " + data[1] + ", Defensa: " + data[2] + ") (rango mínimo: " + data[3] + ")", Gift.toString());
        }
    }

    @Test
    void listAvailableGifts() {
        String[][] GiftsArr1 = {
                { "TestGift1", "1", "1", "1" },
                { "TestGift2", "2", "2", "2" },
                { "TestGift3", "3", "3", "3" }
        };

        String[][] GiftsArr2 = {
                { "TestGift1", "1", "1", "1" },
                { "TestGift2", "2", "2", "2" },
                { "TestGift3", "3", "3", "3" },
                { "TestGift4", "4", "4", "4" }
        };

        String[][] GiftsArr3 = {
                { "TestGift1", "1", "1", "1" },
                { "TestGift2", "2", "2", "2" },
                { "TestGift3", "3", "3", "3" },
                { "TestGift4", "4", "4", "4" },
                { "TestGift5", "5", "5", "5" },
        };

        String[][] GiftsArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(GiftsArr1, GiftsArr2, GiftsArr3,GiftsArr4));

        for (String[][] GiftsArr : dataInput) {
            ArrayList<Gift> Gifts = Gift.loadFromArray(GiftsArr);
            SystemGame.giftsAvailable = Gifts;
            String[] availableGifts = Gift.listAvailableGifts();

            for (int i = 0; i < GiftsArr.length; i++) {
                assertEquals(GiftsArr[i][0] + " (Ataque: " + GiftsArr[i][1] + ", Defensa: " + GiftsArr[i][2] + ") (rango mínimo: " + GiftsArr[i][3] + ")", availableGifts[i]);
            }
        }
    }

    @Test
    void loadFromArray() {
        String[][] GiftsArr1 = { { "TestGift1", "1", "1", "1" }, { "TestGift2", "2", "2", "2" }, { "TestGift3", "3", "3", "3" } };

        String[][] GiftsArr2 = { { "TestGift1", "1", "1", "1" }, { "TestGift2", "2", "2", "2" }, { "TestGift3", "3", "3", "3" }, { "TestGift4", "4", "4", "4" } };

        String[][] GiftsArr3 = {
                { "TestGift1", "1", "1", "1" },
                { "TestGift2", "2", "2", "2" },
                { "TestGift3", "3", "3", "3" },
                { "TestGift4", "4", "4", "4" },
                { "TestGift5", "5", "5", "5" },
        };

        String[][] GiftsArr4 = new String[0][0];

        ArrayList<String[][]> dataInput = new ArrayList<>(List.of(GiftsArr1, GiftsArr2, GiftsArr3, GiftsArr4));

        for (String[][] GiftsArr : dataInput) {
            ArrayList<Gift> Gifts = Gift.loadFromArray(GiftsArr);

            for (int i = 0; i < GiftsArr.length; i++) {
                assertEquals(GiftsArr[i][0], Gifts.get(i).getName());
                assertEquals(Integer.parseInt(GiftsArr[i][1]), Gifts.get(i).getAttack());
                assertEquals(Integer.parseInt(GiftsArr[i][2]), Gifts.get(i).getDefense());
                assertEquals(Integer.parseInt(GiftsArr[i][3]), Gifts.get(i).getMinimumRange());
            }
        }
    }
    
}