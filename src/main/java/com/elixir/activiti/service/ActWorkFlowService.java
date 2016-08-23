package com.elixir.activiti.service;

import java.util.Map;

/**
 * Created by jingyan on 2016/8/23.
 */
public interface ActWorkFlowService {

    public String startActWorkFlow(String processKey, String businessKey,String startUserId, Map<String, Object> parameter);
}
