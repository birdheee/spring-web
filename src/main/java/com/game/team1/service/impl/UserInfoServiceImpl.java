package com.game.team1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.team1.mapper.UserInfoMapper;
import com.game.team1.service.UserInfoService;
import com.game.team1.util.JWTToken;
import com.game.team1.vo.UserInfoVO;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private JWTToken jwtToken;
	
	@Override
	public List<UserInfoVO> getUserInfos(UserInfoVO user){
		return userInfoMapper.selectUserInfos(user);
	}
	
	@Override
	public UserInfoVO getUserInfo(int uiNum) {
		return userInfoMapper.selectUserInfo(uiNum);
	}
	
	@Override
	public UserInfoVO login(UserInfoVO user) {
		user = userInfoMapper.selectUserInfoByIDAndPwd(user);
		if(user != null) { // 로그인이 됨
			String token = jwtToken.getToken(user);
			user.setToken(token);
			user.setUiPwd(null);
		}
		return user;
	}
	
	@Override
	public int insertUserInfo(UserInfoVO user) {
		return userInfoMapper.insertUserInfo(user);
	}
	
	@Override
	public int updateUserInfo(UserInfoVO user) {
		return userInfoMapper.updateUserInfo(user);
	}
	
	@Override
	public int deleteUserInfo(int uiNum) {
		return userInfoMapper.deleteUserInfo(uiNum);
	}
}