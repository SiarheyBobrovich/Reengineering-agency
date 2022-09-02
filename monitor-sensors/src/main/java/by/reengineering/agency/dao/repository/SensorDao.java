package by.reengineering.agency.dao.repository;

import by.reengineering.agency.dao.repository.api.SensorRepository;
import by.reengineering.agency.dao.repository.exceptions.DataSourceException;
import by.reengineering.agency.entity.Sensor;
import org.springframework.stereotype.Repository;
import by.reengineering.agency.entity.Range;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class SensorDao implements SensorRepository<Sensor, UUID> {

    private final DataSource dataSource;

    public SensorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Sensor sensor) {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO sensor.sensors(uuid, name, model, range_from, range_to, type_id, unit_id, location, description) VALUES (?, ?, ? , ?, ?, ?, ?, ?, ?)")) {
                Long typeId = findTypeId(sensor.getType());

                statement.setObject(1, sensor.getUuid());
                statement.setString(2, sensor.getName());
                statement.setString(3, sensor.getModel());
                statement.setDouble(4, sensor.getRange().getFrom());
                statement.setDouble(5, sensor.getRange().getTo());
                statement.setLong(6, typeId);
                statement.setLong(7, Objects.isNull(sensor.getUnit()) ? typeId : findUnitId(sensor.getUnit()));
                statement.setString(8, sensor.getLocation());
                statement.setString(9, sensor.getDescription());

                statement.execute();
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public void update(Sensor sensor) {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("UPDATE sensor.sensors SET name=?, model=?, range_from=?, range_to=?, type_id=?, unit_id=?, location=?, description=? WHERE uuid = ?;")) {
                Long typeId = findTypeId(sensor.getType());

                statement.setString(1, sensor.getName());
                statement.setString(2, sensor.getModel());
                statement.setDouble(3, sensor.getRange().getFrom());
                statement.setDouble(4, sensor.getRange().getTo());
                statement.setLong(5, typeId);
                statement.setLong(6, Objects.isNull(sensor.getUnit()) ? typeId : findUnitId(sensor.getUnit()));
                statement.setString(7, sensor.getLocation());
                statement.setString(8, sensor.getDescription());
                statement.setObject(9, sensor.getUuid());

                statement.execute();
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try(final Connection connection = dataSource.getConnection()){
            try(final PreparedStatement statement = connection.prepareStatement("DELETE FROM sensor.sensors s WHERE s.uuid = ?;")) {
                statement.setObject(1, id);

                statement.execute();
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    @Override
    public boolean isExist(UUID id) {
        try(final Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT s.uuid FROM sensor.sensors s WHERE s.uuid = ?")) {
                statement.setObject(1, id);

                ResultSet resultSet = statement.executeQuery();

                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }

    private Long findTypeId(String typeName) {
        final long id;

        try(final Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT t.id FROM sensor.types t WHERE t.type = ?")) {
                statement.setString(1, typeName);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }else {
                    throw new DataSourceException("Type has not found");
                }
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }

        return id;
    }

    private Long findUnitId(String unitName) {
        final long id;

        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT u.id FROM sensor.units u WHERE u.unit = ?")) {
                statement.setString(1, unitName);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }else {
                    throw new DataSourceException("Unit has not found");
                }
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());

        }

        return id;
    }

    @Override
    public List<Sensor> getAllSensors() {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT s.uuid, s.name, s.model, s.range_from, s.range_to, t.type, u.unit, s.location, s.description\n" +
                    "\tFROM sensor.sensors s\n" +
                    "\tJOIN sensor.types t ON t.id = s.type_id\n" +
                    "\tJOIN sensor.units u ON u.id = s.unit_id;")) {

                ResultSet resultSet = statement.executeQuery();

                return map(resultSet);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());

        }
    }

    @Override
    public List<Sensor> findSensorsByText(final String text) {
        final String likeAny = "%" + text + "%";

        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT s.uuid, s.name, s.model, s.range_from, s.range_to, t.type, u.unit, s.location, s.description\n" +
                            "\tFROM sensor.sensors s\n" +
                            "\tJOIN sensor.types t ON t.id = s.type_id\n" +
                            "\tJOIN sensor.units u ON u.id = s.unit_id\n" +
                            "\tWHERE \n" +
                            "\t\ts.name LIKE ? OR\n" +
                            "\t\ts.model LIKE ? OR\n" +
                            "\t\tt.type LIKE ? OR\n" +
                            "\t\tu.unit LIKE ? OR\n" +
                            "\t\ts.location LIKE ? OR\n" +
                            "\t\ts.description LIKE ?\n" +
                            "\t;")
            ) {

                for (int statementIndex = 1; statementIndex < 7; statementIndex++) {
                    statement.setString(statementIndex, likeAny);
                }

                ResultSet resultSet = statement.executeQuery();

                return map(resultSet);
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());

        }
    }

    private List<Sensor> map(ResultSet resultSet) throws SQLException {
        final List<Sensor> sensors = new ArrayList<>();

        while (resultSet.next()) {
            sensors.add(Sensor.builder()
                            .uuid(UUID.fromString(resultSet.getString("uuid")))
                            .name(resultSet.getString("name"))
                            .model(resultSet.getString("model"))
                            .range(new Range(resultSet.getDouble("range_from"), resultSet.getDouble("range_to")))
                            .type(resultSet.getString("type"))
                            .unit(resultSet.getString("unit"))
                            .location(resultSet.getString("location"))
                            .description(resultSet.getString("description"))
                    .build());
        }

        return sensors;
    }
}
