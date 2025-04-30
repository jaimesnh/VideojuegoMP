package SystemGame;

public interface Adapter {
    void saveFile(Object data, String path);
    Object loadFile(String path);
}
