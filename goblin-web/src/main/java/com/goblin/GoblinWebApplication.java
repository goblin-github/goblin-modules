package com.goblin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/18
 */
@SpringBootApplication
@MapperScan("com.goblin.dao.mapper")
public class GoblinWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoblinWebApplication.class, args);
    }

}
