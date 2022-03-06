package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.dto.UserDto;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResponseService responseService;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_CREATE)
    @PostMapping(value = "sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void SignUpUser(@RequestBody User user, HttpServletResponse res) throws IOException {
        boolean isValidateUser = User.validatorSignUp.test(user);

        if (!isValidateUser) {
            responseService.ErrorF(res, Constantes.ErrorMessage.SIGN_UP_NOT_VALID, HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, User.validatorSignUp.getErrors());
            return;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userFetching = userService.create(user);
        UserDto userDto = new UserDto(userFetching);
        responseService.SuccessF(res, Constantes.SuccessMessage.INSCRIPTION_OK, userDto);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_SIGN_IN )
    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void SignInUser(@RequestBody User user, HttpServletResponse res) throws IOException {
        if (user == null)
            return;

        User userFetching = userService.login(user);
        if (userFetching == null) {
            responseService.ErrorF(res, Constantes.ErrorMessage.AUTHENTIFICATION_NOT_OK, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        String token = jwtService.CreateToken(userFetching.getEmail(), userFetching.getRole());
        res.setHeader(Constantes.Token_value.AUTHORIZATION_HEADER, Constantes.Token_value.TOKEN_PREFIX + token);

        Map<String, String> result = new HashMap<>();
        result.put(Constantes.Keys.TOKEN, token);
        result.put(Constantes.Keys.USERNAME, userFetching.getUsername());
        result.put(Constantes.Keys.EMAIL, userFetching.getEmail());
        responseService.SuccessF(res, Constantes.SuccessMessage.CONNECTION_OK, result);
    }
}
