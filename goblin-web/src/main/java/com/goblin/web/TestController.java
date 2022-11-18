package com.goblin.web;

import com.goblin.annotations.LogMonitor;
import com.goblin.base.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
@RestController
@LogMonitor
public class TestController {

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }
}
