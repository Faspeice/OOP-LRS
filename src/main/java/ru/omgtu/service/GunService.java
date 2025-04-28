package ru.omgtu.service;

import ru.omgtu.repo.GunJsonRepository;

import java.util.List;

public class GunService {
    private final GunJsonRepository repository;

    public GunService(GunJsonRepository repository) {
        this.repository = repository;
    }

    public List<String> getAllGuns() {
        return repository.getAllGuns();
    }

    public void addGun(String gunJson) {
        repository.addGun(gunJson);
    }
} 