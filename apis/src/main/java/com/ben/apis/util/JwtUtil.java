package com.ben.apis.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    // extract username from token
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    // check if token is expired
    private boolean isTokenExpired(String token) {
        Date expiration = getAllClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    // validate token (used in Gateway)
    public boolean isTokenValid(String token) {
        try {
            getAllClaimsFromToken(token); // throws exception if invalid
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // get all claims
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
