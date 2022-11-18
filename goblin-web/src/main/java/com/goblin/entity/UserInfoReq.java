package com.goblin.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
@Data
public class UserInfoReq {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 姓名
     */
    @NotBlank(message = "名字不能为空")
    private String name;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 手机号
     */
    private String phone;
}
