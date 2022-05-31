package com.jfam.subarashii;

import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class SubarashiiApplication extends SpringBootServletInitializer{

	public static void main(String[] args)  {
		SpringApplication.run(SubarashiiApplication.class, args);
	}

	@Value("${spring.jwt.secret.key}")
	public static void setSecretKey(String secretKey) {
		Constantes.Token_value.JWT_SECRET_KEY = secretKey;
	}

	@Value("${cors.authorize.adress.front}")
	public static void setFrontAdress(String adressFront) {
		Constantes.ADRESS_FRONT = adressFront;
	}

	@Value("${token.minute.validation}")
	public static void setTokenTime(Integer minuteTokenValidation) {
		Constantes.Token_value.MINUTE_VALIDATION = minuteTokenValidation;
	}

	@Value("${api.movie.token}")
	public static void setTokenTime(String tokenKey) {
		Constantes.ApiMovie.TOKEN_SECRET = tokenKey;
	}


	@Value("${environnement.type}")
	public static void setEnvironementType(String environnementType) {
		Constantes.ENVIRONNEMENT_TYPE = environnementType;
	}


	@Value("${build.version}")
	public static void getBuildVersion(String version) {
		Constantes.BUILD_VERSION = version;
	}



}
