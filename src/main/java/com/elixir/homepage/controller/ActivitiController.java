package com.elixir.homepage.controller;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jingyan on 2016/8/16.
 */
@Controller
@RequestMapping("")
public class ActivitiController {
    @Autowired
    private ProcessEngine processEngine;
    private String ids="";
    private String ids2="";


    @RequestMapping("/testact")
    @ResponseBody
    public void processTests() {
        // 部署流程文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //Deployment deployment = repositoryService.createDeployment().addClasspathResource("/activitifile/firstrtast.bpmn").deploy();
        Deployment deployment = processEngine.getRepositoryService() // 与流程定义和部署对象相关的service
                .createDeployment()// 创建一个部署对象
                .name("流程定义")// 添加部署的名称
                .addClasspathResource("/activitifile/firstrtast.bpmn")// 从classpath的资源中加载，一次只能加载一个文件
                .deploy();// 完成部署
        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称:" + deployment.getName());
    }

    @RequestMapping("/testact2")
    @ResponseBody
    public void findProcessDefinition() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()// 与流程定义和部署对象先相关的service
                .createProcessDefinitionQuery()// 创建一个流程定义的查询
                        /** 指定查询条件，where条件 */
                        // .deploymentId(deploymentId) //使用部署对象ID查询
                        // .processDefinitionId(processDefinitionId)//使用流程定义ID查询
                        // .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询

            /* 排序 */
                .orderByProcessDefinitionVersion().asc()
                        // .orderByProcessDefinitionVersion().desc()

            /* 返回的结果集 */
                .list();// 返回一个集合列表，封装流程定义
        // .singleResult();//返回惟一结果集
        // .count();//返回结果集数量
        // .listPage(firstResult, maxResults);//分页查询

        if (list != null && list.size() > 0) {
            for (ProcessDefinition pd : list) {
                System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
                System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
                System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
                System.out.println("资源名称bpmn文件:" + pd.getResourceName());
                System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
                System.out.println("部署对象ID：" + pd.getDeploymentId());
                System.out.println("#########################################################");
            }
        }
    }

    /**
     * 启动流程实例
     */
    @RequestMapping("/testact3")
    @ResponseBody
    public void startProcessInstance() {
        // 流程定义的key
        String processDefinitionKey = "HelloWorld";
        ProcessInstance pi = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例，key对应HelloWorld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        ids=pi.getId();
        System.out.println("流程实例ID:" + ids);
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
    }

    /**
     * 查询历史流程实例
     */
    @RequestMapping("/testact4")
    @ResponseBody
    public void findHistoryProcessInstance() {
        String processInstanceId = ids;
        HistoricProcessInstance hpi = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        System.out.println(hpi.getId() + "    " + hpi.getProcessDefinitionId() + "   " + hpi.getStartTime() + "   " + hpi.getDurationInMillis());
    }


    /**
     * 查询当前的个人任务
     */
    @RequestMapping("/testact5")
    @ResponseBody
    public void findMyPersonTask() {
        String assignee = "zs"; // TODO
        List<Task> list = processEngine.getTaskService()// 与正在执行的任务管理相关的service
                .createTaskQuery()// 创建任务查询对象
                        // 查询条件
                .taskAssignee(assignee)// 指定个人任务查询，指定办理人
                        // .taskCandidateGroup("")//组任务的办理人查询
                        // .processDefinitionId("")//使用流程定义ID查询
                        // .processInstanceId("")//使用流程实例ID查询
                        // .executionId(executionId)//使用执行对象ID查询
                        /** 排序 */
                .orderByTaskCreateTime().asc()// 使用创建时间的升序排列
                        // 返回结果集
                        // .singleResult() //返回唯一的结果集
                        // .count()//返回结果集的数量
                        // .listPage(firstResult, maxResults)//分页查询
                .list();// 返回列表
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                ids2=task.getId();
                System.out.println("任务ID：" + task.getId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务的创建时间:" + task.getCreateTime());
                System.out.println("任务的办理人:" + task.getAssignee());
                System.out.println("流程实例ID:" + task.getProcessInstanceId());
                System.out.println("执行对象ID:" + task.getExecutionId());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out
                        .println("##################################################");
            }


        }
    }

    /**
     * 完成我的任务
     */
    @RequestMapping("/testact6")
    @ResponseBody
    public void compliteMyPersonTask() {
        // 任务ID
        String taskId = ids2;
        processEngine.getTaskService().complete(taskId);
        ;
        System.out.println("完成任务：任务ID:" + taskId);
    }

}
