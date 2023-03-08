package com.goblin.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public IPage<UserInfo> list(int pageNo, int pageSize) {
        Page<UserInfo> page = new Page<>(pageNo, pageSize);
        return userInfoService.get(page);

    }
}
