package com.game.team1.util;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.game.team1.vo.UserInfoVO;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTToken {
	private final String JWT_TOKEN_KEY; // config 설정 값은 final로 둘 것
	private final long JWT_TOKEN_EXPIRE;
	
	public JWTToken(@Value("${jwt.token.key}") String jwtTokenKey, @Value("${jwt.token.expire}") long jwtTokenExpire) {
		this.JWT_TOKEN_KEY = jwtTokenKey;
		this.JWT_TOKEN_EXPIRE = jwtTokenExpire;
	}
	
	public String getJwtTokenKey() {
		return JWT_TOKEN_KEY;
	}
	
	public long getJwtTokenExpire() {
		return JWT_TOKEN_EXPIRE;
	}
	
	public String getToken(UserInfoVO user) {
		Date date = new Date();
		long expireDate = date.getTime() + JWT_TOKEN_EXPIRE;
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		log.info("key=>{}", key);
		
		// 빌드 패턴
		String token = Jwts.builder()
		.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
		.setSubject(user.getUiId())
		.setIssuedAt(date)
		.setExpiration(new Date(expireDate))
		.claim("user", user)
		.signWith(key, SignatureAlgorithm.HS256)
		.compact();
		
		return token;
	}
	
	public UserInfoVO validToken(String token) {
		Key key = Keys.hmacShaKeyFor(JWT_TOKEN_KEY.getBytes());
		try {
			Object obj = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parse(token)
			.getBody();
			log.info("obj=>{}", obj);
		}catch(ExpiredJwtException eje) {
		log.error("expired");
		}
		return null;
	}
}