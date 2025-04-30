package Character;

public enum CharacterSelection {
    LYCANTHROPE,
    VAMPIRE,
    HUNTER;

    private CharacterSelection() {
    }

    public String toString() {
        switch (this.ordinal()) {
            case 0 -> {
                return "Lycanthrope";
            }
            case 1 -> {
                return "Vampire";
            }
            case 2 -> {
                return "Hunter";
            }
            default -> {
                return "Unknown";
            }
        }
    }

    public static String[] allToString() {
        CharacterSelection[] characters = values();
        String[] characterNames = new String[characters.length];

        for(int i = 0; i < characters.length; ++i) {
            characterNames[i] = characters[i].toString();
        }

        return characterNames;
    }
}
