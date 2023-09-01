package com.game.team1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin("*") // 허락
public class TestController {
	@RequestMapping(value="/test/**", method=RequestMethod.GET) // @GetMapping과 같은 기능
	public String test() {
		return "str";
	}
	
	@RequestMapping(value="/api/list", method=RequestMethod.GET)
	@ResponseBody // 값 자체가 나오도록
	public List<String> getList(){
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("def");
		list.add("ghi");
		return list; // json 형태로
	}
	
	@GetMapping("/api/users")
	@ResponseBody
	public List<Map<String, String>> getUsers(){
		List<Map<String, String>> users = new ArrayList<>();
		for(int i=1; i<11; i++) {
			Map<String, String> user = new HashMap<>();
			user.put("name", "이름" + i);
			user.put("num", i + "");
			user.put("age", i + "");
			users.add(user);
		}
		return users;
	}
}
