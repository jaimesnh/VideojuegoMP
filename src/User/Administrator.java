package User;

import Challenges.Challenge;
import Character.*;
import Utils.MenuUtils;

public class Administrator extends User {

        public Administrator(String name, String nick, String password) {
                super(name, nick, password);
        }

        //EL ADMINISTRADOR NO TIENE SCORE
        public int getScore() {
                return 0;
        }

        // GESTIONAR DESAFIO
        public void manageChallenge(Challenge challenge) {
        //ADMINISTRADOR ACEPTA O RECHAZA GESTIONAR EL DESAFIO
                String msg1 = String.format("¿Quieres gestionar el desafío entre %s y %s?", challenge.getChallengerPlayer().getName(), challenge.getChallengedPlayer().getName());
                boolean opt = MenuUtils.askYesNo(msg1);

               //NO
                if (!opt) {
                        return;
                }

                //EXTRAER LOS DOS JUGADORES
                Player player1 = challenge.getChallengerPlayer();
                Player player2 = challenge.getChallengedPlayer();

                // BANEAR AL DESAFIADO SI HA PERDIDO RECIENTEMENTE
                if (player2.defeatedRecently()) {
                        String message = "El jugador desafiado perdió una batalla recientemente. Será baneado.";
                        boolean banopt = MenuUtils.askYesNo(message);
                        if (banopt) {
                                player1.ban();
                                return;
                        }
                }

                //EDITAR EQUIPO
                this.manageRules(challenge, player1, player2);

                //AJUSTAR ORO
                if (!player2.canAfford(challenge.getGold())) {
                        challenge.setGold(player2.getGold());
                        String msg = String.format("La apuesta se ha ajustado a %d", challenge.getGold());
                        MenuUtils.alert("Advertencia de desafío", msg);
                }

                //APROBAR DESAFIO
                challenge.approve();

               //NOTIFICAR DESAFIO AL JUGADOR
                player2.notifyChallenge(challenge);
        }


        private void manageRules(Challenge challenge, Player player1, Player player2) {
                String[] options = { "Manage Player 1", "Manage Player 2", "Exit" };
                int opt = MenuUtils.menu("Manage Challenge", options);

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
                        MenuUtils.setConfigLastAsZero(true);
                        int opt = MenuUtils.menu("Manage Player", options);

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
