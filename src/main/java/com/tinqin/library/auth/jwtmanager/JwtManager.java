package com.tinqin.library.auth.jwtmanager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tinqin.library.auth.persistance.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtManager {
    private final String secret = "b8uet4sPagE389DxDHHjtbH6LeQ3bjTuaxTfM28tFWHYtLjJGNYRLwEbxN2Y5ogY";

    public String generateJwt(User user) {
        return JWT
                .create()
                .withClaim("id", String.valueOf(user.getId()))
                .withClaim("username", user.getUsername())
                .withExpiresAt(Instant.now().plus(5, ChronoUnit.MINUTES))
                .sign(Algorithm.HMAC256(secret));
    }

    public Boolean validate(String jwt) {

        try {
            JWT.require(Algorithm.HMAC256(secret))
                    .withClaimPresence("id")
                    .withClaimPresence("username")
                    .build()
                    .verify(jwt.substring(7));

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
