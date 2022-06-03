package com.jfam.subarashii.controllers;

import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.entities.SecretQuestion;
import com.jfam.subarashii.entities.User;
import com.jfam.subarashii.services.ResponseService;
import com.jfam.subarashii.services.SecretQuestionService;
import com.jfam.subarashii.services.StatsMetricsService;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        Integer countUsers = statsMetricsService.getCountUsersByRole(Role.USER.name());
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_USERS_USER,countUsers), countUsers);
    }

    @GetMapping("/count/users/admin")
    public void getCountUsersAdmin(HttpServletResponse res) throws IOException {
        Integer countUsers = statsMetricsService.getCountUsersByRole(Role.ADMIN.name());
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_USERS_ADMIN,countUsers), countUsers);
    }

    @GetMapping("/count/comments")
    public void getCountComments(HttpServletResponse res) throws IOException {
        Long countComments = statsMetricsService.getCountComments();
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_COMMENTS,countComments), countComments);
    }

    @GetMapping("/count/animes")
    public void getCountAnimes(HttpServletResponse res) throws IOException {
        Long countAnimes = statsMetricsService.getCountAnimes();
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_ANIMES,countAnimes), countAnimes);
    }

    @GetMapping("/count/episodes")
    public void getCountEpisodes(HttpServletResponse res) throws IOException {
        Long countEpisodes = statsMetricsService.getCountEpisodes();
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_EPISODES,countEpisodes), countEpisodes);
    }

    @GetMapping("/count/genres")
    public void getCountGenres(HttpServletResponse res) throws IOException {
        Long countGenres = statsMetricsService.getCountGenres();
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_GENRES,countGenres), countGenres);
    }

    @GetMapping("/current/count/anime/avoir")
    public void getCurrentUserCountAnimeAvoir(HttpServletRequest req,
                                              HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        Long countAnimeAvoir = statsMetricsService.getCurrentUserCountAnimeByStatus(Constantes.DefaultList.A_VOIR, currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_ANIME_A_VOIR,countAnimeAvoir), countAnimeAvoir);
    }

    @GetMapping("/current/count/anime/encours")
    public void getCurrentUserCountAnimeEncours(HttpServletRequest req,
                                              HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        Long countAnimeEncours = statsMetricsService.getCurrentUserCountAnimeByStatus(Constantes.DefaultList.EN_COURS, currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_ANIME_EN_COURS,countAnimeEncours), countAnimeEncours);
    }

    @GetMapping("/current/count/anime/attente")
    public void getCurrentUserCountAnimeEnAttente(HttpServletRequest req,
                                              HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        Long countAnimeAttente = statsMetricsService.getCurrentUserCountAnimeByStatus(Constantes.DefaultList.EN_ATTENTE, currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_ANIME_EN_ATTENTE,countAnimeAttente), countAnimeAttente);
    }

    @GetMapping("/current/count/anime/favoris")
    public void getCurrentUserCountAnimeFavoris(HttpServletRequest req,
                                              HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        Long countAnimeFavoris = statsMetricsService.getCurrentUserCountAnimeByStatus(Constantes.DefaultList.FAVORIS, currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_COUNT_ANIME_FAVORIS,countAnimeFavoris), countAnimeFavoris);
    }

    @GetMapping("/current/count/anime/terminee")
    public void getCurrentUserCountAnimeTerminee(HttpServletRequest req,
                                              HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        Long countAnimeTerminee = statsMetricsService.getCurrentUserCountAnimeByStatus(Constantes.DefaultList.TERMINEE, currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_CURRENT_USER_COUNT_ANIME_TERMINEE,countAnimeTerminee), countAnimeTerminee);
    }

    @GetMapping("/current/count/comments")
    public void getCurrentUserCountComments(HttpServletRequest req,
                                                 HttpServletResponse res) throws IOException {
        User currentUser = Helpers.getCurrentUser(req);

        if (currentUser == null) {
            responseService.errorF(res, Constantes.ErrorMessage.ANY_USER_FETCH, 404, false);
            return;
        }

        Long countComments = statsMetricsService.getCurrentUserCountComments(currentUser);
        responseService.successF(res, String.format(Constantes.SuccessMessage.GET_CURRENT_USER_COUNT_COMMENTS, countComments), countComments);
    }
}
