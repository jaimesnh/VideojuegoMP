package User;

import Challenges.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    @Test
    public void Admin() {
        Object[][] testCases = {
                { "Test1", "Pablito1", "password" },
                { "Test2", "Pablito2", "password" },
                { "Test3", "Pablito3", "password" },
                { "Test4", "Pablito4", "password" }
        };

        for (Object[] testCase : testCases) {
            Administrator admin = new Administrator((String) testCase[0], (String) testCase[1], (String) testCase[2]);
            assertNotNull(admin);
            assertEquals((String) testCase[0], admin.getName());
            assertEquals((String) testCase[1], admin.getNick());
            assertEquals((String) testCase[2], admin.getPassword());
        }
    }

    @Test
    void getScore() {
        Administrator admin = new Administrator();
        assertEquals(0, admin.getScore());
    }

    @Test
    public void manageChallenge() {
        Object[][] testCases = {
                {"Test1.1", "Test1.2", "Pablito", "Xx_GorgeElMejor_xX", "PablitoContra", "JorgitoXD", "ID-1", "ID-2", 100},
                {"Test2.1", "Test2.2", "Pablito", "Xx_GorgeElMejor_xX", "PablitoContra", "JorgitoXD", "ID-1", "ID-2", 15},
                {"Test3.1", "Test3.2", "Pablito", "Xx_GorgeElMejor_xX", "PablitoContra", "JorgitoXD", "ID-1", "ID-2", 200},
                {"Test4.1", "Test4.2", "Pablito", "Xx_GorgeElMejor_xX", "PablitoContra", "JorgitoXD", "ID-1", "ID-2", 50}
        };

        tests.TestingUtils.setInput("1", "3", "1", "3", "1", "3", "1", "3");

        for (Object[] testCase : testCases) {
            Player challenger = new Player((String) testCase[0], (String) testCase[2], (String) testCase[4], (String) testCase[6]);
            Player challenged = new Player((String) testCase[1], (String) testCase[3], (String) testCase[5], (String) testCase[7]);
            Challenge challenge = new Challenge(challenger, challenged, (int) testCase[8]);

            Administrator admin = new Administrator("Admin", "Apodo", "Contraseña");


            admin.manageChallenge(challenge);

            assertTrue(challenge.isApproved());
            assertTrue(challenged.isPendingNotification());
            assertEquals(challenge, challenged.getPendingChallenge());

        }
    }
}