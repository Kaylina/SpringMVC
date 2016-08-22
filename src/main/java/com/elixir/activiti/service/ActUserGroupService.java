package com.elixir.activiti.service;

import com.elixir.activiti.domain.ActIdGroup;
import com.elixir.activiti.domain.ActIdMembership;
import com.elixir.activiti.domain.ActIdUser;
import org.activiti.engine.identity.Group;

import java.util.List;

/**
 * Created by jingyan on 2016/8/22.
 */
public interface ActUserGroupService {
    public String createUser(String userId, String firstName, String lastName, String password,
                           String email, String imageResource, List<String> groups, List<String> userInfo);

    public String createGroup(String groupId,String groupName, String type);


    public List<Group> getAllGroup();

}
