package ru.omgtu.service;

import ru.omgtu.model.Gun;
import ru.omgtu.repo.GunJdbcDao;

import java.sql.SQLException;
import java.util.List;

public class GunService {
    private final GunJdbcDao gunDao;

    public GunService(GunJdbcDao gunDao) throws SQLException {
        this.gunDao = gunDao;
    }

    public List<Gun> getAllGuns() throws SQLException {
        return gunDao.getAll();
    }

    public Gun getGunById(Long id) throws SQLException {
        return gunDao.getByPK(id);
    }

    public void addGun(Gun gun) throws SQLException {
        gunDao.persist(gun);
    }

    public boolean updateGun(Gun gun) {
        try {
            gunDao.update(gun);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteGun(Long id) {
        try {
            Gun gun = gunDao.getByPK(id);
            if (gun != null) {
                gunDao.delete(gun);
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }
}