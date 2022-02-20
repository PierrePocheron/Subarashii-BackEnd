package com.jfam.subarashii.controllers;

import com.github.uzrnem.verify.Validator;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jfam.subarashii.entities.User.validatorSignUp;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResponseService responseService;

    @Autowired
    JwtService jwtService;

    @PostMapping(value = "sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void SignUpUser(@RequestBody User user, HttpServletResponse res) throws IOException {

        boolean isValidateUser = User.validatorSignUp.test(user);

        if(!isValidateUser){
            responseService.ErrorF(res,"Inscription incorrecte",HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE,User.validatorSignUp.getErrors());
            return;
        }
        responseService.SuccessF(res,Constantes.SuccessMessage.INSCRIPTION_OK,true);
    }

    @PostMapping(value = "/sign-in",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void SignInUser(@RequestBody User user, HttpServletResponse res) throws IOException {
        if(user == null)
            return;

        //User userFetching = userService.login(user);
        User userFetching = new User();
        if(userFetching == null) {
            responseService.ErrorF(res, Constantes.ErrorMessage.AUTHENTIFICATION_NOT_OK, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        String token = jwtService.CreateToken(userFetching.getEmail(),userFetching.getRole());
        res.setHeader(Constantes.Token_value.AUTHORIZATION_BEARER,token);
        responseService.SuccessF(res,Constantes.SuccessMessage.CONNECTION_OK,true);
    }


}
