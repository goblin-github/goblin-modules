package com.goblin.web.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goblin.dao.moudle.UserInfo;
import com.goblin.manage.UserInfoManage;
import com.goblin.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2023/3/2
 */
@SpringBootTest
@Slf4j
public class UserInfoManageTests {
    @Resource
    private UserInfoManage userInfoManage;

    @Test
    public void list() {
        IPage<UserInfo> list = userInfoManage.list(1, 5);
        System.out.println(GsonUtils.toJson(list));
    }
}
