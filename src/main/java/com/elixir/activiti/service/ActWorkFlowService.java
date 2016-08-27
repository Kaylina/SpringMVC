package com.elixir.activiti.service;

import com.elixir.activiti.domain.BaseActBusinessBean;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.List;
import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 */
public interface ActWorkFlowService {

    public String startActWorkFlow(String processKey, String businessKey,String startUserId, Map<String, Object> variables);

    public List<BaseActBusinessBean> findTodoTasks(String userId);

    public List<BaseActBusinessBean> findRunningProcessInstaces(String processKey);

    public List<BaseActBusinessBean> findFinishedProcessInstaces(String processKey);

    public String signforTask(String taskId,String userId);

    public String completeTask(String taskId,Map<String, Object> variables);

    public BaseActBusinessBean getTaskVariables(String taskId);

}
