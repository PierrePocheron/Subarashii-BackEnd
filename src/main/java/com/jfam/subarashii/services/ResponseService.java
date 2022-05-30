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

    public void ErrorF(HttpServletResponse response, String errormessage, int Errorstatut, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(Errorstatut);
        responsedto = new ResponseDTO(errormessage,Errorstatut,body);
        mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responsedto);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        logger.warn(errormessage);
    }

    public void SuccessF(HttpServletResponse response, String successmessage, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        responsedto = new ResponseDTO(successmessage,HttpServletResponse.SC_OK,body);
        mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responsedto);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }
}
