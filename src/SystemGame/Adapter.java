package SystemGame;

import java.util.Map;

public interface Adapter {
    void save(Object data, String path);
    Object load(String path);
}
