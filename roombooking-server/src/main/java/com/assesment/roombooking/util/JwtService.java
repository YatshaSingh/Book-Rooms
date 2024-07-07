//package com.assesment.roombooking.util;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.stereotype.Component;
//
//
//import java.security.Key;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.function.Function;
//
//
//
//
//@Component
//public class JwtService {
//
//    @Value("${SECRET_key}")
//    private String SECRET;
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
//
//
//    private Map<String, String> previousTokenIds=new HashMap<>();
//    private Set<String> blacklist = Collections.synchronizedSet(new HashSet<>());
//
//
//    public String generateToken(Map<String, Object> claims,String userName) {
//        return createToken(claims, userName);
//    }
//
//    private String createToken(Map<String, Object> claims, String userName) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userName)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    public String refreshToken(String userName) {
//        return Jwts.builder()
//                .setSubject(userName)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 7 days
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//
//    }
//
//
//    private Key getSignKey() {
//        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parse(token);
//            return true;
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//
//        return false;
//    }
//
//
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSignKey()).build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//
//    public boolean isBlacklisted(String token) {
//        return blacklist.contains(token);
//    }
//
//
//    public void addToBlacklist(String token) {
//        blacklist.add(token);
//    }
//
//    public String getTokenId(String token) {
//        return token.substring(7);
//    }
//
//    public String getPreviousTokenId(String username) {
//        if (previousTokenIds.containsKey(username)) {
//            return previousTokenIds.get(username);
//        } else {
//            return null;
//        }
//    }
//
//    public void setPreviousTokenId(String username, String tokenId) {
//        previousTokenIds.put(username, tokenId);
//    }
//
//
//}