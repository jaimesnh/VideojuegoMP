package Character;

import User.Player;

public class VampireFactory implements CharacterFactory {
    public Character createCharacter(Player player) {
        return new Vampire(player);
    }
}
