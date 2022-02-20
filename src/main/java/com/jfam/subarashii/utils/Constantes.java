package com.jfam.subarashii.utils;

import org.springframework.beans.factory.annotation.Value;

public class Constantes {
    public final static String ENCODE = "UTF-8";
    public final static String ROUTE_AUTHENTICATE = "/users/login";

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
    }
    public static class SuccessMessage {
        public final static String AUTHENTIFICATION_OK= "Authentification ok bienvenue !";

    }

    public static class Token_value{

        public static String JWT_SECRET_KEY;
        public final static String AUTHORIZATION_BEARER = "Autorization-bearer";
        public static Integer MINUTE_VALIDATION;
    }
}
