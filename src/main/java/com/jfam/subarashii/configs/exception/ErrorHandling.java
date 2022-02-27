package com.jfam.subarashii.configs.exception;

import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

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

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public final void createSQLExceptionException(SQLSyntaxErrorException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,ex.getMessage(),HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }

    @ExceptionHandler(SQLGrammarException.class)
    public final void SQLGrammarExceptionException(SQLGrammarException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,ex.getMessage(),HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public final void InvalidDataAccessResourceUsageExceptionException(InvalidDataAccessResourceUsageException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,"L'utilisation de la ressource dans la base de donnée n'a pas pu être fait",HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }

    @ExceptionHandler(ResourceApiNotFoundException.class)
    public final void InvalidDataAccessResourceUsageExceptionException(ResourceApiNotFoundException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,ex.getMessage(),HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(NumberFormatException.class)
    public final void InvalidDataAccessResourceUsageExceptionException(NumberFormatException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,"le paramètre attendait un chiffre et n'a pas reçu le bon format",HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final void InvalidDataAccessResourceUsageExceptionException(MethodArgumentTypeMismatchException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,"le paramètre fournit ne correponds pas au type du paramètre attendu",HttpServletResponse.SC_BAD_GATEWAY,false);
    }


}
