package edu.upc.prop.cluster125.persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe GestorPersistencia és responsable de gestionar l'emmagatzematge i recuperació d'objectes en format JSON.
 *
 * @param <U> Tipus de la clau utilitzada per emmagatzemar els elements.
 * @param <T> Tipus de l'element a emmagatzemar/recuperar.
 */
public class GestorPersistencia<U, T> {
    /**
     * Guarda un conjunt d'elements en un arxiu JSON.
     *
     * @param map      El mapa que conté els elements a desar.
     * @param filePath La ruta de l'arxiu on es desaran els elements.
     */
    public void saveElements(HashMap<U, T> map, String filePath) {
        // Crear carpeta si no existeix
        new File("./data/").mkdirs();

        JsonArray ja = new JsonArray();
        for (T e : map.values()) {
            Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = g.toJson(e);
            ja.add(json);
        }

        File fitxer = new File(filePath);
        try (FileWriter fw = new FileWriter(filePath)) {
            if (!fitxer.exists()) fitxer.createNewFile();
            fw.write(ja.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obté una llista d'elements des d'un arxiu JSON.
     *
     * @param elementType El tipus de l'element que es vol recuperar.
     * @param filePath    La ruta de l'arxiu on es troben els elements a recuperar.
     * @return Una llista d'elements recuperats des de l'arxiu JSON.
     */
    public ArrayList<T> getElements(Class<T> elementType, String filePath) {
        ArrayList<T> list = new ArrayList<>();
        if (!Files.exists(Paths.get(filePath))) return list;
        try (FileReader rd = new FileReader(filePath)) {
            Gson g = new Gson();
            String[] elements = g.fromJson(rd, String[].class);
            for (String obj : elements) {
                T e = g.fromJson(obj, elementType);
                list.add(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
