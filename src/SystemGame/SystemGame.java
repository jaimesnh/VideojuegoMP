package SystemGame;

public class SystemGame {
    private static SystemGame instance;
    private SystemData systemData;
    private Adapter adapter;

    private SystemGame() {
        adapter = new XMLAdapter();
        systemData = adapter.load();
    }
    public static SystemGame getInstance() {
        if (instance == null) {
            instance = new SystemGame();
        }
        return instance;
    }
}
