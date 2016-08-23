package com.elixir.activiti.controller;

import com.elixir.activiti.domain.DemoInfo;
import com.elixir.activiti.service.ActWorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 */
@Controller
@RequestMapping("")
public class ActivitiController {
    @Autowired
    private ActWorkFlowService actWorkFlowService;

    @RequestMapping("/act")
    public ModelAndView TestMethod() {
        ModelAndView modeAndView = new ModelAndView();
        modeAndView.setViewName("/activitipage/applypage");
        return modeAndView;
    }



    @RequestMapping("/start")
    @ResponseBody
    public String startWorkFlow(HttpServletRequest request) throws ClassNotFoundException {
        DemoInfo demoInfo=new DemoInfo();
        ServletRequestDataBinder demoInfoBinder = new ServletRequestDataBinder(demoInfo);
        demoInfoBinder.bind(request);
        Map<String,Object> maps=new HashMap<String,Object>();
//      TODO   自己业务类的service，处理自理的业务（数据库持久化之类）
//      处理完，在启动流程
        actWorkFlowService.startActWorkFlow("PC1","1101","kafeitu",maps);
        return "success";
    }

}
