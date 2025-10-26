package com.example.UserManagement.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtUtil {
    private static final String SECRET_KEY_STRING="uaDlbqHcBwNiwhXyqvWUGprmx6Plf7na";

    private final SecretKey SECRET_KEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 ) )//one hour
                .signWith(SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, String username){
        return extractUsername(token).equals(username);

    }
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
}
