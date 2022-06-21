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
	public void setSecretKey(String secretKey) {
		Constantes.tokenValue.jwtSecretKey = secretKey;
	}

	@Value("${cors.authorize.adress.front}")
	public void setFrontAdress(String adressFront) {
		Constantes.adressFront = adressFront;
	}

	@Value("${token.minute.validation}")
	public void setTokenTime(Integer minuteTokenValidation) {
		Constantes.tokenValue.minuteValidation = minuteTokenValidation;
	}

	@Value("${api.movie.token}")
	public void setTokenTime(String tokenKey) {
		Constantes.ApiMovie.tokenSecret = tokenKey;
	}

	@Value("${environnement.type}")
	public void setEnvironementType(String environnementType) {
		Constantes.ENVIRONNEMENT_TYPE = environnementType;
	}

	@Value("${build.version}")
	public static void getBuildVersion(String version) {
		Constantes.buildVersion = version;
	}
}
