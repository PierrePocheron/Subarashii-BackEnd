package com.jfam.subarashii.utils;

import java.util.Arrays;
import java.util.List;

public class Constantes {
    public final static String ENCODE = "UTF-8";
    public final static String ROUTE_SIGN_IN = "/users/sign-in";
    public final static String ROUTE_SIGN_UP = "/users/sign-up";
    public static String ENVIRONNEMENT_TYPE;
    public static String ADRESS_FRONT;
    public final static String URL_IMAGE_NOT_FOUND = "https://image.shutterstock.com/image-vector/no-image-available-vector-hand-600w-745639717.jpg";

    public static class Keys{
        public final static String USER_KEY= "user";
        public final static String USER_TOKEN= "token";
        public final static String USER_USERNAME= "username";
        public final static String USER_EMAIL= "email";
    }

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
        public final static String ANIME_NOT_FOUND = "Aucun animé a été trouvé ...";
        public final static String ERROR_UNIQUE_CONTRAINT_DATABASE = "L'information existe déjà dans la base de donnée";
        public final static String ANIME_NOT_FOUND_ON_PAGE = "Aucun animé n'a été trouvé à la page ";
        public final static String PARAMETER_NOT_EXPECTED = "Le paramètre n'est pas celui attendu ou ne possède pas de la valeur";
        public final static String NUMBER_FORMAT_NOT_OK = "Le paramètre attendait un nombre en entré et n'a pas reçu le bon format";
        public final static String PARAMETER_TYPE_METHOD_MISMATCH= "Le paramètre fournit ne corresponds pas au type du paramètre attendu";
        public final static String DATABASE_ACCESS_RESSOURCE_USAGE_NOT_OK= "L'accès à la ressource de la base de donnée n'a pas pu être réalisé";
        public final static String REQUEST_REFUSED= "La requête n'a pas pu être accepté car elle ne corresponds pas au attente";
        public final static String CONSTRAINT_FIELD_NOT_OK= "Une contrainte n'a pas été respecté sur une ou plusieurs valeurs";
        public final static String NOT_UNIQUE_RESULT = "La requête a retourné plusieurs résultats alors qu'un seul était attentdu";
        public final static String ANY_PARAMETER_PROVIDED = "Aucun paramètre n'a été fournis";
        public final static String ERROR_ADD_ANIME_TO_USER_LIST = "La liste dans laquel vous souhaité ajouter l'animé n'existe pas ou n'appartient pas à l'utilisateur";
        public final static String ERROR_ADD_ANIME_ALSO_EXIST = "L'animé est déjà présent dans la liste";
        public final static String SIGN_UP_NOT_VALID = "Inscription incorrecte";
    }

    public static class SuccessMessage {
        public final static String CONNECTION_OK= "Authentification ok bienvenue !";
        public final static String INSCRIPTION_OK= "L'inscription à été réalisé avec succès, veuillez vous connecter !";
        public static String ADD_ANIME_ON_USER_LIST= "L'animé %s a correctement été ajouté à la liste %s!";
        public final static String EPISODE_FIND= "Les épisodes ont été trouvé";
        public final static String ANIME_FIND= "L'animé a été trouvé";
        public final static String ANIME_DISCOVER_OK= "La liste d'animé à découvrir a été trouvé";
        public final static String SEARCH_ANIME_FIND= "%d animé(s) ont été trouvé(s)";
        public final static String GENRE_HAS_FETCH= "Tout les genres ont été récupérés";
        public final static String COMPLEXE_SEARCH_OK= "La recherche complexe de l'animé est ok";
        public final static String USER_LIST_GET_CURRENT_LIST= "La liste de l'utilisateur a correctement été récupéré";
        public final static String USER_LIST_CREATE_OK= "La liste custom a correctement été crée";
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
        public final static String ROUTE_SERIES_DETAILS_BY_ID= "https://api.themoviedb.org/3/tv/%d?language=fr-Fr";
        public final static String ROUTE_SERIES_GET_EPISODE_BY_ID_ANIME_AND_SEASON= "https://api.themoviedb.org/3/tv/%d/season/%d?language=fr-Fr";
        /**
         * genre 16 = animation , original_language=ja = japan
         */
        public final static String ROUTE_SERIES_DISCOVER_ANIME= "https://api.themoviedb.org/3/discover/tv?with_genres=16&original_language=ja&page=%d&language=fr-Fr&sort_by=original_title.asc";
        public final static String ROUTE_SEARCH_SIMPLE_SEARCH_ANIME= "https://api.themoviedb.org/3/search/tv?language=fr-FR&page=1&include_adult=false&query=%s";
        public final static String ROUTE_GENRE_ANIME= "https://api.themoviedb.org/3/genre/tv/list?language=fr-Fr";
        public final static int MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION= 458;
        public final static String ROUTE_SEARCH_COMPLEXE_SEARCH_ANIME= "https://api.themoviedb.org/3/discover/tv?with_genres=16&original_language=ja&page=%d&language=fr-Fr&sort_by=original_title.asc";
        public final static String ROUTE_SEARCH_COMPLEXE_SEARCH_ANIME_WITHOUT_PARAMS= "https://api.themoviedb.org/3/discover/tv";

        public final static String QUERY_PARAMS_SYNTAX = "&%s=%s";
        public final static String QUERY_PARAMS_SYNTAX_FIRST_PARAMS = "%s=%s";
        public final static String PARAMS_QUESTION_MARK = "?";
    }

    public static class Swagger{
        public final static String SUMMARY_ANIME_GET_BY_ID_API ="Récupère un anime par son id api, s'il n'existe pas en bdd l'ajoute.";
        public final static String SUMMARY_GET_SEASON_BY_ID_API ="Récupère la saison d'un anime grâce à l'id api de l'anime et du numéro de la saison, ajoute en bdd les épisodes et l'anime s'il n'existe pas";
        public final static String SUMMARY_DISCOVER_ANIME="Récupère 20 animés au hasard (se sert de la pagination de l'api)";
        public final static String SUMMARY_USER_LIST_GET_MY_LIST="Retourne tous les listes de l'utilisateur courant";
        public final static String SUMMARY_USER_LIST_CREATE_LIST= "Permets de créer une liste personnalisé pour l'utilisateur courant";
        public final static String SUMMARY_USER_LIST_ADD_ANIME= "Ajoute un animé (grâce à son idapi) à la liste (int envoyé en paramètre) de l'utilisateur courant";
        public final static String SUMMARY_USER_CREATE= "Créer un utilisateur";
        public final static String SUMMARY_USER_SIGN_IN= "Connexion d'un utilisateur";
    }

    public final static List<String> LIST_QUERY_PARAMS_FOR_FULL_SEARCH = Arrays.asList(
            "region",
            "sort_by",
            "certification_country",
            "certification",
            "certification.lte",
            "certification.gte",
            "include_adult",
            "include_video",
            "page",
            "primary_release_year",
            "primary_release_date.gte",
            "primary_release_date.gte",
            "release_date.gte",
            "release_date.lte",
            "with_release_type",
            "year",
            "vote_count.gte",
            "vote_count.lte",
            "vote_average.gte",
            "vote_average.lte",
            "with_cast",
            "with_crew",
            "with_people",
            "with_companies",
            "with_genres",
            "without_genres",
            "with_keywords",
            "without_keywords",
            "with_runtime.gte",
            "with_runtime.lte",
            "with_original_language",
            "with_watch_providers",
            "watch_region",
            "with_watch_monetization_types",
            "without_companies"
    );
}
