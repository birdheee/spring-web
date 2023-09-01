package com.game.team1.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.game.team1.util.JWTToken;

import lombok.extern.slf4j.Slf4j;

@WebFilter("/*")
@Slf4j
public class AuthFilter extends GenericFilter {
	
	@Autowired
	private JWTToken jwtToken;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		if(token != null) {
			log.info("userId=>{}", jwtToken.getUserIdFromToken(token));
		}
		log.info("token=>{}", token);
		chain.doFilter(request, response);
	}
	
}
