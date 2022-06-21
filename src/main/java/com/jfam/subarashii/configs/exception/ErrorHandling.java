package com.jfam.subarashii.configs.exception;

import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@RestController
//extends ResponseEntityExceptionHandler si besoin mais supprimer method region overrideSpring.
public class ErrorHandling extends ResponseEntityExceptionHandler{

    @Autowired
    ResponseService responseService;

    private static final Logger loggers = LoggerFactory.getLogger(ErrorHandling.class);

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final void sqlException(SQLIntegrityConstraintViolationException ex, HttpServletResponse res) throws IOException {
        loggers.error("SQLException: " + ex.getMessage());
        responseService.errorF(res, Constantes.ErrorMessage.ERROR_UNIQUE_CONTRAINT_DATABASE,HttpServletResponse.SC_NOT_ACCEPTABLE, Helpers.substringBefore(ex.getMessage()," for"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final void handleAccessDeniedException(AccessDeniedException ex, HttpServletResponse res) throws IOException {
        loggers.error("handleAccessDeniedException: " + ex.getMessage());
        responseService.errorF(res,ex.getMessage(),401,false);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public final void createSQLExceptionException(SQLSyntaxErrorException ex, HttpServletResponse res) throws IOException {
        loggers.error("SQLSyntaxErrorException: " + ex.getMessage());
        responseService.errorF(res,ex.getMessage(),HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }

    @ExceptionHandler(SQLGrammarException.class)
    public final void sqlGrammarExceptionException(SQLGrammarException ex, HttpServletResponse res) throws IOException {
        loggers.error("SQLGrammarExceptionException: " + ex.getMessage());
        responseService.errorF(res,ex.getMessage(),HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public final void invalidDataAccessResourceUsageExceptionException(InvalidDataAccessResourceUsageException ex, HttpServletResponse res) throws IOException {
        loggers.error("InvalidDataAccessResourceUsageException: " + ex.getMostSpecificCause());
        responseService.errorF(res,Constantes.ErrorMessage.DATABASE_ACCESS_RESSOURCE_USAGE_NOT_OK,HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }

    @ExceptionHandler(ResourceApiNotFoundException.class)
    public final void resourceApiNotFoundException(ResourceApiNotFoundException ex, HttpServletResponse res) throws IOException {
        loggers.error("ResourceApiNotFoundException: " + ex.getMessage());

        responseService.errorF(res,ex.getMessage(),HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(NumberFormatException.class)
    public final void numberFormatException(NumberFormatException ex, HttpServletResponse res) throws IOException {
        loggers.error("NumberFormatException: " + ex.getMessage());

        responseService.errorF(res,Constantes.ErrorMessage.NUMBER_FORMAT_NOT_OK,HttpServletResponse.SC_BAD_GATEWAY,false);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final void methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletResponse res) throws IOException {
        loggers.error("MethodArgumentTypeMismatchException: " +  ex.getMessage());

        responseService.errorF(res, Constantes.ErrorMessage.PARAMETER_TYPE_METHOD_MISMATCH,HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(ParseException.class)
    public final void parseException(ParseException ex, HttpServletResponse res) throws IOException {
        loggers.error("ParseException: " + ex.getMessage());

        responseService.errorF(res, Constantes.ErrorMessage.ERROR_PARSE,HttpServletResponse.SC_BAD_GATEWAY,false);
    }

    @ExceptionHandler(RequestRejectedException.class)
    public final void requestRejectedException(RequestRejectedException ex, HttpServletResponse res) throws IOException {
        loggers.error("RequestRejectedException: " + ex.getMessage());

        responseService.errorF(res,Constantes.ErrorMessage.REQUEST_REFUSED,HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(NonUniqueResultException.class)
    public final void requestRejectedException(NonUniqueResultException ex, HttpServletResponse res) throws IOException {
        loggers.error("NonUniqueResultException: " +  ex.getMessage());

        responseService.errorF(res,Constantes.ErrorMessage.NOT_UNIQUE_RESULT,HttpServletResponse.SC_BAD_GATEWAY,false);
    }

    @ExceptionHandler(CustomErrorMessageException.class)
    public final void customErrorMessageException(CustomErrorMessageException ex, HttpServletResponse res) throws IOException {
        loggers.error("CustomErrorMessageException: " + ex.getMessage());
        responseService.errorF(res,ex.getMessage(),HttpServletResponse.SC_BAD_GATEWAY,false);
    }



    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public final void constraintViolationException(javax.validation.ConstraintViolationException ex, HttpServletResponse res) throws IOException {

        Set<ConstraintViolation<?>> violationExceptionSet =  ex.getConstraintViolations();


        Map<String,String> contraintMap = new HashMap<>();
        violationExceptionSet.forEach(ves->{
            String contraintField =  ves.getPropertyPath().toString();
            String contraintText =  ves.getMessage();

            contraintMap.put(contraintField,contraintText);
        });

        responseService.errorF(res,Constantes.ErrorMessage.CONSTRAINT_FIELD_NOT_OK, HttpServletResponse.SC_BAD_GATEWAY,contraintMap);
    }

}
