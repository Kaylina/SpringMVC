package com.elixir.homepage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elixir.homepage.dao.HomePageDao;
import com.elixir.homepage.domain.HomePageInfo;
import com.elixir.homepage.service.HomePageService;
@Service
public class HomePageServiceImp implements HomePageService {

	@Autowired 
	private HomePageDao homePageDao;

	public HomePageInfo findAllInfo(HomePageInfo homePageInfo) {
		return homePageDao.findAllInfo(homePageInfo);
	}

}
