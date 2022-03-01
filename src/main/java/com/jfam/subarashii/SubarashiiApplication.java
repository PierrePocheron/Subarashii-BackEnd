package com.jfam.subarashii;

import com.jfam.subarashii.services.GenreService;
import com.jfam.subarashii.utils.Constantes;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
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
		Constantes.Token_value.JWT_SECRET_KEY = secretKey;
	}
	@Value("${cors.authorize.adress.front}")
	public void setFrontAdress(String adressFront) {
		Constantes.ADRESS_FRONT = adressFront;
	}

	@Value("${token.minute.validation}")
	public void setTokenTime(Integer minuteTokenValidation) {
		Constantes.Token_value.MINUTE_VALIDATION = minuteTokenValidation;
	}

	@Value("${api.movie.token}")
	public void setTokenTime(String tokenKey) {
		Constantes.ApiMovie.TOKEN_SECRET = tokenKey;
	}


	@Value("${environnement.type}")
	public void setEnvironementType(String environnementType) {
		Constantes.ENVIRONNEMENT_TYPE = environnementType;
	}


/*	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SubarashiiApplication.class);
	}*/
}
