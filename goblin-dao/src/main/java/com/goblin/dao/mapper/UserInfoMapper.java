package com.goblin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.goblin.dao.moudle.UserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("select * from user_info INNER join test on user_info.user_id = test.userId")
    IPage<UserInfo> get(Page page);
}