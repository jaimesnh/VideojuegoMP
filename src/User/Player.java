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
        MenuUtils.doc("Player: " + this.getName(), data);
    }

    public boolean defeatedRecently() {
        long dayTime = 86400000L;
        return System.currentTimeMillis() - this.lastLostFight < dayTime;
    }

    public void ban() {
        this.banned = true;
        this.pendingChallenge = null;
        this.pendingNotification = false;
    }

    public void unban() {
        this.banned = false;
    }

    public void manageModifiers() {
        this.showModifiers();
        String[] options = new String[]{"Cambiar modificador 1", "Cambiar modificador 2", "Salir"};

        while(true) {
            MenuUtils.setConfigLastAsZero(true);
            int option = MenuUtils.menu("Modificadores", options);
            if (option == 1) {
                this.changeModifier(0);
            } else {
                if (option != 2) {
                    return;
                }
                this.changeModifier(1);
            }
        }
    }

    public void changeModifier(int modifierIndex) {
        List<Modifier> modifiersAvailable = Game.modifiersAvailable;
        String[] options = new String[modifiersAvailable.size()];

        for(int i = 0; i < modifiersAvailable.size(); ++i) {
            options[i] = ((Modifier)modifiersAvailable.get(i)).toString();
        }

        int option = MenuUtils.menu("Elija un Modificador", options) - 1;
        Modifier modifierSelected = (Modifier)modifiersAvailable.get(option);
        this.modifiers[modifierIndex] = modifierSelected;
        String message = "Has elegido el modificador." + modifierSelected.getName();
        MenuUtils.alert("Modificador elegido ", message);
    }

    public void showModifiers() {
        String[] info = new String[2];
        info[0] = "Modificador 1: " + (this.modifiers[0] != null ? this.modifiers[0].getName() : "None");
        info[1] = "Modificador 2: " + (this.modifiers[1] != null ? this.modifiers[1].getName() : "None");
        String[] data = info;
        MenuUtils.doc("Modificadores", data);
    }

    public void changeSpecialAbility() {
        String[] options;
        int characterAbility;
        if (this.currentCharacter == CharacterSelection.VAMPIRE) {
            options = Discipline.listAvailableDisciplines();
            characterAbility = 0;
        } else if (this.currentCharacter == CharacterSelection.LYCANTHROPE) {
            options = Don.listAvailableDones();
            characterAbility = 1;
        } else {
            options = Talent.listAvailableTalents();
            characterAbility = 2;
        }

        int option = MenuUtils.menu("Elija una habilidad especial", options) - 1;
        SpecialAbility specialAbilitySelected;
        if (characterAbility == 0) {
            specialAbilitySelected = (SpecialAbility)Game.disciplinesAvailable.get(option);
        } else if (characterAbility == 1) {
            specialAbilitySelected = (SpecialAbility)Game.donesAvailable.get(option);
        } else {
            specialAbilitySelected = (SpecialAbility)Game.talentsAvailable.get(option);
        }

        this.specialAbility = specialAbilitySelected;
        String message = "Has elegido la habilidad especial " + specialAbilitySelected.getName();
        MenuUtils.alert("Habilidad especial elegida", message);
    }

    public void showSpecialAbilities() {
        String[] info = new String[1];
        info[0] = "Habilidad especial: " + (this.specialAbility != null ? this.specialAbility.getName() : "None");
        String[] data = info;
        MenuUtils.doc("Habilidades especiales", data);
    }

    public void manageEquipment() {
        this.showEquipment();
        String[] options = new String[]{"Cambiar armadura", "Cambiar arma 1", "Cambiar arma 2", "Cambiar habilidad especial", "Cambiar modificadores", "Salir"};

        while(true) {
            MenuUtils.setConfigLastAsZero(true);
            int option = MenuUtils.menu("Equipamiento", options);
            if (option == 1) {
                this.changeArmor();
            } else if (option == 2) {
                this.changeWeapon(0);
            } else if (option == 3) {
                this.changeWeapon(1);
            } else if (option == 4) {
                this.changeSpecialAbility();
            } else {
                if (option != 5) {
                    return;
                }

                this.manageModifiers();
            }
        }
    }

    public void showEquipment() {
        String[] info = new String[6];
        info[0] = "Armadura: " + (this.armor != null ? this.armor.getName() : "None");
        info[1] = "Arma 1: " + (this.weapons[0] != null ? this.weapons[0].getName() : "None");
        info[2] = "Arma 2: " + (this.weapons[1] != null ? this.weapons[1].getName() : "None");
        info[3] = "Habilidad especial: " + (this.specialAbility != null ? this.specialAbility.getName() : "None");
        info[4] = "Modificador 1: " + (this.modifiers[0] != null ? this.modifiers[0].getName() : "None");
        info[5] = "Modificador 2: " + (this.modifiers[1] != null ? this.modifiers[1].getName() : "None");
        String[] data = info;
        MenuUtils.doc("Equipamiento", data);
    }

    public void changeArmor() {
        List<Armor> armorsAvailable = Game.armorsAvailable;
        String[] options = new String[armorsAvailable.size()];

        for(int i = 0; i < armorsAvailable.size(); ++i) {
            options[i] = ((Armor)armorsAvailable.get(i)).toString();
        }

        int option = MenuUtils.menu("Elija una armadura", options) - 1;
        this.armor = (Armor)armorsAvailable.get(option);
        String message = "Has elegido la armadura " + this.armor.getName();
        MenuUtils.alert("Armadura elegida", message);
    }

    public void changeWeapon(int weaponIndex) {
        List<Weapon> weaponsAvailable = Game.weaponsAvailable;
        String[] options = new String[weaponsAvailable.size()];

        for(int i = 0; i < weaponsAvailable.size(); ++i) {
            options[i] = ((Weapon)weaponsAvailable.get(i)).toString();
        }

        int option = MenuUtils.menu("Elija un arma", options) - 1;
        Weapon weaponSelected = (Weapon)weaponsAvailable.get(option);
        if (this.weapons[1 - weaponIndex] != null && this.weapons[1 - weaponIndex].getHandsRequired() + weaponSelected.getHandsRequired() > 2) {
            MenuUtils.alert("Error", "Tienes que deshacerte del otro arma para poder equipar la nueva.");
        } else {
            this.weapons[weaponIndex] = weaponSelected;
            String message = "Has elegido el arma  " + weaponSelected.getName();
            MenuUtils.alert("Arma elegida", message);
        }
    }

    public void notifyChallenge(Challenge challenge) {
        this.pendingNotification = true;
        this.pendingChallenge = challenge;
    }

    public void manageNotifications() {
        if (this.pendingNotification) {
            Player opponent = this.pendingChallenge.getOpponent(this);
            String message = "Tienes un desafio pendiente de " + opponent.getName();
            MenuUtils.alert("Notificación de desafío", message);
            String[] challengeData = new String[]{"Oponente: " + opponent.getName(), "Oro: " + this.pendingChallenge.getGold()};
            MenuUtils.doc("Desafío", challengeData);
            message = "¿Quieres aceptar el desafío de " + opponent.getName() + "?";
            boolean yORn = MenuUtils.askYesNo(message);
            if (yORn) {
                this.acceptChallenge();
            } else {
                String msg = "El desafío ha sido rechazado. Deberás pagar el 10% del oro apostado.";
                MenuUtils.alert("Advertencia de desafío", msg);
                this.pendingChallenge.reject();
            }

            this.pendingNotification = false;
        }
    }

    public void payGoldTo(int amount, Player player) {
        this.gold -= amount;
        player.gold += amount;
    }

    public boolean canAfford(int amount) {
        return this.gold >= amount;
    }

    public void acceptChallenge() {
        Challenge challenge = this.pendingChallenge;
        challenge.accept();
        challenge.manageFight();
        Player winner = challenge.getWinner();
        String msg = "";
        if (winner == challenge.getChallengedPlayer()) {
            msg = "¡Has ganado!:)";
        } else {
            msg = "Has perdido :(";
        }

        MenuUtils.alert("Resultado del combate", msg);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Challenge getPendingChallenge() {
        return this.pendingChallenge;
    }

    public void setPendingChallenge(Challenge pendingChallenge) {
        this.pendingChallenge = pendingChallenge;
    }

    public boolean isPendingNotification() {
        return this.pendingNotification;
    }

    public void setPendingNotification(boolean pendingNotification) {
        this.pendingNotification = pendingNotification;
    }

    public boolean isBanned() {
        return this.banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public long getLastLostFight() {
        return this.lastLostFight;
    }

    public void setLastLostFight(long lastLostFight) {
        this.lastLostFight = lastLostFight;
    }

    public CharacterSelection getCurrentCharacter() {
        return this.currentCharacter;
    }

    public void setCurrentCharacter(CharacterSelection currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public Armor getArmor() {
        return this.armor;
    }

    public void setArmor(Armor armor) {

        this.armor = armor;
    }

    public Weapon[] getWeapons() {

        return this.weapons;
    }

    public void setWeapons(Weapon[] weapons) {

        this.weapons = weapons;
    }

    public List<Challenge> getChallenges() {

        return this.challenges;
    }

    public void setChallenges(List<Challenge> challenges) {

        this.challenges = challenges;
    }

    public boolean hasPendingChallenge() {

        return this.pendingChallenge != null;
    }

    public Modifier[] getModifiers() {

        return this.modifiers;
    }

    public void setModifiers(Modifier[] modifiers) {

        this.modifiers = modifiers;
    }

    public SpecialAbility getSpecialAbility() {

        return this.specialAbility;
    }

    public void setSpecialAbilities(SpecialAbility specialAbility) {

        this.specialAbility = specialAbility;
    }
}


