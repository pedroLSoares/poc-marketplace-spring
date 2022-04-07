package com.pedrolsoares.marketplace.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.User;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTUtils {

    public static String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC512("secret".getBytes(StandardCharsets.UTF_8));

        return JWT
                .create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 300 * 60 * 100))
                .sign(algorithm);
    }
}
