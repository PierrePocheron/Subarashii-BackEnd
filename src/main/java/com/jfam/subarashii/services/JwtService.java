package com.jfam.subarashii.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jfam.subarashii.entities.Role;
import com.jfam.subarashii.utils.Constantes;
import com.jfam.subarashii.utils.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    Algorithm algorithm ;

    public String createToken(String email, String role, String username){
        role = role == null ? Role.USER.toString() : role;
        algorithm  = Algorithm.HMAC256(Constantes.Token_value.JWT_SECRET_KEY);
        Map<String, Object> headerClaims = new HashMap();
        headerClaims.put(Constantes.Claims.EMAIL, email);
        headerClaims.put(Constantes.Claims.ROLE, role);
        headerClaims.put(Constantes.Claims.USERNAME, username);
        try {
            return JWT.create()
                    .withHeader(headerClaims)
                    .withExpiresAt(Helpers.currentDatePlusMinutes(Constantes.Token_value.MINUTE_VALIDATION))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            logger.warn(Constantes.ErrorMessage.TOKEN_CREATE + exception.getMessage());
        }
        return null;
    }

    public boolean verifyToken(String token){
        try {
            algorithm  = Algorithm.HMAC256(Constantes.Token_value.JWT_SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return false;
        }
    }

    public Date getExpirationDate(String token){
        try {
            algorithm  = Algorithm.HMAC256(Constantes.Token_value.JWT_SECRET_KEY);
            DecodedJWT jwt =  JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException exception){
            //Invalid token
            logger.warn(Constantes.ErrorMessage.TOKEN_EXPIRATION_DATE + exception.getMessage());
            return null;
        }
    }

    public Claim getClaims(String token,String claimValue){
        try {
            algorithm  = Algorithm.HMAC256(Constantes.Token_value.JWT_SECRET_KEY);
            DecodedJWT jwt =  JWT.decode(token);
            return jwt.getHeaderClaim(claimValue);
        }

        catch (JWTDecodeException exception){
            //Invalid token
            logger.warn(Constantes.ErrorMessage.TOKEN_CLAIMS + exception.getMessage());
            return null;
        }
    }

}
