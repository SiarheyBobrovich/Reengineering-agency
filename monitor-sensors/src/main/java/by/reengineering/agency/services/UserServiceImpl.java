package by.reengineering.agency.services;

import by.reengineering.agency.dao.repository.api.UserRepository;
import by.reengineering.agency.dao.repository.exceptions.NotFoundException;
import by.reengineering.agency.services.api.UserService;
import by.reengineering.agency.services.exception.InvalidUserNameOrPasswordException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails getUser(String login, String password) {
        if (Strings.isEmpty(login) || Strings.isEmpty(password)) {
            throw new InvalidUserNameOrPasswordException();
        }

        final UserDetails user;

        try {
            user = repository.getUser(login);

        }catch (NotFoundException e) {
            throw new InvalidUserNameOrPasswordException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserNameOrPasswordException();
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUser(username);
    }
}
