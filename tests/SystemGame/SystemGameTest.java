package SystemGame;

import static org.junit.jupiter.api.Assertions.*;

import Challenges.Challenge;
import User.Player;
import User.User;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

class SystemGameTest {
    @Test
    public void testLoadDefaultSettings() {
        SystemGame game = new SystemGame();
        game.loadDefaultSettings();

        // Assert that the armors and weapons are loaded correctly
        assertNotNull(SystemGame.armorsAvailable);
        assertNotNull(SystemGame.weaponsAvailable);

        // Assert that the modifiers, talents, dones, disciplines, ghouls, humans, and devils are loaded correctly
        assertNotNull(SystemGame.modifiersAvailable);
        assertNotNull(SystemGame.talentsAvailable);
        assertNotNull(SystemGame.giftsAvailable);
        assertNotNull(SystemGame.disciplinesAvailable);
        assertNotNull(SystemGame.ghoulsAvailable);
        assertNotNull(SystemGame.humansAvailable);
        assertNotNull(SystemGame.devilsAvailable);
    }

    @Test
    public void testPassword() {
        String password;

        // Test password with 8 characters
        password = "Contraseña";
        assertEquals(true, SystemGame.isValidPassword(password));

        // Test password with 7 characters
        password = "Contraseña";
        assertEquals(false, SystemGame.isValidPassword(password));

        // Test password with 9 characters
        password = "Contraseña1";
        assertEquals(true, SystemGame.isValidPassword(password));

        // Test password with 16 characters
        password = "Contraseña123456789";
        assertEquals(false, SystemGame.isValidPassword(password));
    }

    @Test
    public void getUsers() {
        SystemGame game = new SystemGame();
        assertNotNull(game.getUsers());
    }

    @Test
    public void setUsers() {
        SystemGame game = new SystemGame();
        List<User> users = new ArrayList<>();
        game.setUsers(users);
        assertNotNull(game.getUsers());
    }

    @Test
    public void getLoggedUser() {
        SystemGame game = new SystemGame();
        game.setLoggedUser(new Player("Nombre", "Apodo", "Contraseña", "A11A1"));
        assertNotNull(game.getLoggedUser());
    }

    @Test
    public void setLoggedUser() {
        SystemGame game = new SystemGame();
        game.setLoggedUser(new Player("Nombre", "Apodo", "Contraseña", "A11A1"));
        assertNotNull(game.getLoggedUser());
    }

    @Test
    public void getChallenges() {
        SystemGame game = new SystemGame();
        assertNotNull(game.getChallenges());
    }

    @Test
    public void setChallenges() {
        SystemGame game = new SystemGame();
        List<Challenge> challenges = new ArrayList<>();
        game.setChallenges(challenges);
        assertNotNull(game.getChallenges());
    }

    @Test
    public void getLastId() {
        SystemGame game = new SystemGame();
        assertNull(game.getLastId());
        game.setLastId("A11A1");
        assertEquals("A11A1", game.getLastId());
    }

    @Test
    public void setLastId() {
        SystemGame game = new SystemGame();
        game.setLastId("A11A1");
        assertEquals("A11A1", game.getLastId());
    }

}