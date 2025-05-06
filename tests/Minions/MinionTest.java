package Minions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinionTest {

    @Test
    void getName() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.ALTA);
        assertEquals("TestHuman", human.getName());
    }

    @Test
    void setName() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.ALTA);
        human.setName("TestHuman2");
        assertEquals("TestHuman2", human.getName());
    }

    @Test
    void getHealth() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.ALTA);
        assertEquals(100, human.getHealth());
    }

    @Test
    void setHealth() {
        Human human = new Human("TestHuman", 100, LoyaltyEnum.ALTA);
        human.setHealth(200);
        assertEquals(200, human.getHealth());
    }
}