package by.reengineering.agency.dao.repository;

import by.reengineering.agency.dao.repository.api.TypeRepository;
import by.reengineering.agency.dao.repository.exceptions.DataSourceException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeDao implements TypeRepository {

    private final DataSource dataSource;

    public TypeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<String> getAllTypes() {
        final List<String> types = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT t.type FROM types t;")) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    types.add(resultSet.getString(1));
                }
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }

        return types;
    }

    @Override
    public List<String> getAllUnits() {
        final List<String> types = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("SELECT u.unit FROM units u;")) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    types.add(resultSet.getString(1));
                }
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }

        return types;
    }
}
