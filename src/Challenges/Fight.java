package Challenges;

import java.util.ArrayList;
import Utils.MenuUtils;
import User.Player;

    private int rounds;
    private String date;
    private Player winner;
    private ArrayList<String> log = new ArrayList<String>();
    private Character c1;
    private Character c2;
    private Player[] players;
    private final int MAX_ROUNDS = 100;

    // Constructor
    public Fight(Player player1, Player player2) {
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;

        this.c1 = createCharacter(player1);
        this.c2 = createCharacter(player2);

        this.start();
    }

    private Character createCharacter(Player player) {
        Character character;

        CharacterSelection cs = player.getCurrentCharacter();
        if (cs == CharacterSelection.HUNTER) {
            character = new Hunter(player);
        } else if (cs == CharacterSelection.LYCANTHROPE) {
            character = new Lycanthrope(player);
        } else if (cs == CharacterSelection.VAMPIRE) {
            character = new Vampire(player);
        } else {
            throw new IllegalArgumentException("Tipo de personaje no válido");
        }

        character.setName(player.getName());

        return character;
    }

    private void start() {
        int round = 1;

        log.add("La pelea comenzó entre " + c1.getName() + " y " + c2.getName());
        log.add("");

        while (!checkFightEnd() && round <= MAX_ROUNDS) {
            int damage1 = c2.getHit(c1);
            int damage2 = c1.getHit(c2);

            String msg1 = String.format("Ronda [%d]", round, c1.getName(), c1.getHealth());
            String msg2 = String.format(" %s ataques %s infringe (%d) puntos de daño", c1.getName(), c2.getName(), damage1);
            String msg3 = String.format(" %s ataques %s infringe (%d) puntos de daño", c2.getName(), c1.getName(), damage2);
            String msg4 = String.format(" Estado de la pelea: [%s](%d HP) vs [%s](%d HP)", c1.getName(), c1.getHealth(), c2.getName(), c2.getHealth());

            log.add(msg1);
            log.add(msg2);
            log.add(msg3);
            log.add(msg4);
            log.add("");
            round++;
        }

        if (round > MAX_ROUNDS) {
            MenuUtils.alert("Alerta de la pelea", "La pelea ha finalizado por haberse alcanzado el número máximo de rondas.");
        }

        chooseWinner();
    }

    private boolean checkFightEnd() {
        return c1.isDead() || c2.isDead();
    }

    private void chooseWinner() {
        log.add("");
        if (c2.isDead()) {
            winner = players[0];
            log.add("El ganador es " + c1.getName());
        } else if (c1.isDead()) {
            winner = players[1];
            log.add("El ganador es " + c2.getName());
        } else {
            winner = null;
            log.add("La pelea terminó en empate");
        }
    }
    
    // GETTERS/SETTERS

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void setLog(ArrayList<String> log) {
        this.log = log;
    }

    public Character getC1() {
        return c1;
    }

    public void setC1(Character c1) {
        this.c1 = c1;
    }

    public Character getC2() {
        return c2;
    }

    public void setC2(Character c2) {
        this.c2 = c2;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

}
