package com.mokhtar.spring_security.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_key  = "c94b0d22bb3aa4b4245b0a1843d2ed65ce3953dd0878b4b3a9e254b9fff6bda5" ;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>() , userDetails);
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        String userName = extractUsername(token);
        System.out.println("is token valid  : "+userDetails.getUsername()+" \n user from jwt : "+userName);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token) ;
    }
    public boolean isTokenExpired(String token){
        if(extractExpiration(token).before(new Date(System.currentTimeMillis()))){
            return true;
        }else{
            return false;
        }
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }


    public String generateToken(Map<String,Object> extraClaims , UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24 ))
                .signWith(SignatureAlgorithm.HS256, getSignInKey()) // Use signWith directly
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim( String token , Function<Claims,T> claimResolver ){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
