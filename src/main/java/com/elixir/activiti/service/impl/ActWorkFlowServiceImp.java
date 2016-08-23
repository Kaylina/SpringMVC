package com.elixir.activiti.service.impl;

import com.elixir.activiti.service.ActWorkFlowService;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 */
@Service
public class ActWorkFlowServiceImp implements ActWorkFlowService {

    private static Logger logger = LoggerFactory.getLogger(ActWorkFlowServiceImp.class);


    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;



    public String startActWorkFlow(String processKey, String businessKey,String startUserId, Map<String, Object> parameter){
        logger.info("************ 工作流准备启动 ************");
        logger.info("************ processKey: "+processKey+" ************");
        logger.info("************ BusinessKey: "+businessKey+" ************");
        String processId="";
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到 activiti:initiator 中（ ACT_HI_PROCINST → START_USER_ID_）
            identityService.setAuthenticatedUserId(startUserId);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey, parameter);
            processId = processInstance.getId();
            logger.info("************ 启动完成，processId: "+businessKey+" ************");
        }finally {
            identityService.setAuthenticatedUserId(null);
        }
        return processId;
    }
}
