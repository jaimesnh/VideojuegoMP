package SystemGame;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.nio.file.Paths;

public class XMLAdapter implements Adapter {

    @Override
    public void save(Object data, String path) {
        String fullPath = getFullPath(path);
        File file = new File(fullPath);

        // Crear directorio si es necesario
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();

        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.writeObject(data);
            System.out.println("Archivo XML guardado en: " + fullPath);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo XML: " + e.getMessage());
        }
    }

    @Override
    public Object load(String path) {
        String fullPath = getFullPath(path);

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fullPath)))) {
            return decoder.readObject();
        } catch (IOException e) {
            System.err.println("Error al leer archivo XML: " + e.getMessage());
            return null;
        }
    }

    private String getFullPath(String relativePath) {
        return Paths.get(System.getProperty("user.dir"), relativePath).toString();
    }
}
