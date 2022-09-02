package by.reengineering.agency.controllers;

import by.reengineering.agency.controllers.api.UserAPI;
import by.reengineering.agency.services.api.UserService;
import by.reengineering.agency.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
@Tag(name = "Users")
public class UserController implements UserAPI {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String apiV1LoginGet() {
        return "login";
    }

    @Override
    public ResponseEntity<String> apiV1LoginPost(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        UserDetails user = userService.getUser(username, password);

        String token = "Bearer " + JwtTokenUtil.generateAccessToken(user);

        headers.set(HttpHeaders.AUTHORIZATION,token);

        return ResponseEntity.ok().headers(headers).body(token);
    }
}
