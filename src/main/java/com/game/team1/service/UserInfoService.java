package com.game.team1.service;

import java.util.List;

import com.game.team1.vo.UserInfoVO;

public interface UserInfoService {
	List<UserInfoVO> getUserInfos(UserInfoVO user);
	UserInfoVO getUserInfo(int uiNum);
	UserInfoVO login(UserInfoVO user);
	int insertUserInfo(UserInfoVO user);
	int updateUserInfo(UserInfoVO user);
	int deleteUserInfo(int uiNum);
}