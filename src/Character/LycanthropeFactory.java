package Character;

import User.Player;

public class LycanthropeFactory implements CharacterFactory {
    public Character createCharacter(Player player) {
        return new Lycanthrope(player);
    }
}
