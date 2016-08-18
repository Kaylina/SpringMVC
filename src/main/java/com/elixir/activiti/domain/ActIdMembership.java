package com.elixir.activiti.domain;

/**
 * Created by jingyan on 2016/8/18.
 * activiti 用户 - 组 关系映射表
 */
public class ActIdMembership {
    private String userId;
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
