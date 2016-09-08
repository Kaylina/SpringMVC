package com.elixir.homepage.controller;

import com.elixir.homepage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
	@Autowired 
	private HomePageService homePageService;
	
	@RequestMapping("/test")
	public ModelAndView TestMethod(){
		ModelAndView modeAndView=new ModelAndView();
		System.out.println("------------------123456");
		modeAndView.setViewName("/common/index");
		return modeAndView;
	}

}
