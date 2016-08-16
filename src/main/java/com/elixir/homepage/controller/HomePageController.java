package com.elixir.homepage.controller;

import com.elixir.homepage.domain.HomePageInfo;
import com.elixir.homepage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomePageController {

	@Autowired
	private HomePageService homePageService;


	@RequestMapping("/homepage")
	public ModelAndView TestMethod() {
		ModelAndView modeAndView = new ModelAndView();
		HomePageInfo homePageInfo = new HomePageInfo();
		HomePageInfo homePageInfo1 = homePageService.findAllInfo(homePageInfo);
		System.out.println("---" + homePageInfo1.getTitle());
		modeAndView.setViewName("/homepage/home");
		return modeAndView;
	}
}