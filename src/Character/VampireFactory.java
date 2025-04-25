package Character;

public class VampireFactory extends CharacterFactory {
    public Character createCharacter() {
        return new Vampire();
    }
}
