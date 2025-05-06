package Equipment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void testToString() {
    }

    @Test
    void loadFromArray() {
        String[][] weaponArr1 = { { "TestWeapon", "1", "1", "1" }, { "TestWeapon2", "2", "2", "2" }, { "TestWeapon3", "3", "3", "3" } };

        ArrayList<Weapon> weapons = Weapon.loadFromArray(weaponArr1);

        for (int i = 0; i < weaponArr1.length; i++) {
            assertEquals(weaponArr1[i][0], weapons.get(i).getName());
            assertEquals(Integer.parseInt(weaponArr1[i][1]), weapons.get(i).getAttack());
            assertEquals(Integer.parseInt(weaponArr1[i][2]), weapons.get(i).getDefense());
            assertEquals(Integer.parseInt(weaponArr1[i][3]), weapons.get(i).getHandsRequired());
        }
    }

}