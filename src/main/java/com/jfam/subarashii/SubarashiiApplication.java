package com.jfam.subarashii;

import com.jfam.subarashii.utils.Constantes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SubarashiiApplication extends SpringBootServletInitializer {

	@Value("${spring.jwt.secret.key}")
	public void setSecretKey(String secretKey) {
		Constantes.JWT_SECRET_KEY = secretKey;
	}


	public static void main(String[] args) {
		SpringApplication.run(SubarashiiApplication.class, args);
		System.out.println("JWT secret key: " + Constantes.JWT_SECRET_KEY);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SubarashiiApplication.class);
	}
}
