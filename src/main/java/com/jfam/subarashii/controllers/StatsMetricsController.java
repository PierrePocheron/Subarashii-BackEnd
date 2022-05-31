package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.SecretQuestionService;
import com.jfam.subarashii.services.StatsMetricsService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/statsmetrics")
@Tag(name = "StatsMetrics")
public class StatsMetricsController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private StatsMetricsService statsMetricsService;

    @GetMapping("/count/users/user")
    public void getCountUsersUser(HttpServletResponse res) throws IOException {
        Integer countUser = statsMetricsService.getCountUsersRole(Role.USER.name());
        responseService.SuccessF(res, String.format(Constantes.SuccessMessage.GET_ALL_USERS_USER,countUser), countUser);
    }

    @GetMapping("/count/users/admin")
    public void getCountUsersAdmin(HttpServletResponse res) throws IOException {
        Integer countUser = statsMetricsService.getCountUsersRole(Role.ADMIN.name());
        responseService.SuccessF(res, String.format(Constantes.SuccessMessage.GET_ALL_USERS_ADMIN,countUser), countUser);
    }
}
