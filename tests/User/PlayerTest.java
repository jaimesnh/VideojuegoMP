package User;

import Abilities.SpecialAbility;
import Abilities.Talent;
import Character.Strength;
import Character.Modifier;
import Character.CharacterSelection;
import Abilities.Gift;
import Challenges.Challenge;
import Equipment.Armor;
import Equipment.Weapon;
import SystemGame.SystemGame;
import Utils.Const;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @BeforeEach
    void init() {
        SystemGame g = new SystemGame();
        g.loadDefaultSettings();
    }

    @Test
    public void Player() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertNotNull(player);

        assertEquals("Nombre", player.getName());
        assertEquals("Apodo", player.getNick());
        assertEquals("Contraseña", player.getPassword());
        assertEquals("A11A1", player.getId());
    }

    @Test
    void addChallengeToHistory() {
        Player player1 = new Player("Nombre1", "Apodo1", "Contraseña1", "A11A1");
        Player player2 = new Player("Nombre2", "Apodo2", "Contraseña2", "A11A2");

        Challenge challenge = new Challenge(player1, player2, 100);

        player1.addChallengeToHistory(challenge);

        assertTrue(player1.getChallenges().contains(challenge));
    }

    @Test
    void hasChallenges() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.hasChallenges());

        Challenge challenge = new Challenge();
        player.addChallengeToHistory(challenge);

        assertTrue(player.hasChallenges());
    }

    @Test
    void getScore() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        int score = player.getScore();

        assertEquals(Const.INITIAL_GOLD, score);
    }

    @Test
    void defeatedRecently() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        boolean b = player.defeatedRecently();

        assertFalse(b);

        player.setLastLostFight(System.currentTimeMillis());

        b = player.defeatedRecently();

        assertTrue(b);
    }

    @Test
    void ban() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.isBanned());

        player.ban();

        assertTrue(player.isBanned());
    }

    @Test
    void unban() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.ban();

        assertTrue(player.isBanned());

        player.unban();

        assertFalse(player.isBanned());
    }

//    @Test
//    void manageModifiers() {
//        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");
//
//        assertDoesNotThrow(() -> player.manageModifiers());
//    }

    @Test
    void changeModifier() {
        tests.TestingUtils.setInput("0");
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertDoesNotThrow(() -> player.changeModifier(1));
    }

    @Test
    void changeSpecialAbility() {
        tests.TestingUtils.setInput("0");
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertDoesNotThrow(player::changeSpecialAbility);
    }

    @Test
    void showSpecialAbilities() {
        tests.TestingUtils.setInput("");

        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertDoesNotThrow(() -> player.showSpecialAbilities());
    }

    @Test
    void manageEquipment() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertDoesNotThrow(player::manageEquipment);
    }

    @Test
    void changeArmor() {
        tests.TestingUtils.setInput("1", "");

        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");
        SystemGame g = new SystemGame();
        g.loadDefaultSettings();

        assertDoesNotThrow(player::changeArmor);
    }

    @Test
    void changeWeapon() {
        tests.TestingUtils.setInput("1", "");

        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");
        SystemGame g = new SystemGame();
        g.loadDefaultSettings();

        assertDoesNotThrow(() -> player.changeWeapon(1));
    }

    @Test
    void notifyChallenge() {
        Player player1 = new Player("Nombre1", "Apodo1", "Contraseña1", "A11A1");
        Player player2 = new Player("Nombre2", "Apodo2", "Contraseña2", "A11A2");

        Challenge challenge = new Challenge(player1, player2, 100);

        player1.notifyChallenge(challenge);

        assertTrue(player1.isPendingNotification());
    }

    @Test
    void manageNotifications() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.manageNotifications();

        assertFalse(player.isPendingNotification());
    }

    @Test
    void payGoldTo() {
        Player player1 = new Player("Nombre1", "Apodo1", "Contraseña1", "A11A1");
        Player player2 = new Player("Nombre2", "Apodo2", "Contraseña2", "A11A2");

        player1.payGoldTo(50, player2);

        assertEquals(Const.INITIAL_GOLD - 50, player1.getGold());

        assertEquals(Const.INITIAL_GOLD + 50, player2.getGold());
    }

    @Test
    void canAfford() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertTrue(player.canAfford(100));

        assertFalse(player.canAfford(1000));
    }

    @Test
    void acceptChallenge() {
        tests.TestingUtils.setInput(" ", " ", " ", " ");
        Player player1 = new Player("Nombre1", "Apodo1", "Contraseña1", "A11A1");
        player1.setCurrentCharacter(CharacterSelection.LYCANTHROPE);
        player1.setSpecialAbilities(new Gift("SpecialAbility", 10, 10, 10));
        Player player2 = new Player("Nombre2", "Apodo2", "Contraseña2", "A11A2");
        player2.setCurrentCharacter(CharacterSelection.LYCANTHROPE);
        player2.setSpecialAbilities(new Gift("Habilidad Especial", 10, 10, 10));
        SystemGame g = new SystemGame();
        g.loadDefaultSettings();

        Challenge challenge = new Challenge(player1, player2, 100);

        player2.setPendingChallenge(challenge);

        player2.acceptChallenge();

        assertTrue(challenge.isAccepted());
    }

    @Test
    void getId() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        String id = player.getId();

        assertEquals("A11A1", id);
    }

    @Test
    void setId() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.setId("A11A2");
        String id = player.getId();

        assertEquals("A11A2", id);
    }

    @Test
    void getPendingChallenge() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertNull(player.getPendingChallenge());

        Challenge challenge = new Challenge();

        player.setPendingChallenge(challenge);
        Challenge pendingChallenge = player.getPendingChallenge();

        assertEquals(challenge, pendingChallenge);
    }

    @Test
    void setPendingChallenge() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Challenge challenge = new Challenge();

        player.setPendingChallenge(challenge);
        Challenge pendingChallenge = player.getPendingChallenge();

        assertEquals(challenge, pendingChallenge);
    }

    @Test
    void isPendingNotification() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.isPendingNotification());

        player.setPendingNotification(true);

        assertTrue(player.isPendingNotification());
    }

    @Test
    void setPendingNotification() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.isPendingNotification());

        player.setPendingNotification(true);

        assertTrue(player.isPendingNotification());
    }

    @Test
    void isBanned() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.isBanned());

        player.ban();

        assertTrue(player.isBanned());
    }

    @Test
    void setBanned() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.isBanned());

        player.setBanned(true);

        assertTrue(player.isBanned());
    }

    @Test
    void getGold() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        int gold = player.getGold();

        assertEquals(Const.INITIAL_GOLD, gold);
    }

    @Test
    void setGold() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.setGold(100);

        int gold = player.getGold();

        assertEquals(100, gold);
    }

    @Test
    void getLastLostFight() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        long lastLostFight = player.getLastLostFight();

        assertEquals(0, lastLostFight);
    }

    @Test
    void setLastLostFight() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        player.setLastLostFight(9999999L);
    }

    @Test
    void getCurrentCharacter() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        CharacterSelection currentCharacter = player.getCurrentCharacter();

        assertNull(currentCharacter);

        CharacterSelection character = CharacterSelection.LYCANTHROPE;

        player.setCurrentCharacter(character);
        currentCharacter = player.getCurrentCharacter();

        assertEquals(character, currentCharacter);
    }

    @Test
    void setCurrentCharacter() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        CharacterSelection character = CharacterSelection.LYCANTHROPE;

        player.setCurrentCharacter(character);
        CharacterSelection currentCharacter = player.getCurrentCharacter();

        assertEquals(character, currentCharacter);
    }

    @Test
    void getArmor() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Armor armor = player.getArmor();

        assertEquals(null, armor);
    }

    @Test
    void setArmor() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Armor armor = new Armor("Armor", 100, 10);

        player.setArmor(armor);
        Armor currentArmor = player.getArmor();

        assertEquals(armor, currentArmor);
    }

    @Test
    void getWeapons() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Weapon[] weapons = player.getWeapons();

        assertNull(weapons[0]);
        assertNull(weapons[1]);

        Weapon weapon = new Weapon("Weapon", 100, 10, 2);

        player.setWeapons(new Weapon[] { weapon, weapon });
        weapons = player.getWeapons();

        assertEquals(weapon, weapons[0]);
        assertEquals(weapon, weapons[1]);
    }

    @Test
    void setWeapons() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Weapon weapon = new Weapon("Weapon", 100, 10, 2);

        player.setWeapons(new Weapon[] { weapon, weapon });
        Weapon[] weapons = player.getWeapons();

        assertEquals(weapon, weapons[0]);
        assertEquals(weapon, weapons[1]);
    }

    @Test
    void getChallenges() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        List<Challenge> challenges = player.getChallenges();

        assertEquals(0, challenges.size());

        Challenge challenge = new Challenge();
        player.addChallengeToHistory(challenge);

        challenges = player.getChallenges();

        assertEquals(1, challenges.size());
        assertEquals(challenge, challenges.get(0));
    }

    @Test
    void setChallenges() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Challenge challenge = new Challenge();

        player.setChallenges(List.of(challenge));
        List<Challenge> challenges = player.getChallenges();

        assertEquals(1, challenges.size());
        assertEquals(challenge, challenges.get(0));
    }

    @Test
    void hasPendingChallenge() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        assertFalse(player.hasPendingChallenge());

        Challenge challenge = new Challenge();
        player.setPendingChallenge(challenge);

        assertTrue(player.hasPendingChallenge());
    }

    @Test
    void getModifiers() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Modifier[] modifiers = player.getModifiers();

        assertEquals(2, modifiers.length);
        assertNull(modifiers[0]);
        assertNull(modifiers[1]);
    }

    @Test
    void setModifiers() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        Modifier modifier = new Strength("Modifier", 10);

        player.setModifiers(new Modifier[] { modifier, modifier });
        Modifier[] modifiers = player.getModifiers();

        assertEquals(modifier, modifiers[0]);
        assertEquals(modifier, modifiers[1]);
    }

    @Test
    void getSpecialAbility() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        SpecialAbility specialAbility = player.getSpecialAbility();

        assertEquals(null, specialAbility);
    }

    @Test
    void setSpecialAbilities() {
        Player player = new Player("Nombre", "Apodo", "Contraseña", "A11A1");

        SpecialAbility specialAbility = new Talent("Habilidad Especial", 10, 10);

        player.setSpecialAbilities(specialAbility);
        SpecialAbility specialAbilities = player.getSpecialAbility();

        assertEquals(specialAbility, specialAbilities);
    }
}