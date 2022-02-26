package com.jfam.subarashii.utils;

import org.springframework.beans.factory.annotation.Value;

public class Constantes {
    public final static String ENCODE = "UTF-8";
    public final static String ROUTE_SIGN_IN = "/users/sign-in";
    public final static String ROUTE_SIGN_UP = "/users/sign-up";

    public static class Claims{
        public final static String EMAIL= "email";
        public final static String ROLE= "pseudo";
    }

    public static class ErrorMessage {
        public final static String TOKEN_EXPIRATION_DATE = "L'obtention de la date d'expiration n'a pas pû être effectué: ";
        public final static String TOKEN_CLAIMS = "L'obtention de la claim n'a pas pû être effectué: ";
        public final static String TOKEN_CREATE = "La création du token à échoué :";
        public final static String TOKEN_NOT_EXIST = "Veuillez vous identifier";
        public final static String TOKEN_INVALIDE = "Le token est invalide";
        public final static String AUTHENTIFICATION_NOT_OK = "Email ou mots de passe incorrect";
        public final static String EMPTY_EMAIL_OR_PASSWORD = "Le mot de passe ou  ou mots de passe incorrect";
        public final static String ANIME_NOT_FOUND = "Aucun animé a été trouvé";
        public final static String ERROR_UNIQUE_CONTRAINT_DATABASE = "L'information existe déjà dans la base de donnée";
    }
    public static class SuccessMessage {
        public final static String CONNECTION_OK= "Authentification ok bienvenue !";
        public final static String INSCRIPTION_OK= "L'inscription à été réalisé avec succès, veuillez vous connecter !";

    }

    public static class Token_value{

        public static String JWT_SECRET_KEY;
        public static Integer MINUTE_VALIDATION;

        public final static String AUTHORIZATION_HEADER = "Authorization";
        public final static String TOKEN_PREFIX = "bearer ";

    }

    public static class ApiMovie{
        public static String TOKEN_SECRET ;
        public final static String URL_IMAGE="https://image.tmdb.org/t/p/w500";
        public final static String BASE_URL= "https://api.themoviedb.org/3/";
        public final static String TAG_SERIE= "tv/";
        public final static String TAG_PARAMETER= "?";
        public final static String PARAMETER_LANGUE_FR = "language=fr-Fr" ;

        public static String ROUTE_SERIES_DETAILS_BY_ID= "https://api.themoviedb.org/3/tv/%d?language=fr-Fr";
        public static String ROUTE_SERIES_GET_EPISODE_BY_ID_ANIME_AND_SEASON= "https://api.themoviedb.org/3/tv/%d/season/%d?language=fr-Fr";
    }
}
