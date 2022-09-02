package by.reengineering.agency.services.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails getUser(String login, String password);
}
