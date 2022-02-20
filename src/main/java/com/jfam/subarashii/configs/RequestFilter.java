package com.jfam.subarashii.configs;

import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
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


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, AuthenticationException {

        String token = request.getHeader(Constantes.Token_value.AUTHORIZATION_BEARER);

        if (token == null) {
            responseService.ErrorF(response, Constantes.ErrorMessage.TOKEN_NOT_EXIST, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }

        if (!jwtService.VerifyToken(token)) {
            responseService.ErrorF(response, Constantes.ErrorMessage.TOKEN_INVALIDE, HttpServletResponse.SC_UNAUTHORIZED, false);
            return;
        }
        // set user role
        String email = jwtService.getClaims(token,Constantes.Claims.EMAIL).asString();
        String role = jwtService.getClaims(token,Constantes.Claims.ROLE).asString();
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,
        AuthorityUtils.createAuthorityList("ROLE_"+ role));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals(Constantes.ROUTE_SIGN_UP)  || path.equals(Constantes.ROUTE_SIGN_IN);
    }
}