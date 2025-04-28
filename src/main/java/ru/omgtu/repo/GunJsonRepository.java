package ru.omgtu.repo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GunJsonRepository {
    private static final String FILE_PATH = "src/main/resources/data/guns.json";
    private final File file;

    public GunJsonRepository() {
        this.file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]");
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to create guns.json file", e);
            }
        }
    }

    public List<String> getAllGuns() {
        List<String> guns = new ArrayList<>();
        try (FileReader reader = new FileReader(file)) {
            StringBuilder content = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                content.append((char) ch);
            }
            String json = content.toString().trim();
            if (json.startsWith("[") && json.endsWith("]")) {
                json = json.substring(1, json.length() - 1);
                if (!json.isEmpty()) {
                    String[] gunArray = json.split("(?<=}),");
                    for (String gun : gunArray) {
                        guns.add(gun.trim());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read guns from file", e);
        }
        return guns;
    }

    public void addGun(String gunJson) {
        List<String> guns = getAllGuns();
        guns.add(gunJson);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("[" + String.join(",", guns) + "]");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write gun to file", e);
        }
    }
} 