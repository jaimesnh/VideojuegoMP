package Character;

import User.Player;

public interface CharacterFactory {
    public abstract Character createCharacter(Player player);
}