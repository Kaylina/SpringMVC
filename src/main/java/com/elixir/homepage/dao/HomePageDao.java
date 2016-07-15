package com.elixir.homepage.dao;

import com.elixir.common.dao.MyBatisDao;
import com.elixir.homepage.domain.HomePageInfo;

public interface HomePageDao extends MyBatisDao<HomePageInfo>{

	public HomePageInfo findAllInfo(HomePageInfo homePageInfo);
}
