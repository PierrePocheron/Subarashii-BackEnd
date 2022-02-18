package com.jfam.subarashii.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    String fakeToken = "eyJyb2xlIjoiZGV2ZWxvcGVyIiwidHlwIjoiSldUIiwicHNldWRvIjoiZGV2ZWxvcGVyIiwiYWxnIjoiSFMyNTYiLCJlbWFpbCI6ImRldmVsb3BlckB0ZXN0LmZyIn0.eyJleHAiOjE2NDUxMjk3Mjl9.HQY4eAd-7lSraLcI18gJYvv83l8ujW_oIJ8kH39qbSU";

    Algorithm algorithm  = Algorithm.HMAC256(Constantes.JWT_SECRET_KEY);

    public String CreateToken(String pseudo, String email, String role){
        Map<String, Object> headerClaims = new HashMap();
        headerClaims.put("pseudo", "developer");
        headerClaims.put("email", "developer@test.fr");
        headerClaims.put("role", "developer");
        try {
            return JWT.create()
                    .withHeader(headerClaims)
                    .withExpiresAt(Helpers.CurrentDatePlusMinutes(2))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            logger.warn(Constantes.ErrorMessage.TOKEN_CREATE + exception.getMessage());
        }
        return null;
    }

    public boolean VerifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(fakeToken);
            return true;
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            return false;
        }
    }

    public Date getExpirationDate(String token){
        try {
            DecodedJWT jwt =  JWT.decode(fakeToken);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException exception){
            //Invalid token
            logger.warn(Constantes.ErrorMessage.TOKEN_EXPIRATION_DATE + exception.getMessage());
            return null;
        }
    }

    public Claim getClaims(String token){
        try {
            DecodedJWT jwt =  JWT.decode(fakeToken);
            return jwt.getHeaderClaim("pseudo");
        }

        catch (JWTDecodeException exception){
            //Invalid token
            logger.warn(Constantes.ErrorMessage.TOKEN_CLAIMS + exception.getMessage());
            return null;
        }
    }

}
