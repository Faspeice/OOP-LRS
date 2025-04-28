package ru.omgtu.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.omgtu.model.Gun;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GunJsonRepository {
    private final ObjectMapper objectMapper;
    private final File dataFile;

    public GunJsonRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.writerWithDefaultPrettyPrinter();
        
        String projectRoot = System.getProperty("user.dir");
        File resourcesDir = new File(projectRoot, "src/main/resources/data");
        
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs();
        }
        
        this.dataFile = new File(resourcesDir, "guns.json");
    }

    public List<Gun> loadGunsFromFile() {
        try {
            if (dataFile.exists()) {
                return objectMapper.readValue(dataFile, 
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Gun.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void writeGunsToFile(List<Gun> guns) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, guns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 