package Abilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialAbilityTest {

    @Test
    void testToString() {
        SpecialAbility ability = new Gift("BolaArdiente", 10, 5, 2);
        String expected = "BolaArdiente (Ataque: 10, Defensa: 5) (rango mínimo: 2)";
        assertEquals(expected, ability.toString());
    }

    @Test
    void abilityAttack() {
        SpecialAbility ability = new Gift("BolaArdiente", 10, 5, 10);
        int expected = 10;
        assertEquals(expected, ability.abilityAttack());
    }

    @Test
    void abilityDefense() {
        SpecialAbility ability = new Gift("BolaArdiente", 10, 5, 10);
        int expected = 5;
        assertEquals(expected, ability.abilityDefense());
    }
}