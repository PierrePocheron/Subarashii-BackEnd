package com.jfam.subarashii.configs;

import com.jfam.subarashii.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@RestController
public class ErrorHandling extends ResponseEntityExceptionHandler {

    @Autowired
    ResponseService responseService;

    @ExceptionHandler(AccessDeniedException.class)
    public final void handleAccessDeniedException(Exception ex, HttpServletResponse response) throws IOException {
        responseService.ErrorF(response,ex.getMessage(),401,false);
    }
}
