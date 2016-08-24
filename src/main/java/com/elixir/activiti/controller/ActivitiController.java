package com.elixir.activiti.controller;

import com.elixir.activiti.domain.BaseActBusinessBean;
import com.elixir.activiti.domain.DemoInfo;
import com.elixir.activiti.service.ActWorkFlowService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 */
@Controller
@RequestMapping("")
public class ActivitiController {
    private static Logger logger = LoggerFactory.getLogger(ActivitiController.class);
    @Autowired
    private ActWorkFlowService actWorkFlowService;

    @RequestMapping("/act")
    public ModelAndView TestMethod() {
        ModelAndView modeAndView = new ModelAndView();
        modeAndView.setViewName("/activitipage/applypage");
        return modeAndView;
    }


    @RequestMapping("/startact")
    @ResponseBody
    public String startWorkFlow(HttpServletRequest request) {
        DemoInfo demoInfo = new DemoInfo();
        ServletRequestDataBinder demoInfoBinder = new ServletRequestDataBinder(demoInfo);
        demoInfoBinder.bind(request);
        Map<String, Object> maps = new HashMap<String, Object>();
//TODO   自己业务类的service，处理自理的业务（数据库持久化之类）
        actWorkFlowService.startActWorkFlow("PC1", "1101", "kafeitu", maps);
        return "success";
    }

    @RequestMapping("/findTodoTasks")
    @ResponseBody
    public String findTodoTasks(HttpServletRequest request) {
        List<BaseActBusinessBean> results=actWorkFlowService.findTodoTasks("leaderuser");
        for (BaseActBusinessBean baseActBusinessBean : results) {
            logger.info("************ RunningProcess1" + baseActBusinessBean.getBusinessKey() + " ************");
            logger.info("************ RunningProcess2" + baseActBusinessBean.getProcessInstance().getProcessDefinitionId() + " ************");
            logger.info("************ RunningProcess3" + baseActBusinessBean.getProcessInstance().getProcessDefinitionKey() + " ************");
            logger.info("************ RunningProcess4" + baseActBusinessBean.getProcessInstance().getProcessDefinitionName() + " ************");
            logger.info("************ RunningProcess5" + baseActBusinessBean.getProcessDefinition().getDiagramResourceName() + " ************");
            logger.info("************ RunningProcess6" + baseActBusinessBean.getTask().getId() + " ************");
            HttpSession session = request.getSession();
            session.setAttribute("taskId", baseActBusinessBean.getTask().getId());
            logger.info("************---------------------------------------------------------************");
        }
        return "success";
    }
    @RequestMapping("/findTodoTasks2")
    @ResponseBody
    public String findTodoTasks2(HttpServletRequest request) {
        List<BaseActBusinessBean> results=actWorkFlowService.findTodoTasks("hruser");
        for (BaseActBusinessBean baseActBusinessBean : results) {
            logger.info("************ RunningProcess1" + baseActBusinessBean.getBusinessKey() + " ************");
            logger.info("************ RunningProcess2" + baseActBusinessBean.getProcessInstance().getProcessDefinitionId() + " ************");
            logger.info("************ RunningProcess3" + baseActBusinessBean.getProcessInstance().getProcessDefinitionKey() + " ************");
            logger.info("************ RunningProcess4" + baseActBusinessBean.getProcessInstance().getProcessDefinitionName() + " ************");
            logger.info("************ RunningProcess5" + baseActBusinessBean.getProcessDefinition().getDiagramResourceName() + " ************");
            logger.info("************ RunningProcess6" + baseActBusinessBean.getTask().getId() + " ************");
            HttpSession session = request.getSession();
            session.setAttribute("taskId", baseActBusinessBean.getTask().getId());
            logger.info("************---------------------------------------------------------************");
        }
        return "success";
    }


    @RequestMapping("/findRunningProcessInstaces")
    @ResponseBody
    public String findRunningProcessInstaces(HttpServletRequest request) {
        List<BaseActBusinessBean> results=actWorkFlowService.findRunningProcessInstaces("PC1");
        for (BaseActBusinessBean baseActBusinessBean : results) {
            logger.info("************ RunningProcess1" + baseActBusinessBean.getBusinessKey() + " ************");
            logger.info("************ RunningProcess2" + baseActBusinessBean.getProcessInstance().getProcessDefinitionId() + " ************");
            logger.info("************ RunningProcess3" + baseActBusinessBean.getProcessInstance().getProcessDefinitionKey() + " ************");
            logger.info("************ RunningProcess4" + baseActBusinessBean.getProcessInstance().getProcessDefinitionName() + " ************");
            logger.info("************ RunningProcess5" + baseActBusinessBean.getProcessDefinition().getDiagramResourceName() + " ************");
            logger.info("************---------------------------------------------------------************");
        }
        return "success";
    }

    @RequestMapping("/signforTask")
    @ResponseBody
    public String signforTask(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String taskId=(String)session.getAttribute("taskId");
        logger.info("************ signforTask" + taskId + " ************");
        actWorkFlowService.signforTask(taskId,"leaderuser");
        return "success";
    }

    @RequestMapping("/completeTask")
    @ResponseBody
    public String completeTask(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String taskId=(String)session.getAttribute("taskId");
        logger.info("************ signforTask2" + taskId + " ************");
        Map<String, Object> parameter=new HashMap<String, Object>();
        actWorkFlowService.completeTask(taskId, parameter);
        return "success";
    }


}
