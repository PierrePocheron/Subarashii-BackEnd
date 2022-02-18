package com.jfam.subarashii.utils;

import org.springframework.beans.factory.annotation.Value;

public class Constantes {

    @Value("${spring.jwt.secret.key}")
    public static String JWT_SECRET_KEY;


    public static class ErrorMessage {
        public static String TOKEN_EXPIRATION_DATE = "L'obtention de la date d'expiration n'a pas pû être effectué: ";
        public static String TOKEN_CLAIMS = "L'obtention de la claim n'a pas pû être effectué: ";
        public static String TOKEN_CREATE = "La création du token à échoué :";
    }
}
