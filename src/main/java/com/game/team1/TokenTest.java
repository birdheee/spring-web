package com.game.team1;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenTest {
	
	private static final Long EXPIRE_TIME = 100L;
	
	public static void main(String[] args) {
		Date date = new Date();
		Date expireDate = new Date(date.getTime() + EXPIRE_TIME);
		
		String keyStr = "12345678123456781234567812345678"; // 키로 토큰을 검증
		Key key = Keys.hmacShaKeyFor(keyStr.getBytes()); // 키 만들기
		String token = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setSubject("id") // 주제
				.setIssuedAt(date) // 발급일
				.setExpiration(expireDate) // 만료일
				.signWith(key, SignatureAlgorithm.HS256) // 사인 만들기, 알고리즘은 HS256
				.compact(); // 토큰 만들기
		
		log.info("token => {}", token);
		
		String subject = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		System.out.println(subject);
		
		}
}
