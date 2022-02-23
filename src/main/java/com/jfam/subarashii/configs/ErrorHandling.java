package com.jfam.subarashii.configs;

import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import com.sun.jdi.InternalException;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jca.endpoint.GenericMessageEndpointFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@RestController
public class ErrorHandling extends ResponseEntityExceptionHandler {

    @Autowired
    ResponseService responseService;


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final void SQLException(SQLIntegrityConstraintViolationException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res, Constantes.ErrorMessage.ERROR_UNIQUE_CONTRAINT_DATABASE,HttpServletResponse.SC_NOT_ACCEPTABLE, Helpers.SubstringBefore(ex.getMessage()," for"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final void handleAccessDeniedException(AccessDeniedException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,ex.getMessage(),401,false);
    }


}
