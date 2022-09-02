package by.reengineering.agency.dao.repository.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {
    UserDetails getUser(String login);
}
