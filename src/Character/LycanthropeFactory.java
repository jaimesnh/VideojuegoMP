package Character;

public class LycanthropeFactory extends CharacterFactory {
    public Character createCharacter(Player player) {
        return new Lycanthrope(player);
    }
}
