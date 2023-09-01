package com.game.team1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.team1.service.impl.UserInfoServiceImpl;
import com.game.team1.util.JWTToken;
import com.game.team1.vo.UserInfoVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class UserInfoController {
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	@Autowired
	private JWTToken jwtToken;
	
	@GetMapping("/valid")
	public UserInfoVO valid(@RequestParam("token") String token) {
		return jwtToken.validToken(token);
	}
	
	@GetMapping("/expire")
	public Long getExpire() {
		return jwtToken.getJwtTokenExpire();
	}
	
	// 0개 이상 검색(list)
	@GetMapping("/user-infos")
	public List<UserInfoVO> getUserInfos(@ModelAttribute UserInfoVO user){ // @ModelAttribute 생략 가능
		return userInfoServiceImpl.getUserInfos(user);
	}
	
	// 1개 이하 검색(one)
	@GetMapping("/user-infos/{uiNum}")
	public UserInfoVO getUserInfo(@PathVariable("uiNum") int uiNum) {
		log.info("uiNum=>{}", uiNum);
		return userInfoServiceImpl.getUserInfo(uiNum);
	}
	
	@PostMapping("/login")
	public UserInfoVO selectUserInfoByIDAndPwd(@RequestBody UserInfoVO user) {
		log.info("user=>{}", user);
		return userInfoServiceImpl.login(user);
	}
	
	// insert는 POST
	@PostMapping("/user-infos")
	public int insertUserInfo(@RequestBody UserInfoVO user) {
		log.info("user=>{}", user);
		return userInfoServiceImpl.insertUserInfo(user);
	}
	
	// update는 PUT
	@PutMapping("/user-infos")
	public int updateUserInfo(@RequestBody UserInfoVO user) {
		log.info("user=>{}", user);
		return userInfoServiceImpl.updateUserInfo(user);
	}
	
	// delete는 DELETE
	@DeleteMapping("/user-infos/{uiNum}")
	public int deleteUserInfo(@PathVariable int uiNum) {
		log.info("uiNum=>{}", uiNum);
		return userInfoServiceImpl.deleteUserInfo(uiNum);
	}
}