package SystemGame;

import User.Player;
public class Challenge {
    private Player challenger;
    private Player challenged;
    private int goldBet;
    private String status;

    // Constructor
    public Challenge(Player challenger, Player challenged, int gold) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.goldBet = gold;
        this.status = "pending";
    }

    // Method to change the challenge status
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    // Getters
    public Player getChallenger() {
        return challenger;
    }

    public Player getChallenged() {
        return challenged;
    }

    public int getGoldBet() {
        return goldBet;
    }

    public String getStatus() {
        return status;
    }
}
