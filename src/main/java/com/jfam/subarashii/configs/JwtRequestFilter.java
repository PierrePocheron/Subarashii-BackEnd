package com.jfam.subarashii.configs;

import ch.qos.logback.classic.Logger;
import com.jfam.subarashii.entities.Roles;
import com.jfam.subarashii.services.JwtService;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import org.apache.catalina.Role;
import org.apache.catalina.realm.GenericPrincipal;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.security.Principal;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

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

        final boolean isUserAdmin= request.isUserInRole(Roles.ADMIN.toString());
        final boolean isUserStandard= request.isUserInRole(Roles.USER.toString());
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // alone route who as not filter
        String path = request.getRequestURI();
        return Constantes.ROUTE_AUTHENTICATE.equals(path);
    }
}