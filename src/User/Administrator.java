package User;

public class Administrator extends User {

        public Administrator() {}

        public Administrator(String name, String nick, String password) {
                super(name, nick, password);
        }


        public int getScore() {
                return 0;
        }


        public void manageChallenge(Challenge challenge) {
                //ACEPTAR GESTIÓN DE DESAFÍO?
                String msg1 = String.format("Quiéres gestionar el desafío entre %s and %s?", challenge.getChallengerPlayer().getName(), challenge.getChallengedPlayer().getName());
                boolean opt = MenuBuilder.askYesNo(msg1);

                //NO ACEPTA
                if (!opt) {
                        return;
                }

                //COGEMOS LOS JUGADORES
                Player player1 = challenge.getChallengerPlayer();
                Player player2 = challenge.getChallengedPlayer();

                //VEMOS EL DESAFIADO ESTÁ BANEADO
                if (player2.defeatedRecently()) {
                        String message = "El jugador desafiado perdió una batalla recientemente. Será baneado.";
                        boolean result = MenuBuilder.askYesNo(message);
                        if (result) {
                                player1.ban();
                                return;
                        }
                }

                //EDITAR EQUIPO
                this.manageRules(challenge, player1, player2);

                //AJUSTAR EL ORO DEL DESAFÍO
                if (!player2.canAfford(challenge.getGold())) {
                        challenge.setGold(player2.getGold());
                        String msg = String.format("La apuesta se ha ajustado a %d", challenge.getGold());
                        MenuBuilder.alert("Advertencia de desafío", msg);
                }

                //APROBAR DESAFÍO
                challenge.approve();

                //NOTIFICAR DESAFÍO
                player2.notifyChallenge(challenge);
        }


        private void manageRules(Challenge challenge, Player player1, Player player2) {
                String[] options = { "Manage Player 1", "Manage Player 2", "Exit" };
                int opt = MenuBuilder.menu("Manage Challenge", options);

                if (opt == 1) {
                        this.managePlayer(player1);
                } else if (opt == 2) {
                        this.managePlayer(player2);
                } else {
                        return;
                }
        }


        private void managePlayer(Player player) {
                String[] options = { "Manage Equipment", "Modify Character", "Alter Modifiers", "Modify Special Ability", "Exit" };

                while (true) {
                        MenuBuilder.setConfigLastAsZero(true);
                        int opt = MenuBuilder.menu("Manage Player", options);

                        if (opt == 1) {
                                player.manageEquipment();
                        } else if (opt == 2) {
                                this.modifyCharacter(player.getCurrentCharacter());
                        } else if (opt == 3) {
                                player.manageModifiers();
                        } else if (opt == 4) {
                                player.changeSpecialAbility();
                        } else {
                                break;
                        }
                }
        }

        private void modifyCharacter(CharacterSelection character) {
                if (character == CharacterSelection.LYCANTHROPE) {
                        Lycanthrope.modifyAttributes();
                } else if (character == CharacterSelection.VAMPIRE) {
                        Vampire.modifyAttributes();
                } else {
                        Hunter.modifyAttributes();
                }
        }
}
