package com.jfam.subarashii.utils;

import java.util.Arrays;
import java.util.List;

public class Constantes {
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
    public static final  String ROUTE_SIGN_IN = "/users/sign-in";
    public static final  String ROUTE_SIGN_UP = "/users/sign-up";
    public static final  String ROUTE_GET_ALL_SECRET_QUESTIONS = "/secretquestions";
    public static String ENVIRONNEMENT_TYPE;
    public static String ADRESS_FRONT;
    public static String BUILD_VERSION;

    public static final  String URL_IMAGE_NOT_FOUND = "https://espace-stockage.fra1.digitaloceanspaces.com/school/Master/Subarashii/not_found.jpg";

    public static final  String EMPTY_STRING = "";

    public static class Keys {
        public  static final  String USER = "user";
        public  static final String TOKEN = "token";
        public static final  String USERNAME = "username";
        public static final  String EMAIL = "email";
        public static final  String GENRES = "genres";
        public static final  String ROLE = "ROLE_";
    }

    public static class Claims {
        public static final  String EMAIL = "email";
        public static final  String USERNAME = "username";
        public static final  String ROLE = "role";
    }

    public static class DefaultList {
        public static final  String A_VOIR = "A voir";
        public static final  String EN_COURS = "En cours";
        public static final  String TERMINEE = "Terminée";
        public static final  String EN_ATTENTE = "En attente";
        public static final  String FAVORIS = "Favoris";
    }

    public static class ErrorMessage {
        public static final String TOKEN_EXPIRATION_DATE = "L'obtention de la date d'expiration n'a pas pû être effectué: ";
        public static final String TOKEN_CLAIMS = "L'obtention de la claim n'a pas pû être effectué: ";
        public static final String TOKEN_CREATE = "La création du token à échoué :";
        public static final String TOKEN_NOT_EXIST = "%s - Veuillez vous identifier";
        public static final String TOKEN_INVALIDE = "Le token est invalide";
        public static final String AUTHENTIFICATION_NOT_OK = "Email ou mots de passe incorrect";
        public static final String ANIME_NOT_FOUND = "Aucun animé a été trouvé ...";
        public static final String ERROR_UNIQUE_CONTRAINT_DATABASE = "L'information existe déjà dans la base de donnée";
        public static final String ANIME_NOT_FOUND_ON_PAGE = "Aucun animé n'a été trouvé à la page ";
        public static final String PARAMETER_NOT_EXPECTED = "Le paramètre n'est pas celui attendu ou ne possède pas de la valeur";
        public static final String NUMBER_FORMAT_NOT_OK = "Le paramètre attendait un nombre en entré et n'a pas reçu le bon format";
        public static final String PARAMETER_TYPE_METHOD_MISMATCH = "Le paramètre fournit ne corresponds pas au type du paramètre attendu";
        public static final String PARAMETER_NOT_VALID = "Le ou les paramètre fournis ne répondent pas aux critères attendus";
        public static final String DATABASE_ACCESS_RESSOURCE_USAGE_NOT_OK = "L'accès à la ressource de la base de donnée n'a pas pu être réalisé";
        public static final String REQUEST_REFUSED = "La requête n'a pas pu être accepté car elle ne corresponds pas au attente";
        public static final String CONSTRAINT_FIELD_NOT_OK = "Une contrainte n'a pas été respecté sur une ou plusieurs valeurs";
        public static final String NOT_UNIQUE_RESULT = "La requête a retourné plusieurs résultats alors qu'un seul était attentdu";
        public static final String ANY_PARAMETER_PROVIDED = "Aucun paramètre n'a été fournis";
        public static final String ERROR_ADD_ANIME_TO_USER_LIST = "La liste dans laquel vous souhaité ajouter l'animé n'existe pas ou n'appartient pas à l'utilisateur";
        public static final String ERROR_ADD_ANIME_ALSO_EXIST = "L'animé est déjà présent dans la liste";
        public static final String SIGN_UP_NOT_VALID = "Inscription incorrecte";
        public static final String RESOURCE_NOT_FOUND = "La ressource recherché n'a pas pu être trouvé";
        public static final String TOKEN_USER_NOT_EXIST = "L'utilisateur que vous tentez d'utiliser n'existe plus dans la base de donnée";
        public static final String GENRE_NOT_FOUND = "Le genre recherché n'a pas été trouvé";
        public static final String ERROR_PARSE = "Une erreur a eut lieu pendant le parcours de la méthode";
        public static final String EPISODE_NOT_FOUND = "Aucun épisode n'a été trouvé";
        public static final String ANY_ANIME_FETCH = "Aucune liste ne corresponds ou aucun animé n'a été trouvé";
        public static final String ANY_USER_FETCH = "Aucun utilisateur ne corresponds à l'utilisateur connecté";
        public static final String LIST_USER_NOT_EXIST_OR_CANT_DELETABLE = "La liste que vous souhaité supprimer n'existe pas ou n'est pas supprimable.";
        public static final String USER_LIST_NOT_FOUND = "La liste recherché n'existe pas ou n'appartient pas l'utilisateur";
        public static final String USER_LIST_NOT_CONTAIN_ANIME = "La liste ne contient pas d'animé";
        public static final String USER_LIST_ANIME_NOT_EXIST = "L'animé que vous souhaité supprimer n'existe pas dans la liste";
        public static final String USER_LIST_BAD_SAVE_AFTER_DELETE_ANIME = "La modification pendant la supression d'un animé dans la liste utilisateur n'as pas été correctement sauvegardé";
        public static final String EXCEPTION_USER_DOESNT_RIGHTS_ADMIN = "L'utilisateur ne possède pas les droits d'administrateurs";
        public static final String EXCEPTION_USER_DOESNT_EXISTS = "L'utilisateur n'existe pas";
        public static final String EXCEPTION_ANIMECOMMENT_DOESNT_EXISTS = "Le commentaire n'existe pas";
        public static final String EXCEPTION_ANIME_DOESNT_EXISTS = "L'animé n'existe pas";

    }

    public static class SuccessMessage {
        public static final String CONNECTION_OK = "Authentification ok bienvenue !";
        public static final String INSCRIPTION_OK = "L'inscription à été réalisé avec succès, veuillez vous connecter !";
        public static final String ADD_ANIME_ON_USER_LIST = "L'animé %s a correctement été ajouté à la liste %s!";
        public static final String EPISODE_FIND = "Les épisodes ont été trouvé";
        public static final String ANIME_FIND = "L'animé a été trouvé";
        public static final String ANIME_DISCOVER_OK = "La liste d'animé à découvrir a été trouvé";
        public static final String SEARCH_ANIME_FIND = "%d animé(s) ont été trouvé(s)";
        public static final String GENRE_HAS_FETCH = "Tous les genres ont été récupérés";
        public static final String USER_HAS_FETCH = "Tous les utilisateurs ont été récupérés";
        public static final String COMPLEXE_SEARCH_OK = "La recherche complexe de l'animé est ok";
        public static final String USER_FIND = "L'utilisateur à été trouvé";
        public static final String USER_LIST_GET_CURRENT_LIST = "La liste de l'utilisateur a correctement été récupéré";
        public static final String USER_LIST_CREATE_OK = "La liste custom a correctement été crée";
        public static final String FETCH_ALL_ID_API_ANIME_ON_ALL_USER_LIST = "récupération de tous les id api animés dans les listes de l'utilisateur: %d au total";
        public static final String GET_ALL_SECRET_QUESTIONS = "récupération de toutes les questions secretes: %d au total";
        public static final String GET_COUNT_USERS_ADMIN = "Récupération du nombre d'administrateurs : %d au total";
        public static final String GET_COUNT_USERS_USER = "Récupération du nombre d'utilisateurs : %d au total";
        public static final String GET_COUNT_COMMENTS = "Récupération du nombre de commentaires : %d au total";
        public static final String GET_COUNT_ANIMES = "Récupération du nombre d'animés : %d au total";
        public static final String GET_COUNT_EPISODES = "Récupération du nombre d'épisodes : %d au total";
        public static final String GET_COUNT_GENRES = "Récupération du nombre de genre : %d au total";
        public static final String EPISODE_REMOVE_VIEW = "L'épisode a correctement été retiré aux vus";
        public static final String EPISODE_ADD_VIEW = "L'épisode a correctement été ajouté aux vus";
        public static final String FETCH_EPISODE_VIEW_BY_ID_ANIME = "Il y a %d épisode(s) vu(s) récupéré(s) pour l'animé %d";
        public static final String CREATE_ANIME_COMMENT = "Commentaire bien enregistré";
        public static final String UPDATE_USER_USERNAME_OK = "Username de l'utilisateur mis à jour";
        public static final String UPDATE_USER_PASSWORD_OK = "Password de l'utilisateur mis à jour";
        public static final String ALL_ANIME_ON_LIST = "Vous avez récupéré %d animé(s) dans la liste";
        public static final String READ_USER_OK = "L'utilisateur connecté a bien été récupérer";
        public static final String DELETE_USERLIST_OK = "La liste a correctement été supprimé";
        public static final String DELETE_USER_OK = "L'utilisateur a correctement été supprimé";
        public static final String DELETE_ANIMECOMMENT_OK = "Le commentaire a correctement été supprimé";
        public static final String DELETE_ANIME_ON_USERLIST_OK = "L'animé a correctement été supprimé de la liste";
        public static final String GRANT_USER_ROLE_ADMIN_OK = "L'utilisateur a été gradé administrateur";
        public static final String GRANT_USER_ROLE_USER_OK = "L'utilisateur a été rétrogradé";
        public static final String TOKEN_EXPIRATION_DATE = "L'obtention de la date d'expiration n'a pas pû être effectué: ";
        public static final String TOKEN_CLAIMS = "L'obtention de la claim n'a pas pû être effectué: ";
        public static final String TOKEN_CREATE = "La création du token à échoué :";
        public static final String TOKEN_NOT_EXIST = "%s - Veuillez vous identifier";
        public static final String TOKEN_INVALIDE = "Le token est invalide";
        public static final String AUTHENTIFICATION_NOT_OK = "Email ou mots de passe incorrect";
        public static final String ANIME_NOT_FOUND = "Aucun animé a été trouvé ...";
        public static final String ERROR_UNIQUE_CONTRAINT_DATABASE = "L'information existe déjà dans la base de donnée";
        public static final String ANIME_NOT_FOUND_ON_PAGE = "Aucun animé n'a été trouvé à la page ";
        public static final String PARAMETER_NOT_EXPECTED = "Le paramètre n'est pas celui attendu ou ne possède pas de la valeur";
        public static final String NUMBER_FORMAT_NOT_OK = "Le paramètre attendait un nombre en entré et n'a pas reçu le bon format";
        public static final String PARAMETER_TYPE_METHOD_MISMATCH = "Le paramètre fournit ne corresponds pas au type du paramètre attendu";
        public static final String PARAMETER_NOT_VALID = "Le ou les paramètre fournis ne répondent pas aux critères attendus";
        public static final String DATABASE_ACCESS_RESSOURCE_USAGE_NOT_OK = "L'accès à la ressource de la base de donnée n'a pas pu être réalisé";
        public static final String REQUEST_REFUSED = "La requête n'a pas pu être accepté car elle ne corresponds pas au attente";
        public static final String CONSTRAINT_FIELD_NOT_OK = "Une contrainte n'a pas été respecté sur une ou plusieurs valeurs";
        public static final String NOT_UNIQUE_RESULT = "La requête a retourné plusieurs résultats alors qu'un seul était attentdu";
        public static final String ANY_PARAMETER_PROVIDED = "Aucun paramètre n'a été fournis";
        public static final String ERROR_ADD_ANIME_TO_USER_LIST = "La liste dans laquel vous souhaité ajouter l'animé n'existe pas ou n'appartient pas à l'utilisateur";
        public static final String ERROR_ADD_ANIME_ALSO_EXIST = "L'animé est déjà présent dans la liste";
        public static final String SIGN_UP_NOT_VALID = "Inscription incorrecte";
        public static final String RESOURCE_NOT_FOUND = "La ressource recherché n'a pas pu être trouvé";
        public static final String TOKEN_USER_NOT_EXIST = "L'utilisateur que vous tentez d'utiliser n'existe plus dans la base de donnée";
        public static final String GENRE_NOT_FOUND = "Le genre recherché n'a pas été trouvé";
        public static final String ERROR_PARSE = "Une erreur a eut lieu pendant le parcours de la méthode";
        public static final String EPISODE_NOT_FOUND = "Aucun épisode n'a été trouvé";
        public static final String ANY_ANIME_FETCH = "Aucune liste ne corresponds ou aucun animé n'a été trouvé";
        public static final String ANY_USER_FETCH = "Aucun utilisateur ne corresponds à l'utilisateur connecté";
        public static final String LIST_USER_NOT_EXIST_OR_CANT_DELETABLE = "La liste que vous souhaité supprimer n'existe pas ou n'est pas supprimable.";
        public static final String USER_LIST_NOT_FOUND = "La liste recherché n'existe pas ou n'appartient pas l'utilisateur";
        public static final String USER_LIST_NOT_CONTAIN_ANIME = "La liste ne contient pas d'animé";
        public static final String USER_LIST_ANIME_NOT_EXIST = "L'animé que vous souhaité supprimer n'existe pas dans la liste";
        public static final String USER_LIST_BAD_SAVE_AFTER_DELETE_ANIME = "La modification pendant la supression d'un animé dans la liste utilisateur n'as pas été correctement sauvegardé";
    }

    public static class Token_value {
        public static  String JWT_SECRET_KEY = null;
        public static  Integer MINUTE_VALIDATION;
        public static final String AUTHORIZATION_HEADER = "Authorization";
        public static final String TOKEN_PREFIX = "Bearer ";
    }

    public static class ApiMovie {
        public static  String TOKEN_SECRET;
        public static final int MAX_PAGE_FOR_DISCOVER_JAPAN_ANIMATION = 458;
        public static final int ANIMATION_ID_GENRE = 16;
        public static final String URL_IMAGE = "https://image.tmdb.org/t/p/w500";
        public static final String URL_IMAGE_ORIGINE = "https://image.tmdb.org/t/p/original";
        public static final String ROUTE_SERIES_DETAILS_BY_ID = "https://api.themoviedb.org/3/tv/%d?language=fr-Fr";
        public static final String ROUTE_SERIES_GET_EPISODE_BY_ID_ANIME_AND_SEASON = "https://api.themoviedb.org/3/tv/%d/season/%d?language=fr-Fr";
        /**
         * genre 16 = animation , original_language=ja = japan
         */
        public static final String ROUTE_SERIES_DISCOVER_ANIME = "https://api.themoviedb.org/3/discover/tv?with_genres=16&original_language=ja&page=%d&language=fr-Fr&sort_by=original_title.asc&original_language=ja";
        public static final String ROUTE_GENRE_ANIME = "https://api.themoviedb.org/3/genre/tv/list?language=fr-Fr";


        public static final String ROUTE_COMPLEXE_SEARCH_ANIME_WITHOUT_PARAMS = "https://api.themoviedb.org/3/discover/tv?language=fr-FR&with_genres=16&sort_by=original_title.asc";
        public static final String ROUTE_SIMPLE_SEARCH_ANIME_WITHOUT_PARAMS = "https://api.themoviedb.org/3/search/tv?language=fr-FR";

        public static final String OTHER_QUERY_PARAMS_SYNTAX = "&%s=%s";

        public static final String JSON_KEY_RESULT = "results";
        public static final String JSON_KEY_GENRE_IDS = "genre_ids";
        public static final String JSON_KEY_GENRES = "genres";
        public static final String JSON_KEY_ID = "id";
        public static final String JSON_KEY_EPISODES = "episodes";
    }

    public static class Swagger {
        public static final String SUMMARY_ANIME_GET_BY_ID_API = "Récupère un anime par son id api, s'il n'existe pas en bdd l'ajoute.";
        public static final String SUMMARY_GET_SEASON_BY_ID_API = "Récupère la saison d'un anime grâce à l'id api de l'anime et du numéro de la saison, ajoute en bdd les épisodes et l'anime s'il n'existe pas";
        public static final String SUMMARY_DISCOVER_ANIME = "Récupère 20 animés au hasard (se sert de la pagination de l'api)";
        public static final String SUMMARY_USER_LIST_GET_MY_LIST = "Retourne tous les listes de l'utilisateur courant";
        public static final String SUMMARY_USER_LIST_CREATE_LIST = "Permets de créer une liste personnalisé pour l'utilisateur courant";
        public static final String SUMMARY_USER_LIST_ADD_ANIME = "Ajoute un animé (grâce à son idapi) à la liste (int envoyé en paramètre) de l'utilisateur courant";
        public static final String SUMMARY_USER_CREATE = "Créer un utilisateur";
        public static final String SUMMARY_USER_READ = "Lire un utilisateur";
        public static final String SUMMARY_USER_CONNECTED_READ = "Lire l'utilisateur connecté";
        public static final String SUMMARY_USER_CONNECTED_PATCH_USERNAME = "Changer le nom d'utilisateur de l'utilisateur connecté";
        public static final String SUMMARY_USER_CONNECTED_PATCH_PASSWORD = "Changer le mot de passe de l'utilisateur connecté";
        public static final String SUMMARY_USER_GRANT_ROLE_ADMIN = "Grader le role d'un utilisateur vers admin";
        public static final String SUMMARY_USER_GRANT_ROLE_USER = "Retrograder le role d'un utilisateur";
        public static final String SUMMARY_USER_SIGN_IN = "Connexion d'un utilisateur";
    }


    public static final List<String> LIST_QUERY_PARAMS_FOR_SIMPLE_SEARCH = Arrays.asList(
            "first_air_date_year",
            "include_adult",
            "query",
            "page",
            "language");


    public static final List<String> LIST_QUERY_PARAMS_FOR_FULL_SEARCH = Arrays.asList(
            "language",
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
            "with_status",
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
            "without_companies");


    public static final String STARTMESSAGE = "\n" +
            "       ==================================== \n" +
            "          ______________    ____  ______\n" +
            "         / ___/_  __/   |  / __ \\/_  __/\n" +
            "         \\__ \\ / / / /| | / /_/ / / /   \n" +
            "        ___/ // / / ___ |/ _, _/ / /    \n" +
            "       /____//_/ /_/  |_/_/ |_| /_/     \n" +
            "       =================================== \n" +
            "=== %s en mode: %s ===";
}
