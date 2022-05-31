package com.jfam.subarashii.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfam.subarashii.MyRunner;
import com.jfam.subarashii.entities.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class ResponseService {
    ObjectMapper mapper;
    ResponseDTO responsedto;
    private static final Logger logger = LoggerFactory.getLogger(ResponseService.class);

    public void errorF(HttpServletResponse response, String errormessage, int errorstatut, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorstatut);
        responsedto = new ResponseDTO(errormessage,errorstatut,body);
        mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responsedto);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        logger.warn(errormessage);
    }

    public void successF(HttpServletResponse response, String successmessage, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        responsedto = new ResponseDTO(successmessage,HttpServletResponse.SC_OK,body);
        mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responsedto);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }
}
