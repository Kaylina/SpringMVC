package com.elixir.activiti.util;

import com.elixir.activiti.service.ActUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG

public class DemoDataInitConfig {
    @Autowired
    protected ActUserGroupService actUserGroupService;



    @PostConstruct
    public void init() {
        String[] assignmentGroups = new String[]{"user", "deptLeader","manage","admin"};
        for (String groupId : assignmentGroups) {
            actUserGroupService.createGroup(groupId,"G_"+groupId,"security_role");
        }

        actUserGroupService.createUser("admin", "Henry", "Yan", "000000", "henry.yan@le.com",
                "", Arrays.asList("user", "admin"), null);

        actUserGroupService.createUser("hruser", "Lili", "Zhang", "000000", "lili.zhang@le.com",
                "", Arrays.asList("manage", "user"), null);

        actUserGroupService.createUser("leaderuser", "Jhon", "Li", "000000", "jhon.li@le.com",
                "", Arrays.asList("deptLeader", "user"), null);

        actUserGroupService.createUser("kafeitu", "Coffee", "Rabbit", "000000", "coffee.rabbit@le.com",
                "", Arrays.asList("user", "admin"), null);
    }

}
