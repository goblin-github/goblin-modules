package com.goblin.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.goblin.dao.mapper.UserInfoMapper;
import com.goblin.dao.moudle.UserInfo;
import com.goblin.dao.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
