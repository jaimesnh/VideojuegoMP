package Character;

import User.Player;

public class HunterFactory implements CharacterFactory {
    public Character createCharacter(Player player) {
        return new Hunter(player);
    }
}
