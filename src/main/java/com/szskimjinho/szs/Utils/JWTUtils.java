package com.szskimjinho.szs.Utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.h2.security.SHA256;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtils {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.expiretime}")
    private long exprireTime;

    private Key key;
    @PostConstruct
    public void init(){
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String genJwt(String userName){
        Claims claims = Jwts.claims().setSubject(userName);
        Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+exprireTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public String getUserName(String jwt){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }

    public boolean validJwt(String jwt){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
