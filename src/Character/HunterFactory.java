package Character;

public class HunterFactory extends CharacterFactory {
    public Character createCharacter(Player player) {
        return new Hunter(player);
    }
}
