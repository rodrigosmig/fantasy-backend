package br.com.backend.fantasygame.application.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import br.com.backend.fantasygame.domain.entity.User;
import br.com.backend.fantasygame.domain.service.ServicoToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ServicoTokenImpl implements ServicoToken {

    @Value("${fantasy.jwt.expiration}")
    private String expiration;

    @Value("${fantasy.jwt.secret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
            .setIssuer("Fantasy Game")
            .setSubject(user.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        
        return claims.getSubject();
    }    
}
