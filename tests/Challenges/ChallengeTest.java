package Challenges;

import Character.CharacterSelection;
import Abilities.Gift;
import Abilities.Talent;
import SystemGame.SystemGame;
import Utils.Const;
import Utils.TestingUtils;
import User.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeTest {

     static Object[][] defaultDataInput = {
        { new Player("1", "1", "1", "9999"), new Player("2", "2", "2", "9999"), 1 },
        { new Player("3", "3", "3", "9999"), new Player("4", "4", "4", "9999"), 2 },
        { new Player("5", "5", "5", "9999"), new Player("6", "6", "6", "9999"), 3 },
        { new Player("7", "7", "7", "9999"), new Player("8", "8", "8", "9999"), 4 }
    };


    @Test
    void getOpponent() {
        Challenge challenge = new Challenge();

        for (Object[] data : this.defaultDataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            assertEquals(player2, challenge.getOpponent(player1));
            assertEquals(player1, challenge.getOpponent(player2));
        }
    }

    @Test
    void isValid() {
        Player auxPlayer = new Player("1", "1", "1", "9999");
        Object[][] dataInput = {
                // Player 1 - Player 2 - Gold - Config1 - Config2 - Config3 - Result - TerminalInput
                { new Player("1", "1", "1", "X-999"), new Player("2", "2", "2", "Y-999"), 1, true, false, true, false, " "},
                { new Player("3", "3", "3", "X-999"), new Player("4", "4", "4", "Y-999"), 2, false, false, true, true, "1" },
                { new Player("5", "5", "5", "X-999"), new Player("6", "6", "6", "Y-999"), 3, true, true, true, false, " " },
                { auxPlayer, auxPlayer, 4, false, true, false, false, " " }
        };

        TestingUtils.setInput(
                (String) dataInput[0][7], (String) dataInput[1][7],
                (String) dataInput[2][7], (String) dataInput[3][7]
        );

        Challenge auxChallenge = new Challenge();

        for (Object[] data : dataInput) {
            Player loggedUser = (Player) data[0];
            Player opponent = (Player) data[1];
            if ((boolean) data[3]) {
                opponent.setPendingChallenge(auxChallenge);
            }
            opponent.setBanned((boolean) data[4]);

            if ((boolean) data[5]) {
                opponent.setLastLostFight(System.currentTimeMillis() - 22 * 60 * 60 * 1000);
            } else {
                opponent.setLastLostFight(System.currentTimeMillis() - 25 * 60 * 60 * 1000);
            }

            int gold = (int) data[2];
            Challenge challenge = new Challenge(loggedUser, opponent, gold);

            boolean result = (boolean) data[6];
            assertEquals(result, challenge.isValid(loggedUser, opponent));
        }
    }

    @Test
    void getChallengedPlayer() {
        Challenge challenge = new Challenge();

        for (Object[] data : this.defaultDataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            assertEquals(player2, challenge.getChallengedPlayer());
        }
    }

    @Test
    void getChallengerPlayer() {
        Challenge challenge = new Challenge();

        for (Object[] data : this.defaultDataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            assertEquals(player1, challenge.getChallengerPlayer());
        }
    }

    @Test
    void approve() {
        Challenge challenge = new Challenge();

        for (Object[] data : this.defaultDataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            challenge.approve();
            assertEquals(true, challenge.isApproved());
        }
    }

    @Test
    void accept() {
        Challenge challenge = new Challenge();

        for (Object[] data : this.defaultDataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            challenge.accept();
            assertEquals(true, challenge.isAccepted());
        }
    }

    @Test
    void reject() {
        Challenge challenge = new Challenge();

        for (Object[] data : this.defaultDataInput) {
            Player player1 = (Player) data[0];
            Player player2 = (Player) data[1];
            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);

            challenge.reject();
            assertEquals(false, challenge.isAccepted());
            assertEquals(player1, challenge.getWinner());
        }
    }

    @Test
    void manageFight() {
        Challenge challenge = new Challenge();
        TestingUtils.setInput(" ", " ", " ", " ");

        for (Object[] data : this.defaultDataInput) {


            SystemGame game = new SystemGame();
            game.loadDefaultSettings();

            Player player1 = (Player) data[0];
            player1.setCurrentCharacter(CharacterSelection.HUNTER);
            player1.setSpecialAbilities(new Talent("test", 5, 5));

            Player player2 = (Player) data[1];
            player2.setCurrentCharacter(CharacterSelection.LYCANTHROPE);
            player2.setSpecialAbilities(new Gift("test", 0, 0, 0));

            int gold = (int) data[2];

            challenge = new Challenge(player1, player2, gold);
            challenge.manageFight();

            Player loser = null;
            Player winner = null;

            // If the challenge turns out to be a draw, return
            if (challenge.getWinner() == null) {
                return;
            }

            // Continue with the test
            if (challenge.getWinner().equals(player1)) {
                winner = player1;
                loser = player2;
            } else {
                winner = player2;
                loser = player1;
            }

            assertNotNull(challenge.getResult().getWinner());
            assertNull(player1.getPendingChallenge());
            assertNull(player2.getPendingChallenge());

            boolean result1 = false;
            for (Challenge ch : player1.getChallenges()) {
                if (ch.equals(challenge)) {
                    result1 = true;
                    break;
                }
            }

            boolean result2 = false;
            for (Challenge ch : player2.getChallenges()) {
                if (ch.equals(challenge)) {
                    result2 = true;
                    break;
                }
            }

            assertEquals(true, result1);
            assertEquals(true, result2);

            assertEquals(winner.getGold(), Const.INITIAL_GOLD + gold);
            assertEquals(loser.getGold(), Const.INITIAL_GOLD - gold);

        }
    }
}