package com.szskimjinho.szs.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.h2.security.SHA256;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JWTUtils {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.expiretime}")
    private long exprireTime;

    private Key key;
    @PostConstruct
    public void init(){
        String encodeKey=Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodeKey.getBytes());
    }

    public String genJwt(String userName){
        Claims claims = Jwts.claims().setSubject(userName);
        Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+exprireTime))
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserName(String jwt){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }

    public boolean validJwt(String jwt){
        log.debug("validJwt jwt  {}",jwt);
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            log.debug("validJwt true");
            return true;
        } catch (SignatureException ex) {
            log.debug("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.debug("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.debug("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.debug("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.debug("JWT claims string is empty");
        }
        return false;
    }

}
