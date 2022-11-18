package com.goblin.manage;

import com.goblin.dao.moudle.UserInfo;
import com.goblin.dao.service.IUserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
@Component
public class UserInfoManage {
    @Resource
    private IUserInfoService userInfoService;

    public List<UserInfo> list() {
        return userInfoService.list();
    }
}
