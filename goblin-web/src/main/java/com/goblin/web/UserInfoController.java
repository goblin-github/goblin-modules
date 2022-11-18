package com.goblin.web;

import com.goblin.base.ApiResult;
import com.goblin.entity.UserInfoReq;
import com.goblin.manage.UserInfoManage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Size;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18`
 */
@RestController
@RequestMapping("user/info")
//该注解仅针对RequestParam和PathVariable
@Validated
public class UserInfoController {

    @Resource
    private UserInfoManage userInfoManage;

    @PostMapping
    public ApiResult<String> save(@RequestBody @Validated UserInfoReq userInfoReq) {
        return ApiResult.success("ok");
    }

    @GetMapping
    public ApiResult<String> info() {
        return ApiResult.success("ok");
    }

    @GetMapping("{name}")
    public ApiResult<String> infoByName(@PathVariable("name") @Size(max = 10) String userName) {
        return ApiResult.success("ok");
    }
}
