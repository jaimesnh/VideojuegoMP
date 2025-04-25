package User;

import SystemGame.Challenge;
import SystemGame.Ranking;

public class Player extends User {
    private int registrationNumber;
    private Character character;

    public Player(String name, String username, String password, int registrationNumber) {
        super(name, username, password);
        this.registrationNumber = registrationNumber;
    }

    public void challenge(String username) {
    }

    public void acceptChallenge(Challenge c) {
    }

    public Ranking viewRanking() {
        return new Ranking();
    }

    public void registerCharacter() {
    }

    public void unregisterCharacter() {
        character = null;
    }

    public void chooseWeapons() {
    }

    public void chooseArmor() {
    }

    public void rejectChallenge() {
    }

    public void checkGold() {
    }

    public void operation1() {
    }

    // Optional Getters and Setters
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}

