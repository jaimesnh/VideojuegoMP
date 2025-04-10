package SystemGame;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XMLAdapter implements Adapter {

    private static final String FILE_NAME = "system_data.xml";

    public void save(SystemData systemData) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(FILE_NAME))) {
            encoder.writeObject(systemData);
            System.out.println("Datos guardados exitosamente en XML.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos.");
        }
    }

    public SystemData load() {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(FILE_NAME))) {
            SystemData data = (SystemData) decoder.readObject();
            System.out.println("Datos cargados correctamente desde XML.");
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos. Se devuelve uno nuevo.");
            return new SystemData();
        }
    }
}
