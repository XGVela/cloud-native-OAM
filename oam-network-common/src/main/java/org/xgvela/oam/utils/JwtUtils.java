package org.xgvela.oam.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {
    private static final long EXPIRE_TIME = 86400000L;

    public JwtUtils() {
    }

    public static boolean verify(String token, String userId, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userId", userId).build();
            verifier.verify(token);
            return true;
        } catch (Exception var6) {
            return false;
        }
    }

    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException var3) {
            return null;
        }
    }

    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException var2) {
            return null;
        }
    }

    public static String sign(String userId, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create().withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
        } catch (Exception var5) {
            return null;
        }
    }

}
