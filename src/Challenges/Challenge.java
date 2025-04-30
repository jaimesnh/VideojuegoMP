package Challenges;

import java.util.ArrayList;
import User.Player;
import Utils.MenuUtils;

public class Challenge {

    private int gold;
    private boolean accepted;
    private boolean approved;
    private Player[] players = new Player[2];
    private Fight result;
    private Player winner;

    public Challenge() {}
    // Contructor
    public Challenge(Player player1, Player player2, int gold) {
        this.gold = gold;
        this.accepted = false;
        this.approved = false;
        this.players[0] = player1;
        this.players[1] = player2;
        this.result = null;
        this.winner = null;
    }


    public Player getOpponent(Player player) {
        if (player.equals(this.players[0])) {
            return this.players[1];
        } else {
            return this.players[0];
        }
    }

    public boolean isValid(Player loggedUser, Player opponent) {
        // Check if the opponent is the same as the logged user
        if (opponent == loggedUser) {
            MenuUtils.alert("Desafío inválido", "No puedes desafiarte a ti mismo.");
            return false;
        }
        // Check if the opponent has already been challenged
        else if (opponent.hasPendingChallenge()) {
            MenuUtils.alert("Desafío inválido", "El oponente ya ha sido desafiado.");
            return false;
        }
        // Check if the opponent is banned
        else if (opponent.isBanned()) {
            MenuUtils.alert("Desafío inválido", "El oponente esta baneado.");
            return false;
        }
        // Check if the opponent has recently battled
        else if (opponent.defeatedRecently()) {
            boolean yORn = MenuUtils.askYesNo("El oponente ha perdido recientemente una batalla, ¿Estás seguro de que quieres continuar?");
            if (!yORn) {
                return false;
            }
        }

        return true;
    }

    public Player getChallengedPlayer() {
        return this.players[1];
    }

    public Player getChallengerPlayer() {
        return this.players[0];
    }

    public void approve() {
        this.approved = true;
        this.getChallengedPlayer().setPendingChallenge(this);
    }

    public void accept() {
        this.accepted = true;
    }

    public void reject() {
        this.accepted = false;
        this.winner = this.getChallengerPlayer();
        Player loser = this.getChallengedPlayer();
        int fee = (int) (this.gold * 0.1);

        if (loser.canAfford(fee)) {
            loser.payGoldTo(fee, this.winner);
        } else {
            String msg = "El jugador desafiado no tiene fondos suficientes para pagar la cuota. Será baneado.";
            MenuUtils.alert("Oro insuficiente", msg);
            int gold = loser.getGold();
            loser.payGoldTo(gold, this.winner);
        }
    }

    public void manageFight() {
        this.startFight();
        this.printResult();

        this.finishChallenge();
    }

    private void startFight() {
        this.result = new Fight(this.players[0], this.players[1]);
        this.winner = this.result.getWinner();
    }

    private void printResult() {
        ArrayList<String> log = this.result.getLog();
        String[] fixedLog = log.toArray(String[]::new);

        MenuUtils.doc("Resultado del desafío", fixedLog);
    }

    private void finishChallenge() {
        Player p1 = this.players[0];
        Player p2 = this.players[1];

        // Reset the pending challenges
        p1.setPendingChallenge(null);
        p2.setPendingChallenge(null);

        // Add the fight to the history
        p1.addChallengeToHistory(this);
        p2.addChallengeToHistory(this);

        // Loser pays the gold to the winner and sets the last lost fight
        if (this.winner == null) {
            return;
        }
        Player loser = this.getOpponent(this.winner);
        loser.payGoldTo(this.gold, winner);
        loser.setLastLostFight(System.currentTimeMillis());
    }

    // GETTERS/SETTERS

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Fight getResult() {
        return result;
    }

    public void setResult(Fight result) {
        this.result = result;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

}
