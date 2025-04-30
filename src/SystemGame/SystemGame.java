package SystemGame;

import User.*;
import Utils.*;
import Abilities.*;
import Challenges.*;
import Character.*;
import Equipment.*;
import Minions.*;
import java.util.*;


public class SystemGame {

    private static SystemGame instance;
    private List<User> users = new ArrayList<>();
    Adapter adapter = new XMLAdapter();
    private User loggedUser = null;
    private String lastId = null;
    private List<Challenge> challenges = new ArrayList<>();
    public static List<Armor> armorsAvailable = new ArrayList<>();
    public static List<Weapon> weaponsAvailable = new ArrayList<>();
    public static List<Modifier> modifiersAvailable = new ArrayList<>();
    public static List<Talent> talentsAvailable = new ArrayList<>();
    public static List<Gift> giftsAvailable = new ArrayList<>();
    public static List<Discipline> disciplinesAvailable = new ArrayList<>();
    public static List<Ghoul> ghoulsAvailable = new ArrayList<>();
    public static List<Human> humansAvailable = new ArrayList<>();
    public static List<Devil> devilsAvailable = new ArrayList<>();


    public SystemGame() {}

    //SINGLETON
    public static SystemGame getInstance() {
        if (instance == null) {
            instance = new SystemGame();
        }
        return instance;
    }

    // Main method to run the game with a loop
    public void play() {
        // Load the game
        this.load();

        // Save the game for the first time
        this.save();

        // Main Loop
        boolean exit = false;
        while (!exit) {
            // If the user is a player, manage the notifications
            if (this.loggedUser instanceof Player player) {
                player.manageNotifications();
            }

            // Print the menu and get the exit status
            exit = this.menu();

            // Save the game
            this.save();
        }
    }

    // Load the game
    private void load() {
        SystemGame game = (SystemGame) adapter.loadFile(Const.DATA_PATH);

        if (game != null) {
            this.replaceSettings(game);
        } else {
            this.loadDefaultSettings();
        }
    }

    // Load a default settings in case the is nothing saved before
    public void loadDefaultSettings() {

        SystemGame.armorsAvailable = Armor.loadFromArray(Const.ARMORS);
        SystemGame.weaponsAvailable = Weapon.loadFromArray(Const.WEAPONS);
        SystemGame.modifiersAvailable = Modifier.loadFromArray(Const.STRENGHTS, Const.WEAKNESSES);
        SystemGame.talentsAvailable = Talent.loadFromArray(Const.TALENTS);
        SystemGame.giftsAvailable = Gift.loadFromArray(Const.DONES);
        SystemGame.disciplinesAvailable = Discipline.loadFromArray(Const.DISCIPLINES);
        SystemGame.ghoulsAvailable = Ghoul.loadFromArray(Const.GHOULS);
        SystemGame.humansAvailable = Human.loadFromArray(Const.HUMANS);
        SystemGame.devilsAvailable = Devil.loadFromArray(Const.DEVILS);
    }

    // Load the data structure saved on disc
    private void replaceSettings(SystemGame game) {
        this.users = game.users;
        this.lastId = game.lastId;
        this.challenges = game.challenges;

        Map<String, Object> state = game.retrieveStaticState();
        SystemGame.armorsAvailable = (List<Armor>) state.get("armorsAvailable");
        SystemGame.weaponsAvailable = (List<Weapon>) state.get("weaponsAvailable");
        SystemGame.modifiersAvailable = (List<Modifier>) state.get("modifiersAvailable");
        SystemGame.talentsAvailable = (List<Talent>) state.get("talentsAvailable");
        SystemGame.giftsAvailable = (List<Gift>) state.get("giftsAvailable");
        SystemGame.disciplinesAvailable = (List<Discipline>) state.get("disciplinesAvailable");
        SystemGame.ghoulsAvailable = (List<Ghoul>) state.get("ghoulsAvailable");
        SystemGame.humansAvailable = (List<Human>) state.get("humansAvailable");
        SystemGame.devilsAvailable = (List<Devil>) state.get("devilsAvailable");
    }

    private Map<String, Object> retrieveStaticState() {
        return (Map<String, Object>) adapter.loadFile(Const.STATE_PATH);
    }

    // Save the game
    private void save() {
        adapter.saveFile(this, Const.DATA_PATH);

        adapter.saveFile(this.createStaticState(), Const.STATE_PATH);
    }

    // Method to create the data structure to save on disc
    private Map<String, Object> createStaticState() {
        Map<String, Object> state = new HashMap<>();
        state.put("armorsAvailable", SystemGame.armorsAvailable);
        state.put("weaponsAvailable", SystemGame.weaponsAvailable);
        state.put("modifiersAvailable", SystemGame.modifiersAvailable);
        state.put("talentsAvailable", SystemGame.talentsAvailable);
        state.put("giftsAvailable", SystemGame.giftsAvailable);
        state.put("disciplinesAvailable", SystemGame.disciplinesAvailable);
        state.put("ghoulsAvailable", SystemGame.ghoulsAvailable);
        state.put("humansAvailable", SystemGame.humansAvailable);
        state.put("devilsAvailable", SystemGame.devilsAvailable);
        return state;
    }

    // Check the length of the password
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 12;
    }


    // Method to print the menu
    private boolean menu() {
        boolean exit = false;

        switch (this.loggedUser) {
            case null -> exit = this.notLoggedMenu();
            case Player player -> this.loggedPlayerMenu();
            case Administrator administrator -> this.loggedAdminMenu();
            default -> {
            }
        }

        return exit;
    }

    // Print the menu for not logged users
    private boolean notLoggedMenu() {
        String[] options = { "Iniciar Sesión", "Registrarse", "Salir" }; // 1, 2, 3
        MenuUtils.setConfigLastAsZero(true);
        int answer = MenuUtils.menu("Bienvenido al juego", options);

        if (answer == 1) {
            this.login();
            if (loggedUser instanceof Player) {
                ((Player) loggedUser).manageNotifications();
            }
        } else if (answer == 2) {
            this.register();
        } else {
            return true;
        }

        return false;
    }

    // Print the menu for logged users
    private void loggedPlayerMenu() {
        String[] options = { "desafío", "Modificar equipamiento activo", "Cambiar personaje", "Historial de batallas", "Ranking", "Gestionar cuenta", "Cerrar sesión" };
        // 1, 2, 3, 4, 5, 6, 0
        String nickName = this.loggedUser.getNick();
        MenuUtils.setConfigLastAsZero(true);
        int answer = MenuUtils.menu(String.format("Menu [%s]", nickName), options);

        if (answer == 1) {
            this.challenge();
        } else if (answer == 2) {
            this.modifyActiveEquipment();
        } else if (answer == 3) {
            this.changeCharacter();
        } else if (answer == 4) {
            this.checkBattleHistory();
        } else if (answer == 5) {
            this.checkRanking();
        } else if (answer == 6) {
            this.manageAccount();
        } else {
            this.logOut();
        }
    }

    // Print the menu for logged admins
    private void loggedAdminMenu() {
        String[] options = { "Gestionar jugadores", "Gestionar equipamiento", "Gestionar desafíos", "Comprobar Ranking", "Gestionar cuenta", "Cerrar Sesión" };
        // 1, 2, 3, 4, 5, 0
        String nickName = this.loggedUser.getNick();
        MenuUtils.setConfigLastAsZero(true);
        int answer = MenuUtils.menu(String.format("Menú [%s]", nickName), options);

        if (answer == 1) {
            this.managePlayers();
        } else if (answer == 2) {
            this.manageEquipment();
        } else if (answer == 3) {
            this.manageChallenges();
        } else if (answer == 4) {
            this.checkRanking();
        } else if (answer == 5) {
            this.manageAccount();
        } else {
            this.logOut();
        }
    }

    // Main loop for the logging in process
    private void login() {
        while (this.loggedUser == null) {
            String[] credentials = this.askUserCredentials();

            User user = this.retrUser(credentials[0], credentials[1]);

            if (user == null) {
                MenuUtils.alert("Credenciales incorrectas", "El usuario o contraseña son incorrectas");
                boolean answer = MenuUtils.askYesNo("¿Quieres volver a intentarlo?");
                if (!answer) {
                    return;
                }
            } else if (user instanceof Player && ((Player) user).isBanned()) {
                String msg = "Has sido baneado. Contacta con el administrador para más información.";
                MenuUtils.alert("Usuario baneado", msg);
                return;
            } else {
                this.setLoggedUser(user);
            }
        }
    }

    private String[] askUserCredentials() {
        String[] labels = { "Usuario", "Contraseña" };
        return MenuUtils.form("Iniciar sesión", labels);
    }

    // Method to search the user from the user list of logged users
    private User retrUser(String username, String password) {
        for (User user : this.users) {
            if (validateUser(user, username, password)) {
                return user;
            }
        }
        return null;
    }

    // Method to check if the username and password match the user credentials
    private boolean validateUser(User user, String username, String password) {
        return user.getName().equals(username) && user.getPassword().equals(password);
    }

    // Method to print the register form
    private void register() {
        String[] userData = this.readUserData();

        while (this.isUsernameTaken(userData[0])) {
            MenuUtils.alert("Usuario incorrecto", "Nombre de usuario escogido");
            userData = this.readUserData();
        }

        int userType = this.readUserType();

        User user = this.createUser(userData, userType);

        this.setLoggedUser(user);

        if (user instanceof Player) {
            this.changeCharacter();
        }

        this.users.add(user);
    }

    private String[] readUserData() {
        String[] labels = { "Usuario", "Apodo", "Contraseña", "Confirmar contraseña" };
        String[] data = MenuUtils.form("Registro", labels);

        while ((!data[2].equals(data[3])) || (!SystemGame.isValidPassword(data[2]))) {
            MenuUtils.alert("Contraseña incorrecta", "La contraseña debe tener entre 8 y 12 caracteres.");
            data = MenuUtils.form("Registro", labels);
        }

        return data;
    }

    // Check if the usurname is taken
    private boolean isUsernameTaken(String username) {
        for (User user : this.users) {
            if (user.getName().equals(username)) return true;
        }

        return false;
    }

    // Prints a menu to select the user type among two options
    private int readUserType() {
        String[] options = { "Jugador", "Administrador" };
        return MenuUtils.menu("Seleccione el tipo de usuario", options);
    }

    // Method to create the user
    private User createUser(String[] userData, int userType) {
        if (userType == 1) {
            return new Player(userData[0], userData[1], userData[2], this.generatePlayerId());
        } else {
            return new Administrator(userData[0], userData[1], userData[2]);
        }
    }


    // Method to create the player id. The id format is: LNLLN (L: Letter, N: Number).
    private String generatePlayerId() {
        if (this.lastId == null) {
            this.lastId = "A0AA0";
            return this.lastId;
        }

        String[] parts = this.lastId.split("");

        int[] partsInt = new int[5];
        for (int i = 0; i < parts.length; i++) {
            partsInt[i] = parts[i].codePointAt(0);
        }

        partsInt[4]++;
        if (partsInt[4] > 57) {
            partsInt[4] = 48;
            partsInt[3]++;
            if (partsInt[3] > 90) {
                partsInt[3] = 65;
                partsInt[2]++;
                if (partsInt[2] > 90) {
                    partsInt[2] = 65;
                    partsInt[1]++;
                    if (partsInt[1] > 57) {
                        partsInt[1] = 48;
                        partsInt[0]++;
                        if (partsInt[0] > 90) {
                            throw new RuntimeException("Limites de IDS llegados");
                        }
                    }
                }
            }
        }
        for (int i = 0; i < partsInt.length; i++) {
            parts[i] = String.valueOf((char) partsInt[i]);
        }

        String newId = String.format("%s%s%s%s%s", parts[0], parts[1], parts[2], parts[3], parts[4]);
        this.lastId = newId;

        return newId;
    }

    // Admin methods

    // Prints a list of players to the user and asks the user to select an opponent to challenge.
    private Player askPlayerFromPlayersList() {
        Player[] players = this.getPlayers();

        String title = "Seleccione un jugador para desafiar";
        String[] options = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            options[i] = players[i].getNick() + " #" + players[i].getId();
        }
        int answer = MenuUtils.menu(title, options) - 1;

        return (Player) players[answer];
    }

    // Method to challenge a player
    private void challenge() {
        Player currPlayer = (Player) this.loggedUser;

        Player opponent = this.askPlayerFromPlayersList();

        int gold = MenuUtils.readInt("Introduce el oro a apostar");
        if (!currPlayer.canAfford(gold)) {
            MenuUtils.alert("Oro invalido", "No tienes tanto oro para apostar");
            return;
        }

        Challenge challenge = new Challenge(currPlayer, opponent, gold);
        if (!challenge.isValid(currPlayer, opponent)) {
            MenuUtils.alert("Alerta de desafío", "El desafío no ha sido creado, inténtelo de nuevo");
            return;
        }

        this.modifyActiveEquipment();
        currPlayer.setPendingChallenge(challenge);
        MenuUtils.alert("desafío creado", "El desafío ha sido creado con éxito");
        this.challenges.add(challenge);
    }

    // Method to get list o players
    private Player[] getPlayers() {
        List<Player> players = new ArrayList<>();
        for (User user : this.users) {
            if (user instanceof Player) {
                players.add((Player) user);
            }
        }
        return players.toArray(new Player[players.size()]);
    }

    // Prints the manage equipment
    private void modifyActiveEquipment() {
        Player currPlayer = (Player) this.loggedUser;
        currPlayer.manageEquipment();
    }

    // method to change character
    private void changeCharacter() {
        Player player = (Player) this.loggedUser;

        String title = "Cambiar personaje";
        String[] options = CharacterSelection.allToString();
        int answer = MenuUtils.menu(title, options);

        CharacterSelection selectedCharacter = CharacterSelection.values()[answer - 1];

        String output = "%s ha sido seleccionado.";
        output = String.format(output, selectedCharacter.toString());

        MenuUtils.alert(title, output);

        if (selectedCharacter != player.getCurrentCharacter()) {
            player.setCurrentCharacter(selectedCharacter);
            player.setSpecialAbilities(null);
        }
    }

    // Prints the battle histoty
    private void checkBattleHistory() {
        Player player = (Player) this.loggedUser;

        if (!player.hasChallenges()) {
            MenuUtils.alert("El historia esta vacío", "No has participado en en ningún desafío");
            return;
        }

        // Create the battle history report
        String[] data = new String[player.getChallenges().size()];

        // Fill the data array with the battle history
        for (int i = 0; i < player.getChallenges().size(); i++) {
            // Get a certain challenge
            Challenge challenge = player.getChallenges().get(i);

            // Get the result of the challenge
            String result = "Empate";
            if (challenge.getResult() != null) {
                result = challenge.getWinner() == player ? "Victoria" : "Derrota";
            }

            String name1 = challenge.getChallengerPlayer().getName();
            String name2 = challenge.getChallengedPlayer().getName();
            String msg = String.format("- [%d] %s VS %s ==> TÚ %s!", i + 1, name1, name2, result);
            data[i] = msg;
        }

        // Print the battle history
        MenuUtils.doc("Historial de batallas", data);
    }

    private void managePlayers() {
        while (true) {
            Player player = this.askPlayerFromPlayersList();

            String[] options = new String[] { "Banear jugador", "Desbanear jugador", "Mostrar información de jugador", "Volver" };
            // 1, 2, 3
            MenuUtils.setConfigLastAsZero(true);
            int action = MenuUtils.menu("Gestionar jugador", options);

            if (action == 1) {
                this.banPlayer(player);
            } else if (action == 2) {
                this.unbanPlayer(player);
            } else if (action == 3) {
                this.showPlayerInfo(player);
            } else {
                break;
            }
        }
    }

    // Methods to ban/unban players

    private void banPlayer(Player player) {
        boolean confirm = MenuUtils.askYesNo("¿Estas seguro que quieres banear a este jugador?");
        if (confirm) {
            player.ban();
            MenuUtils.alert("Jugador baneado", "El jugador ha sido baneado.");
        } else {
            MenuUtils.alert("Operación cancelada", "El jugador no ha sido baneado.");
        }
    }

    private void unbanPlayer(Player player) {
        boolean confirm = MenuUtils.askYesNo("¿Estas seguro que quieres desbanear a este jugador?");
        if (confirm) {
            player.unban();
            MenuUtils.alert("Jugador desbaneado", "El jugador ha sido desbaneado.");
        } else {
            MenuUtils.alert("Operación cancelada", "El jugador no ha sido desbaneado");
        }
    }

    private void showPlayerInfo(Player player) {
        player.showPlayerInfo();
    }

    // Method to manage equipment
    private void manageEquipment() {
        while (true) {
            String[] options = new String[] { "Gestionar armaduras", "Gestionar armas", "Volver" };
            MenuUtils.setConfigLastAsZero(true);
            int answer = MenuUtils.menu("Gestionar equipo", options);

            if (answer == 1) {
                this.manageArmors();
            } else if (answer == 2) {
                this.manageWeapons();
            } else {
                break;
            }
        }
    }

    // Method to manage armors
    private void manageArmors() {
        while (true) {
            String[] options = new String[]{"Añadir armadura", "Eliminar armadura", "Mostrar armadura", "Volver"};
            MenuUtils.setConfigLastAsZero(true);
            int answer = MenuUtils.menu("Gestionar armaduras", options);

            if (answer == 1) {
                this.addArmor();
            } else if (answer == 2) {
                this.removeArmor();
            } else if (answer == 3) {
                this.showArmors();
            } else {
                break;
            }
        }
    }

    // Method to add a new armor
    private void addArmor() {
        String[] labels = { "Nombre", "Modificador de defensa", "Modificador de ataque" };
        String[] dataInput = MenuUtils.form("Añadir armadura", labels);

        int defenseModifier = 0;
        int attackModifier = 0;

        try {
            defenseModifier = Integer.parseInt(dataInput[1]);
            attackModifier = Integer.parseInt(dataInput[2]);

            Armor armor = new Armor(dataInput[0], defenseModifier, attackModifier);

            boolean answer = MenuUtils.askYesNo("¿Estas seguro de añadir esta armadura?");

            if (answer) {
                SystemGame.armorsAvailable.add(armor);
            } else {
                MenuUtils.alert("Operación cancelada", "Esta armadura no ha sido añadida");
            }
        } catch (NumberFormatException e) {
            MenuUtils.alert("Error en la entrada", "Los modificadores de ataque y defensa deben de ser enteros");
            this.addArmor(); // Recursive call
        }
    }

    // Method to remove an existing armor
    private void removeArmor() {
        String[] options = new String[SystemGame.armorsAvailable.size()];
        for (int i = 0; i < SystemGame.armorsAvailable.size(); i++) {
            options[i] = SystemGame.armorsAvailable.get(i).getName();
        }
        int answer = MenuUtils.menu("Eliminar armadura", options) - 1;

        // Ask for user confirmation
        boolean confirm = MenuUtils.askYesNo("¿Estas seguro de eliminar esta armadura?");

        // If the user confirms, remove the armor from the armors available
        if (confirm) {
            SystemGame.armorsAvailable.remove(answer);
        } else {
            MenuUtils.alert("Operación cancelada", "Esta armadura no ha sido eliminada");
        }
    }

    // Method to show armors
    private void showArmors() {
        // Create the armors data table
        String[] data = new String[SystemGame.armorsAvailable.size()];

        // Fill the data array with the armors
        for (int i = 0; i < SystemGame.armorsAvailable.size(); i++) {
            Armor armor = SystemGame.armorsAvailable.get(i);
            data[i] = armor.getName() + " --> Defensa: " + armor.getDefense() + " | Ataque: " + armor.getAttack();
        }

        MenuUtils.doc("Armors", data);
    }

    // Method to manage weapons
    private void manageWeapons() {
        while (true) {
            String[] options = new String[] { "Añadir arma", "Eliminar arma", "Mostrar armas", "Volver" };
            MenuUtils.setConfigLastAsZero(true);
            int answer = MenuUtils.menu("Gestionar armas", options);

            if (answer == 1) {
                this.addWeapon();
            } else if (answer == 2) {
                this.removeWeapon();
            } else if (answer == 3) {
                this.showWeapons();
            } else {
                break;
            }
        }
    }


    private void addWeapon() {
        String[] labels = { "Nombre", "Modificador de defensa", "Modificador de ataque", "Manos requeridas" };
        String[] dataInput = MenuUtils.form("Añadir arma", labels);

        int defenseModifier = 0;
        int attackModifier = 0;
        int handsRequired = 0;

        try {
            defenseModifier = Integer.parseInt(dataInput[1]);
            attackModifier = Integer.parseInt(dataInput[2]);
            handsRequired = Integer.parseInt(dataInput[3]);

            Weapon weapon = new Weapon(dataInput[0], defenseModifier, attackModifier, handsRequired);

            boolean answer = MenuUtils.askYesNo("¿Estas seguro de añadir este arma?");

            if (answer) {
                SystemGame.weaponsAvailable.add(weapon);
            } else {
                MenuUtils.alert("Operación cancelada", "El arma no ha sido añadida");
            }
        } catch (NumberFormatException e) {
            MenuUtils.alert("Error en la entrada", "Los modificadores de ataque, defensa y las manos requeridas deben de ser enteros");
            this.addWeapon();
        }
    }

    // Method to remove a weapon
    private void removeWeapon() {
        String[] options = new String[SystemGame.weaponsAvailable.size()];
        for (int i = 0; i < SystemGame.weaponsAvailable.size(); i++) {
            options[i] = SystemGame.weaponsAvailable.get(i).getName();
        }
        int answer = MenuUtils.menu("Eliminar arma", options) - 1;

        boolean confirm = MenuUtils.askYesNo("¿Estas seguro de eliminar este arma?");

        if (confirm) {
            SystemGame.weaponsAvailable.remove(answer);
        } else {
            MenuUtils.alert("Operación cancelada", "El arma no ha sido eliminada.");
        }
    }

    // Method to show weapons
    private void showWeapons() {
        String[] data = new String[SystemGame.weaponsAvailable.size()];

        for (int i = 0; i < SystemGame.weaponsAvailable.size(); i++) {
            Weapon weapon = SystemGame.weaponsAvailable.get(i);
            data[i] = weapon.getName() + " --> Defensa: " + weapon.getDefense() + " | Ataque: " + weapon.getAttack() + " | Manos requeridas: " + weapon.getHandsRequired();
        }

        MenuUtils.doc("Armas", data);
    }

    // Method tomanage challenges
    private void manageChallenges() {
        Administrator administrator = (Administrator) this.loggedUser;

        for (Challenge challenge : this.challenges) {
            if (!challenge.isApproved()) {
                administrator.manageChallenge(challenge);
            }
        }

        MenuUtils.alert("gestión de desafíos", "Todos los desafíos han sido gestionados con éxito");
    }


    // Non admin methods

    // Method to check ranking (for players)
    private void checkRanking() {
        this.users.sort((u1, u2) -> u2.getScore() - u1.getScore());

        Player[] players = this.getPlayers();

        String[] data = new String[players.length];

        for (int i = 0; i < players.length; i++) {
            Player player = players[i];

            String playerData = player.getNick() + "#" + player.getId();

            if (player.isBanned()) {
                playerData += " --> BANEADO";
            } else {
                playerData += " --> " + player.getScore();
            }

            data[i] = playerData;
        }

        MenuUtils.doc("Ranking", data);
    }

    // Method to manage account (as player)
    private void manageAccount() {
        int answer = -1;

        while (answer != 0 && this.loggedUser != null) {
            String[] options = { "Cambiar apodo", "Cambiar contraseña", "Eliminar cuenta", "Volver" };
            MenuUtils.setConfigLastAsZero(true);
            answer = MenuUtils.menu("Gestionar cuenta", options);

            if (answer == 1) {
                this.changeNick();
            } else if (answer == 2) {
                this.changePassword();
            } else if (answer == 3) {
                this.deleteAccount();
            }
        }
    }

    // Method to change nick for users
    private void changeNick() {
        String data = MenuUtils.readString("Cambiar apodo");

        if (data != null) {
            this.loggedUser.setNick(data);
            MenuUtils.alert("Apodo actualizado", "Tu apodo ha sido actualizado correctamente.");
        }
    }

    // Method to change password for all users
    private void changePassword() {
        String[] labels = { "Nueva contraseña", "Confirmar contraseña" };
        String[] data = MenuUtils.form("Cambiar contraseña", labels);

        if (data[0].equals(data[1])) {
            this.loggedUser.setPassword(data[0]);
            MenuUtils.alert("Contraseña actualizada", "Tu contraseña ha sido actualizada con éxito.");
        } else {
            MenuUtils.alert("Contraseña incorrecta", "La contraseña es incorrecta. Vuelva a intentarlo.");
            this.changePassword();
        }
    }

    // Method to delete the account
    private void deleteAccount() {
        boolean answer = MenuUtils.askYesNo("¿Estas seguro de eliminar la cuenta?");

        if (answer) {
            this.users.remove(this.loggedUser);
            this.logOut();
            MenuUtils.alert("cuenta eliminada", "Tu cuenta ha sido eliminada correctamente");
        }
    }

    private void logOut() {
        this.loggedUser = null;
    }


    // GETTERS/SETTERS
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

}
