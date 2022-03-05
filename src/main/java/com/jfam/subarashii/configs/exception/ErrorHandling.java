package com.jfam.subarashii.configs.exception;

import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@RestController
//extends ResponseEntityExceptionHandler si besoin mais supprimer method region overrideSpring.
public class ErrorHandling extends ResponseEntityExceptionHandler{

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
        responseService.ErrorF(res,Constantes.ErrorMessage.DATABASE_ACCESS_RESSOURCE_USAGE_NOT_OK,HttpServletResponse.SC_SERVICE_UNAVAILABLE,false);
    }

    @ExceptionHandler(ResourceApiNotFoundException.class)
    public final void ResourceApiNotFoundException(ResourceApiNotFoundException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,ex.getMessage(),HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(NumberFormatException.class)
    public final void NumberFormatException(NumberFormatException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,Constantes.ErrorMessage.NUMBER_FORMAT_NOT_OK,HttpServletResponse.SC_BAD_GATEWAY,false);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final void MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res, Constantes.ErrorMessage.PARAMETER_TYPE_METHOD_MISMATCH,HttpServletResponse.SC_BAD_GATEWAY,false);
    }

    @ExceptionHandler(RequestRejectedException.class)
    public final void RequestRejectedException(RequestRejectedException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,Constantes.ErrorMessage.REQUEST_REFUSED,HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(NonUniqueResultException.class)
    public final void RequestRejectedException(NonUniqueResultException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,Constantes.ErrorMessage.NOT_UNIQUE_RESULT,HttpServletResponse.SC_BAD_GATEWAY,false);
    }
    @ExceptionHandler(NullPointerException.class)
    public final void NullPointerException(NullPointerException ex, HttpServletResponse res) throws IOException {
        responseService.ErrorF(res,"Un élément s'est retrouvé non renseigner alors qu'il aurait du l'être" + ex.getMessage(),HttpServletResponse.SC_BAD_GATEWAY,false);
    }






    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public final void ConstraintViolationException(javax.validation.ConstraintViolationException ex, HttpServletResponse res) throws IOException {

        Set<ConstraintViolation<?>> violationExceptionSet =  ex.getConstraintViolations();

        // récupère toutes les contraintes non respectés lors de l'action
        // et les renvois dans le body
        Map<String,String> ContraintMap = new HashMap<>();
        violationExceptionSet.forEach(ves->{
            String contraintField =  ves.getPropertyPath().toString();
            String contraintText =  ves.getMessage();

            ContraintMap.put(contraintField,contraintText);
        });

        responseService.ErrorF(res,Constantes.ErrorMessage.CONSTRAINT_FIELD_NOT_OK, HttpServletResponse.SC_BAD_GATEWAY,ContraintMap);
    }

}
