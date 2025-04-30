package Character;

public class VampireFactory extends CharacterFactory {
    public Character createCharacter(Player player) {
        return new Vampire(player);
    }
}
