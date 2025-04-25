package Character;

public class HunterFactory extends CharacterFactory {
    public Character createCharacter() {
        return new Hunter();
    }
}
