package com.ben.Access.Service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    public static SecretKey key = Keys.hmacShaKeyFor(JwtConfiguration.SECRET.getBytes());

    public String generateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roles = populateAuthorities(authorities);

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .claim("email",authentication.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
    }

    public String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();

        return String.valueOf(claims.get("email"));
    }

    public String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths = new HashSet<>();

        for(GrantedAuthority auth : authorities) {
            auths.add(auth.getAuthority());
        }

        return String.join(",", auths);
    }
}
