package com.jfam.subarashii.configs;

import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.UserService;
import com.jfam.subarashii.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException, AuthenticationException {
        String header = req.getHeader(Constantes.Token_value.AUTHORIZATION_HEADER);

        if (header == null) {
            responseService.ErrorF(res, Constantes.ErrorMessage.TOKEN_NOT_EXIST, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }
        String token = header.replace(Constantes.Token_value.TOKEN_PREFIX,Constantes.EMPTY_STRING);
        if (!jwtService.VerifyToken(token)) {
            responseService.ErrorF(res, Constantes.ErrorMessage.TOKEN_INVALIDE, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }
        // set user role for security
        String email = jwtService.getClaims(token,Constantes.Claims.EMAIL).asString();

        User currrentUser = userService.getUserForFilterByEmail(email);
        if(currrentUser == null){
            responseService.ErrorF(res, Constantes.ErrorMessage.TOKEN_USER_NOT_EXIST, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        String role = jwtService.getClaims(token,Constantes.Claims.ROLE).asString();
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,
        AuthorityUtils.createAuthorityList(Constantes.Keys.ROLE + role));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // add user on request
        req.setAttribute(Constantes.Keys.USER , currrentUser);
        chain.doFilter(req, res);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        logger.info("Entre dans ShouldNotFilter");

        String path = request.getRequestURI();
        logger.info("Chemin courant" + path);

        if(Constantes.ENVIRONNEMENT_TYPE.equals("local")){

            return path.equals(Constantes.ROUTE_SIGN_UP) || path.equals(Constantes.ROUTE_SIGN_IN) ||
                    path.startsWith("/swagger-ui/") || path.startsWith("/api");
        }

        return path.equals(Constantes.ROUTE_SIGN_UP) || path.equals(Constantes.ROUTE_SIGN_IN);
    }
}