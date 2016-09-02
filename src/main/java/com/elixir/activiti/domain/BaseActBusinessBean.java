package com.elixir.activiti.domain;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 * 基本的 【业务 → Activiti】 的实体类 .
 * 业务需要关联 Activiti 的实体类，最好都继承该类
 */
public class BaseActBusinessBean {

    // 业务主键
    protected String businessKey;
    // 流程任务
    private Task task;
    // 运行中的流程实例
    private ProcessInstance processInstance;
    // 历史的流程实例
    private HistoricProcessInstance historicProcessInstance;
    // 流程定义实例
    private ProcessDefinition processDefinition;
    // 流程参数
    private Map<String, Object> variables;


    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public ProcessInstance getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstance processInstance) {
        this.processInstance = processInstance;
    }

    public HistoricProcessInstance getHistoricProcessInstance() {
        return historicProcessInstance;
    }

    public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
        this.historicProcessInstance = historicProcessInstance;
    }

    public ProcessDefinition getProcessDefinition() {
        return processDefinition;
    }

    public void setProcessDefinition(ProcessDefinition processDefinition) {
        this.processDefinition = processDefinition;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
