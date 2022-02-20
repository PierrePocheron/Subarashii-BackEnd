package com.jfam.subarashii.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfam.subarashii.entities.dto.ResponseDTO;
import com.jfam.subarashii.utils.Constantes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class ResponseService {
    ObjectMapper mapper;
    ResponseDTO responsedto;

    public void ErrorF(HttpServletResponse response, String errormessage, int Errorstatut, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(Errorstatut);
        responsedto = new ResponseDTO(errormessage,Errorstatut,body);
        mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responsedto);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }

    public void SuccessF(HttpServletResponse response, String successmessage, Object body) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        responsedto = new ResponseDTO(successmessage,HttpServletResponse.SC_ACCEPTED,body);
        mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responsedto);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }
}
