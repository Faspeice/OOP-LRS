package ru.omgtu.repo;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

@NoArgsConstructor
public abstract class GenericDaoJdbc<T extends IdentifiedByPK<PK>, PK extends Serializable> {
    protected Connection connection;

    public GenericDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();
    public abstract String getCreateQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T entity) throws SQLException;
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T entity) throws SQLException;

    public T getByPK(PK id) throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, (Long) id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return list.iterator().next();
    }


    public List<T> getAll() throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return list;
    }

    public T persist(T object) throws SQLException {
        if (object.getPK() != null) {
            throw new SQLException("Object is already persist.");
        }

        saveDependencies(object);

        T persistInstance;

        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new SQLException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return persistInstance;
    }

    public void update(T object) throws SQLException {

        saveDependencies(object);
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public void delete(T object) throws SQLException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getPK());
            } catch (Exception e) {
                throw new SQLException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    protected abstract List<?> loadDependency(PK id) throws SQLException;
    protected abstract void saveDependencies(T object) throws SQLException;


}