package com.game.team1.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewsController {
	@RequestMapping(value="/views/**", method=RequestMethod.GET)
	public void page() {
		// URL의 앞뒤에 prefix, surfix를 붙임
	}
	
	// 두 가지 방식이 더 있음
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public String test1() {
		return "views/test1";
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public ModelAndView test2() {
		ModelAndView mv = new ModelAndView("view/test2");
		return mv;
	}
}
