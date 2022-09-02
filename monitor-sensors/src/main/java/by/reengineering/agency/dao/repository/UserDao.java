package by.reengineering.agency.dao.repository;

import by.reengineering.agency.dao.repository.api.UserRepository;
import by.reengineering.agency.dao.repository.exceptions.DataSourceException;
import by.reengineering.agency.dao.repository.exceptions.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserDao implements UserRepository {

    private  final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDetails getUser(String login) {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement userStatement = connection.prepareStatement("" +
                    "SELECT u.username, u.password, u.enabled, a.authority\n" +
                    "FROM security.authorities a \n" +
                    "INNER JOIN security.users u ON\n" +
                    "a.username = u.username\n" +
                    "WHERE u.username = ?;");
               ) {
                userStatement.setString(1, login);

                final ResultSet resultSet = userStatement.executeQuery();
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
                final String username;
                final String password;
                final boolean isEnable;

                if (resultSet.next()) {
                    username = resultSet.getString("username");
                    password = resultSet.getString("password");
                    isEnable = resultSet.getBoolean("enabled");

                    do {
                        grantedAuthorities.add(new SimpleGrantedAuthority(resultSet.getString("authority")));
                    }while (resultSet.next());

                    return User.builder()
                            .username(username)
                            .password(password)
                            .disabled(!isEnable)
                            .authorities(grantedAuthorities)
                            .build();

                }else {
                    throw new NotFoundException("Not found");
                }
            }

        } catch (SQLException e) {
            throw new DataSourceException(e.getMessage());
        }
    }
}
