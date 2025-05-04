package Utils;

public class Const {

    public static final String DATA_PATH = "/data/game.xml";
    public static final String STATE_PATH = "/data/state.xml";

    public static final int INITIAL_GOLD = 500;

    public static final String[][] ARMORS = {
            // Nombre, Modificador de ataque, Modificador de defensa
            { "Armadura de cuero", "0", "1" },
            { "Armadura de escamas de dragon", "2", "1" },
            { "Armadura forjada de hierro", "0", "5" },
            { "Armadura de obsidiana", "0", "7" },
    };

    public static final String[][] WEAPONS = {
            // Nombre, Modificador de ataque, Modificador de defensa, Manos
            { "Escudo", "0", "3", "1" },
            { "Cuchillo", "1", "0", "1" },
            { "Lanza", "4", "0", "2" },
            { "Daga maldita", "4", "1", "1" }
    };

    public static final String[][] STRENGHTS = {
            // Nombre, Modificador
            { "Piel dura", "4" },
            { "Dia lluvioso", "1" },
            { "Luna roja", "3" },
            { "Dolor", "4" }
    };

    public static final String[][] WEAKNESSES = {
            // Nombre, Modificador
            { "Ruidos fuertes", "2" },
            { "Ambientes frios", "4" },
            { "Plata", "5" },
            { "Agua bendita", "3" }
    };

    public static final String[][] DONS = {
            // Nombre, Modificador de ataque, Modificador de defensa, Coste
            { "Furia instintiva", "2", "0", "1" },
            { "Garra espiritual", "3", "1", "3" },
            { "Rugido atemorizante", "0", "4", "2" },
            { "Furia lunar", "4", "2", "5" }
    };

    public static final String[][] DISCIPLINES = {
            // Nombre, Modificador de ataque, Modificador de defensa, Coste
            { "Celeridad sobrenatural", "3", "3", "5" },
            { "Dominacion mental", "2", "0", "4" },
            { "Fortaleza vampirica", "0", "3", "2" },
            { "Transformacion parcial", "4", "1", "5" }
    };

    public static final String[][] TALENTS = {
            // Nombre, Modificador de ataque, Modificador de defensa
            { "Maestro de armas", "3", "1" },
            { "Investigador", "3", "3" },
            { "Trampa improvisada", "2", "0" },
            { "Experto en monstruos", "0", "4" }
    };

    public static final String[][] GHOULS = {
            // Nombre, Salud, Dependencia
            { "Morzul", "1", "3" },
            { "Dumbor", "3", "1" },
            { "Gulshak", "4", "3" },
            { "Zargor", "2", "2" }
    };

    public static final String[][] HUMANS = {
            // Nombre, Salud, Lealtad
            { "Curandera", "1", "NORMAL" },
            { "Campesino", "1", "ALTA" },
            { "Espadachin", "2", "NORMAL" },
            { "Caballero", "3", "BAJA" }
    };

    public static final String[][] DEVILS = {
            // Nombre, Salud, Pacto
            { "Lilith", "2", "_" },
            { "Leviatan", "4", "_" },
            { "Behemoth", "1", "_" },
            { "Azazel", "3", "_" }
    };
}
