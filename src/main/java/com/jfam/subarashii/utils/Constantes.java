package com.jfam.subarashii.utils;

public class Constantes {
    public final static String ENCODE = "UTF-8";
    public final static String ROUTE_SIGN_IN = "/users/sign-in";
    public final static String ROUTE_SIGN_UP = "/users/sign-up";
    public static String ENVIRONNEMENT_TYPE;
    public final static String URL_IMAGE_NOT_FOUND = "https://image.shutterstock.com/image-vector/no-image-available-vector-hand-600w-745639717.jpg";

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
        public static String ADD_ANIME_ON_USER_LIST= "L'animé %s a correctement été ajouté à la liste %s!";

    }

    public static class Token_value{

        public static String JWT_SECRET_KEY;
        public static Integer MINUTE_VALIDATION;

        public final static String AUTHORIZATION_HEADER = "Authorization";
        public final static String TOKEN_PREFIX = "Bearer ";

    }

    public static class ApiMovie{
        public static String TOKEN_SECRET ;
        public final static String URL_IMAGE="https://image.tmdb.org/t/p/w500";
        public final static String BASE_URL= "https://api.themoviedb.org/3/";
        public final static String TAG_SERIE= "tv/";
        public final static String TAG_PARAMETER= "?";
        public final static String PARAMETER_LANGUE_FR = "language=fr-Fr" ;

        public final static String ROUTE_SERIES_DETAILS_BY_ID= "https://api.themoviedb.org/3/tv/%d?language=fr-Fr";
        public final static String ROUTE_SERIES_GET_EPISODE_BY_ID_ANIME_AND_SEASON= "https://api.themoviedb.org/3/tv/%d/season/%d?language=fr-Fr";
        /**
         * genre 16 = animation , original_language=ja = japan
         */
        public final static String ROUTE_SERIES_DISCOVER_ANIME= "https://api.themoviedb.org/3/discover/tv?with_genres=16&original_language=ja&page=%d&language=fr-Fr";
        public final static String ROUTE_SEARCH_ANIME_BY_NAME= "https://api.themoviedb.org/3/search/tv?language=fr-FR&page=1&include_adult=false&query=%s";
        public final static String ROUTE_GENRE_ANIME= "https://api.themoviedb.org/3/genre/tv/list?language=fr-Fr";

        public final static int MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION= 458;

    }
}
