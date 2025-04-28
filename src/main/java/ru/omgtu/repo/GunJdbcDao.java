package ru.omgtu.repo;

import ru.omgtu.model.Gun;
import ru.omgtu.model.ProductStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GunJdbcDao extends GenericDaoJdbc<Gun, Long> {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM guns";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM guns WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO guns (name, description, price, status, model, producer, img) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE guns SET name = ?, description = ?, price = ?, status = ?, model = ?, producer = ?, img = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM guns WHERE id = ?";


    public GunJdbcDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    protected List<Gun> parseResultSet(ResultSet rs) throws SQLException {
        List<Gun> result = new ArrayList<>();
        while (rs.next()) {
            result.add(mapResultSetToGun(rs));
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Gun gun) throws SQLException {
        setGunParameters(statement, gun);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Gun gun) throws SQLException {
        setGunParameters(statement, gun);
        statement.setLong(8, gun.getId());
    }

    private void setGunParameters(PreparedStatement statement, Gun gun) throws SQLException {
        statement.setString(1, gun.getName());
        statement.setString(2, gun.getDescription());
        statement.setBigDecimal(3, gun.getPrice());
        statement.setString(4, gun.getStatus().name());
        statement.setString(5, gun.getModel());
        statement.setString(6, gun.getProducer());
        statement.setString(7, gun.getImg());
    }

    private Gun mapResultSetToGun(ResultSet rs) throws SQLException {
        Gun gun = new Gun();
        gun.setId(rs.getLong("id"));
        gun.setName(rs.getString("name"));
        gun.setDescription(rs.getString("description"));
        gun.setPrice(rs.getBigDecimal("price"));
        gun.setStatus(ProductStatus.valueOf(rs.getString("status")));
        gun.setModel(rs.getString("model"));
        gun.setProducer(rs.getString("producer"));
        gun.setImg(rs.getString("img"));
        return gun;
    }

    @Override
    protected List<?> loadDependency(Long id) throws SQLException {
        return null; // No dependencies to load
    }

    @Override
    protected void saveDependencies(Gun object) throws SQLException {
        // No dependencies to save
    }
}
