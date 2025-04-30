package User;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private String id;
    private Armor armor;
    private Weapon[] weapons = new Weapon[2];
    private int gold;
    private CharacterSelection currentCharacter;
    private SpecialAbility specialAbility;
    private Challenge pendingChallenge;
    private boolean pendingNotification;
    private boolean banned;
    private long lastLostFight;
    private Modifier[] modifiers = new Modifier[2];
    private List<Challenge> challenges = new ArrayList();

    public Player(String id, String nick, String password) {
        super(id, nick, password);
        this.id = id;
        this.pendingChallenge = false;
        this.banned = false;
        this.gold = 500;
    }

    public void addChallengeToHistory(Challenge challenge) {
        this.challenges.add(challenge);
    }

    public boolean hasChallenges() {
        return !this.challenges.isEmpty();
    }

    public int getScore() {
        return this.gold;
    }

    public void showPlayerInfo() {
        String[] info = new String[12];
        String nick = this.getNick();
        info[0] = "Nombre: " + nick;
        String name = this.getName();
        info[1] = "Nombre: " + name;
        int gold = this.gold;
        info[2] = "Gold: " + gold;
        info[3] = "Banned: " + (this.banned ? "Yes" : "No");
        info[4] = "Character: " + this.currentCharacter.name().toLowerCase();
        String var4 = this.armor != null ? this.armor.getName() : "None";
        info[5] = "Armor: " + var4;
        var4 = this.weapons[0] != null ? this.weapons[0].getName() : "None";
        info[6] = "Weapon 1: " + var4;
        var4 = this.weapons[1] != null ? this.weapons[0].getName() : "None";
        info[7] = "Weapon 2: " + var4;
        var4 = this.modifiers[0] != null ? this.modifiers[0].getName() : "None";
        info[8] = "Modifier 1" + var4;
        var4 = this.modifiers[1] != null ? this.modifiers[1].getName() : "None";
        info[9] = "Modifier 2" + var4;
        var4 = this.specialAbility != null ? this.specialAbility.getName() : "None";
        info[10] = "Special Ability" + var4;
        info[11] = "Pending challenge: " + (this.pendingChallenge != null ? "Yes" : "No");
        String[] data = info;
        MenuBuilder.doc("Player: " + this.getName(), data);
    }
}


