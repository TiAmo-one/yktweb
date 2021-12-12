package com.team.yplus.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component("jwtTokenUtil")
public class JwtTokenUtil {
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireTime}")
    private Long expiration;
    @Value("${token.header}")
    private  String header;

    //创建token
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>(2);
        claims.put("sub",userDetails.getUsername());
        claims.put("created",new Date());

        return generateToken(claims);
    }

    public String generateToken(String username){
        Date expirationDate = new Date(new Date().getTime()+expiration);
        return Jwts.builder().setHeaderParam("typ","JWT").setSubject(username).setIssuedAt(new Date()).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512,secret).compact();
    }

    public String generateToken(Map<String,Object> claims){
        Date expirationDate = new Date(System.currentTimeMillis()+expiration);

        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    //从token中获取Claims
    private Claims getClaimsFromToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    //从token中获取该token的用户
    public String getUsernameFromToken(String token){
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    //判断token是否过期
    public Boolean isTokenExpired(String token){
        try{
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();

            return expiration.before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    //验证token
    public Boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }
}
