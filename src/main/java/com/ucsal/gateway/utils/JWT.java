package com.ucsal.gateway.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWT {
	private final String SECRET_API_KEY = "oLJ2KxFsXZNQMUpy6PftcghLybkY1FVkjlKRYuLrjLVgXC42gu4llkxycWwVre7PpnQUs77HXOJBc3P2SQk4HDwpPE4r69GgHlM0BnX0g1Bj3G6WV5I2gEiic9OXbnYA";
	
	private Key GetAuthKey() {
		return Keys.hmacShaKeyFor(SECRET_API_KEY.getBytes());
	}
	
	public String CreateToken(String identifier) {
		return Jwts.builder()
				.setSubject(identifier)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)))
				.signWith(GetAuthKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String FindIdentifier(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(GetAuthKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
	
	public boolean isTokenValid(String token, String identifier) {
        final String identify = FindIdentifier(token);
        return (identify.equals(identifier) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parserBuilder()
                .setSigningKey(GetAuthKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
