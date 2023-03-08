package com.goblin.dao.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.goblin.dao.moudle.UserInfo;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
public interface IUserInfoService extends IService<UserInfo> {

    IPage<UserInfo> get(Page page);
}
