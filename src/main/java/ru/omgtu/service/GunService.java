package ru.omgtu.service;

import ru.omgtu.model.Gun;
import ru.omgtu.repo.GunJsonRepository;

import java.util.ArrayList;
import java.util.List;

public class GunService {
    private final GunJsonRepository repository;
    private List<Gun> guns;

    public GunService(GunJsonRepository repository) {
        this.repository = repository;
        this.guns = repository.loadGunsFromFile();
    }

    public List<Gun> getAllGuns() {
        return new ArrayList<>(guns);
    }

    public void addGun(Gun gun) {
        guns.add(gun);
        repository.writeGunsToFile(guns);
    }

    public void updateGun(Gun gun) {
        for (int i = 0; i < guns.size(); i++) {
            if (guns.get(i).getId().equals(gun.getId())) {
                guns.set(i, gun);
                repository.writeGunsToFile(guns);
                break;
            }
        }
    }

    public void deleteGun(String id) {
        guns.removeIf(gun -> gun.getId().equals(id));
        repository.writeGunsToFile(guns);
    }
} 