package SystemGame;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.nio.file.Paths;

public class XMLAdapter implements Adapter {

    public void saveFile(Object data, String filePath) {
        String finalPath = getFilePath(filePath);

        try {
            File file = new File(finalPath);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                if (!parent.mkdirs()) {
                    System.err.println("No se pudo crear el directorio: " + parent.getAbsolutePath());
                    return;
                }
            }

            try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
                encoder.writeObject(data);
                System.out.println("Archivo guardado exitosamente.");
            }

        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Object loadFile(String filePath) {
        String finalPath = getFilePath(filePath);
        System.out.println("Leyendo de: " + finalPath);

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(finalPath)))) {
            return decoder.readObject();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String getFilePath(String relativePath) {
        return Paths.get(System.getProperty("user.dir"), relativePath).toString();
    }
}
