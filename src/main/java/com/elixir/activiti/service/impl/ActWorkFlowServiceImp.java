package com.elixir.activiti.service.impl;

import com.elixir.activiti.domain.BaseActBusinessBean;
import com.elixir.activiti.service.ActWorkFlowService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 * activiti 主要接口，提供：
 *                         启动任务、
 *                         查询自己代办任务、
 *                         查询运行中 和 已完成流程、
 *                         签收任务、
 *                         完成任务、
 *                         查询任务详细数据。
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

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  9:52
     * Description: 启动工作流
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public String startActWorkFlow(String processKey, String businessKey, String startUserId, Map<String, Object> variables) {
        logger.info("************ 工作流准备启动 ************");
        logger.info("************ processKey: " + processKey + " ************");
        logger.info("************ businessKey: " + businessKey + " ************");
        String processId = "";
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到 activiti:initiator 中（DB： ACT_HI_PROCINST → START_USER_ID_）
            identityService.setAuthenticatedUserId(startUserId);
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, businessKey, variables);
            processId = processInstance.getId();
            logger.info("************ 启动完成，processId: " + processId + " ************");
        } finally {
            identityService.setAuthenticatedUserId(null);
        }
        return processId;
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  9:52
     * Description: 查询自己的代办任务
     * 业务层通过 {String businessKey = processInstance.getBusinessKey()} 获取业务主键，查询业务数据
     */
    public List<BaseActBusinessBean> findTodoTasks(String userId) {
        logger.info("************ 查询待办任务，用户ID：" + userId + " ************");
        List<BaseActBusinessBean> results = new ArrayList<BaseActBusinessBean>();
        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        List<Task> tasks = taskQuery.list();
        for (Task task : tasks) {
            BaseActBusinessBean baseActBusinessBean = new BaseActBusinessBean();
            String processInstanceId = task.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
            baseActBusinessBean.setTask(task);
            baseActBusinessBean.setProcessInstance(processInstance);
            baseActBusinessBean.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(baseActBusinessBean);
        }
        return results;
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  10:41
     * Description: 根据流程定义key查询 [运行中] 流程
     * 业务层通过：{ String businessKey = processInstance.getBusinessKey() }  获取业务主键。
     */
    public List<BaseActBusinessBean> findRunningProcessInstaces(String processKey) {
        logger.info("************ 查询 [运行中] 流程，流程定义key：" + processKey + " ************");
        List<BaseActBusinessBean> results = new ArrayList<BaseActBusinessBean>();
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().processDefinitionKey(processKey).active().orderByProcessInstanceId().desc();
        List<ProcessInstance> processInstances = query.list();
        for (ProcessInstance processInstance : processInstances) {
            BaseActBusinessBean baseActBusinessBean = new BaseActBusinessBean();
            baseActBusinessBean.setProcessInstance(processInstance);
            baseActBusinessBean.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
            results.add(baseActBusinessBean);
        }
        return results;
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  10:43
     * Description: 根据流程定义key查询 [已完成] 流程
     * 业务层通过：{ String businessKey = historicProcessInstance.getBusinessKey() }  获取业务主键。
     */
    public List<BaseActBusinessBean> findFinishedProcessInstaces(String processKey) {
        logger.info("************ 查询 [已完成] 流程，流程定义key：" + processKey + " ************");
        List<BaseActBusinessBean> results = new ArrayList<BaseActBusinessBean>();
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processKey).finished().orderByProcessInstanceEndTime().desc();
        List<HistoricProcessInstance> historicProcessInstances = query.list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            BaseActBusinessBean baseActBusinessBean = new BaseActBusinessBean();
            baseActBusinessBean.setHistoricProcessInstance(historicProcessInstance);
            baseActBusinessBean.setProcessDefinition(getProcessDefinition(historicProcessInstance.getProcessDefinitionId()));
            results.add(baseActBusinessBean);
        }
        return results;
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  11:11
     * Description: 签收任务
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public String signforTask(String taskId, String userId) {
        logger.info("************ 签收任务，taskId：" + taskId + " ************");
        taskService.claim(taskId, userId);
        return "success";
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  11:11
     * Description: 完成任务
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public String completeTask(String taskId, Map<String, Object> variables) {
        logger.info("************ 完成任务，taskId：" + taskId + " ************");
        variables.put("deptLeaderPass",true);
        taskService.complete(taskId, variables);
        return "success";

    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  11:20
     * Description: 读取详细数据
     */
    public BaseActBusinessBean getTaskVariables(String taskId) {
        logger.info("************ 读取详细数据，taskId：" + taskId + " ************");
        Map<String, Object> variables = taskService.getVariables(taskId);
        BaseActBusinessBean baseActBusinessBean = new BaseActBusinessBean();
        baseActBusinessBean.setVariables(variables);
        return baseActBusinessBean;
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/24  10:33
     * Description: 查询流程定义对象
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        logger.info("************ 查询流程定义对象，processDefinitionId：" + processDefinitionId + " ************");
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }
}
