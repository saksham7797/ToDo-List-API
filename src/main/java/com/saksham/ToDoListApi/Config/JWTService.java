package com.saksham.ToDoListApi.Config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class JWTService {
    
    @Value("${jwt.secret}")
    private String secretKey;

    public String tokenGenerator(String email) {
        String token = Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 64800000)).signWith(getSignKey()).compact();

        return token;
    }   

    private Key getSignKey() {
        Key key = Keys.hmacShaKeyFor(getSecretKey().getBytes());
        return key;
    }

    private String getSecretKey() {
        return secretKey;
    }

    public String userNameExtractor(String token) {
        String email = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getSubject();
        return email;
    }

    private Date tokenExpirationextractor(String token) {
        Date date = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getExpiration();
        return date;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if(!userNameExtractor(token).equals(userDetails.getUsername())) return false;
        
        return new Date().before(tokenExpirationextractor(token));
    }
}
