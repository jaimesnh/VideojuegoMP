package Equipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    @Test
    void getName() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getName().equals("TestArmor"));
    }

    @Test
    void setName() {
        Armor armor = new Armor("TestArmor", 100, 100);
        armor.setName("TestArmor2");
        assert(armor.getName().equals("TestArmor2"));
    }

    @Test
    void getAttack() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getAttack() == 100);
    }

    @Test
    void setAttack() {
        Armor armor = new Armor("TestArmor", 100, 100);
        armor.setAttack(200);
        assert(armor.getAttack() == 200);
    }

    @Test
    void getDefense() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.getDefense() == 100);
    }

    @Test
    void setDefense() {
        Armor armor = new Armor("TestArmor", 100, 100);
        armor.setDefense(200);
        assert(armor.getDefense() == 200);
    }

    @Test
    void testToString() {
        Armor armor = new Armor("TestArmor", 100, 100);
        assert(armor.toString().equals("TestArmor ( Ataque: 100, Defensa: 100 )"));
    }
}