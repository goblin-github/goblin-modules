package com.goblin.web;

import com.goblin.utils.GsonUtils;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 一些杂七杂八的测试案例
 *
 * @author wangpeng
 * @version v1.0
 * @since 2023/4/28
 */
public class SomeThingsTests {
    @Test
    public void streamTest() {
        //构建测试list数据
        List<String> list = buildList();
        StopWatch stopWatch = new StopWatch();
        //测试串行stream运算
        stopWatch.start("stream");
        list.stream().forEach(e -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        stopWatch.stop();
        System.out.println(GsonUtils.toJson(stopWatch.getLastTaskTimeMillis()));
        //测试并行stream运算
        stopWatch.start("parallelStream");
        list.parallelStream().forEach(e -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        stopWatch.stop();
        System.out.println(GsonUtils.toJson(stopWatch.getLastTaskTimeMillis()));
    }

    private List<String> buildList() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
