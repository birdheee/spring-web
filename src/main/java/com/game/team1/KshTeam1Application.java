package com.game.team1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan
@ServletComponentScan
public class KshTeam1Application {

	public static void main(String[] args) {
		SpringApplication.run(KshTeam1Application.class, args);
	}

}