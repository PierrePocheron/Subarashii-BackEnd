package com.jfam.subarashii.services;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.repositories.UserRepository;
import com.jfam.subarashii.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    ResponseService responseService;

    public void login(HttpServletResponse response, User user) throws IOException {
        if(user == null){
            return ;
        }
        logger.info("Tentative de login sur l'utilisateur: " + user.toString());
        User usr =  userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        usr = new User();
        if(usr == null){
            responseService.ErrorF(response,"Email ou mots de passe incorrect",HttpServletResponse.SC_UNAUTHORIZED,false);
            return;
        }

        String token = jwtService.CreateToken(user.getEmail(),user.getRole());

        response.setHeader(Constantes.Token_value.AUTHORIZATION_BEARER,token);

        responseService.SuccessF(response,"L'utilisateur c'est connecté correctement",true);
    }
}
