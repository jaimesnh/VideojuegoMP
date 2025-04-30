package Utils;

public class Const {

    // Constants for the file paths
    public static final String DATA_PATH = "/data/game.xml";
    public static final String STATE_PATH = "/data/state.xml";

    // Constants for the game rules
    public static final int INITIAL_GOLD = 500;

    // Default Available Armors
    public static final String[][] ARMORS = {
            // Name, Attack Modifier, Defense Modifier
            { "Leather Armor", "0", "1" },
            { "Chainmail Armor", "0", "2" },
            { "Plate Armor", "0", "3" },
            { "Dragon Armor", "1", "3" },
    };

    // Default Available Weapons
    public static final String[][] WEAPONS = {
            // Name, Attack Modifier, Defense Modifier, Hands
            { "Dagger", "1", "1", "1" },
            { "Sword", "2", "2", "1" },
            { "Axe", "3", "1", "2" },
            { "Dragon Slayer", "3", "2", "2" },
    };

    // Default Available Strenghts
    public static final String[][] STRENGHTS = {
            //Name, modifier
            { "Moonlight","4" },
            { "Darkness", "3" },
            { "Fire", "1" },
            { "Magic", "2" }

    };

    // Default Available Weaknesses
    public static final String[][] WEAKNESSES = {
            //Name, modifier
            {"Garlic","3"},
            { "Silver", "2" },
            { "Holy Water", "3" },
            { "Sunlight", "4" }
    };

    // Default Available Dones
    public static final String[][] DONES = {
            // Name, Attack Modifier, Defense Modifier, Cost
            { "Claw", "1", "1", "2" },
            { "Bite", "2", "2", "3" },
            { "Tail", "2", "3", "4" },
            { "Roar", "1", "2", "5" }
    };

    // Default Available Disciplines
    public static final String[][] DISCIPLINES = {
            // Name, Attack Modifier, Defense Modifier, Cost
            { "Bloodlust", "1", "3", "2" },
            { "Frenzy", "2", "1", "3" },
            { "Rage", "3", "2", "4" },
            { "Vampirism", "1", "3", "5" }
    };

    // Default Available Talents
    public static final String[][] TALENTS = {
            // Name, Attack Modifier, Defense Modifier, Cost
            { "Sneak", "1", "1" },
            { "Hide", "2", "2" },
            { "Backstab", "2", "3" },
            { "Assassinate", "1", "2" }
    };

    // Default Available Ghouls
    public static final String[][] GHOULS = {
            // Name, Health, Dependency
            { "Ghoul", "1", "1" },
            { "Vampire", "1", "2" },
            { "Werewolf", "2", "3" },
            { "Zombie", "2", "4" }
    };

    // Default Available Humans
    public static final String[][] HUMANS = {
            // Name, Health, Loyalty
            { "Knight", "1", "HIGH" },
            { "Archer", "1", "LOW" },
            { "Mage", "2", "REGULAR" },
            { "Peasant", "2", "LOW" },
            { "Rogue", "1", "HIGH" }
    };

    // Default Available Devils
    public static final String[][] DEVILS = {
            // Name, Health, Devils
            {"Lucifer", "3", "..."},
            {"Beelzebub", "3", "..."},
            {"Mephistopheles", "4", "..."},
            {"Satan", "4", "..."}
    };
}
