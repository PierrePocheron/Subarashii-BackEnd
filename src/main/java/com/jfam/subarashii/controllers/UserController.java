package com.jfam.subarashii.controllers;

import com.jfam.subarashii.configs.exception.ResourceApiNotFoundException;
import com.jfam.subarashii.entities.Genre;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.entities.dto.UserDto;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
    public void signUpUser(@RequestBody User user, HttpServletResponse res) throws IOException {
        boolean isValidateUser = User.validatorSignUp.test(user);

        if (!isValidateUser) {
            responseService.errorF(res, Constantes.ErrorMessage.SIGN_UP_NOT_VALID, HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, User.validatorSignUp.getErrors());
            return;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User userFetching;

        if (user.getSecretQuestion() != null) {
            userFetching = userService.create(user, user.getSecretQuestion());
        }
        else {
            userFetching = userService.create(user);
        }

        UserDto userDto = new UserDto(userFetching);
        responseService.successF(res, Constantes.SuccessMessage.INSCRIPTION_OK, userDto);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_SIGN_IN )
    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void signInUser(@RequestBody User user, HttpServletResponse res) throws IOException {
        if (user == null)
            return;

        User userFetching = userService.login(user);
        if (userFetching == null) {
            responseService.errorF(res, Constantes.ErrorMessage.AUTHENTIFICATION_NOT_OK, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        String token = jwtService.createToken(userFetching.getEmail(), userFetching.getRole(), userFetching.getUsername());
        res.setHeader(Constantes.Token_value.AUTHORIZATION_HEADER, Constantes.Token_value.TOKEN_PREFIX + token);

        Map<String, String> result = new HashMap<>();
        result.put(Constantes.Keys.TOKEN, token);
        result.put(Constantes.Keys.USERNAME, userFetching.getUsername());
        result.put(Constantes.Keys.EMAIL, userFetching.getEmail());
        responseService.successF(res, Constantes.SuccessMessage.CONNECTION_OK, result);
    }

    @GetMapping(value = "/idapianimes")
    public void getAllAnimeOnAllUserLists(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);
        List<Long> idApiAnimeList = userService.getAllIdApiAnimeOnUserList(currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.FETCH_ALL_ID_API_ANIME_ON_ALL_USER_LIST,idApiAnimeList.size()), idApiAnimeList);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_READ)
    @GetMapping(value = "/{iduser}")
    public void getUserById(@PathVariable long iduser, HttpServletResponse res) throws IOException {
        User user = userService.getByIdUser(iduser);
        responseService.successF(res,Constantes.SuccessMessage.USER_FIND, user);
    }

    @GetMapping("/all")
    public void GetAllGenres(HttpServletResponse res) throws IOException, ResourceApiNotFoundException {
        List<User> userList = userService.getAll();
        responseService.successF(res, Constantes.SuccessMessage.USER_HAS_FETCH, userList);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_CONNECTED_READ)
    @GetMapping(value = "/current")
    public void getUserConnected(HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        User user = new User(currentUser.getIdUser(), currentUser.getEmail(), currentUser.getRole(), currentUser.getUsername());

        responseService.successF(res, Constantes.SuccessMessage.READ_USER_OK, user);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_CONNECTED_PATCH_USERNAME)
    @PatchMapping(value = "/current/username")
    public void patchUsernameUserConnected(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        user.setIdUser(currentUser.getIdUser());
        User userPatched = userService.patchUsernameUserConnected(user);

        responseService.successF(res, Constantes.SuccessMessage.UPDATE_USER_USERNAME_OK, userPatched);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_CONNECTED_PATCH_PASSWORD)
    @PatchMapping(value = "/current/password")
    public void patchPasswordUserConnected(@RequestBody User user, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        user.setIdUser(currentUser.getIdUser());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userFetching = userService.patchPasswordUserConnected(user);
        UserDto userDto = new UserDto(userFetching);

        responseService.successF(res, Constantes.SuccessMessage.UPDATE_USER_PASSWORD_OK, userDto);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_GRANT_ROLE_ADMIN)
    @PatchMapping(value = "/{idUser}/role/admin")
    public void patchRoleUserGrantAdmin(@PathVariable(name = "idUser") Long idUser, @RequestBody User user, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        User userFetching = userService.patchRoleUserGrantAdmin(currentUser, idUser);
        UserDto userDto = new UserDto(userFetching);

        responseService.successF(res, Constantes.SuccessMessage.GRANT_USER_ROLE_ADMIN_OK, userDto);
    }

    @Operation(summary = Constantes.Swagger.SUMMARY_USER_GRANT_ROLE_USER)
    @PatchMapping(value = "/{idUser}/role/user")
    public void patchRoleUserGrantUser(@PathVariable(name = "idUser") Long idUser, @RequestBody User user, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        User userFetching = userService.patchRoleUserGrantUser(currentUser, idUser);
        UserDto userDto = new UserDto(userFetching);

        responseService.successF(res, Constantes.SuccessMessage.GRANT_USER_ROLE_USER_OK, userDto);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable Long idUser, HttpServletRequest req, HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (userService.deleteUserById(currentUser, idUser)){
            responseService.successF(res,Constantes.SuccessMessage.DELETE_USER_OK, true);
        }
    }
}
