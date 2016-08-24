package com.elixir.activiti.service.impl;

import com.elixir.activiti.service.ActUserGroupService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.util.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jingyan on 2016/8/22.
 * 同步 业务组织架构 到 activiti 用户表的重要接口，主要提供用户组关系的同步，组的查询、新增
 */
@Service
public class ActUserGroupServiceImp implements ActUserGroupService {

    @Autowired
    protected IdentityService identityService;

    /**
     * Created with: jingyan.
     * Date: 2016/8/22  15:31
     * Description: 新增工作流用户
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public String createUser(String userId, String firstName, String lastName, String password,
                             String email, String imageResource, List<String> groupIds, List<String> userInfo) {
//      验证用户名是否唯一
        if (identityService.createUserQuery().userId(userId).count() == 0) {
            User user = identityService.newUser(userId);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setEmail(email);
            identityService.saveUser(user);
            if (groupIds != null) {
                for (String groupId : groupIds) {
                    identityService.createMembership(userId, groupId);
                }
            }
        } else {
            return "not_only";
        }
//      UserPicture 字段  （image）
        if (imageResource != null && !"".equals(imageResource)) {
            byte[] pictureBytes = IoUtil.readInputStream(this.getClass().getClassLoader().getResourceAsStream(imageResource), null);
            Picture picture = new Picture(pictureBytes, "image/jpeg");
            identityService.setUserPicture(userId, picture);
        }
//     用户扩展信息，没有可以不维护到activiti
        if (userInfo != null) {
            for (int i = 0; i < userInfo.size(); i += 2) {
                identityService.setUserInfo(userId, userInfo.get(i), userInfo.get(i + 1));
            }
        }
        return "success";
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/22  15:30
     * Description: 创建用户组
     */
    @Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
    public String createGroup(String groupId, String groupName, String type) {
        //和 User 一样，检验ID的唯一性。
        if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
            Group newGroup = identityService.newGroup(groupId);
            //group的Name字段暂定为ID的首字母大写的形式，也可以直接set键入的汉字。
            //newGroup.setName(groupId.substring(0, 1).toUpperCase() + groupId.substring(1));
            newGroup.setName(groupName);
            newGroup.setType(type);
            identityService.saveGroup(newGroup);
            return "success";
        } else {
            return "not_only";
        }
    }

    /**
     * Created with: jingyan.
     * Date: 2016/8/22  16:04
     * Description: 查询所有用户组
     */
    public List<Group> getAllGroup() {
        List<Group> groups = identityService.createGroupQuery().orderByGroupId().list();
        return groups;
    }
}
