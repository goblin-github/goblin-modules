package com.goblin.web;

import com.goblin.utils.GsonUtils;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/23
 */
@SpringBootTest
public class SyncTest {

    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    public void syncTest() throws ExecutionException, InterruptedException {
        List<Integer> arrayList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List<Future<List<Integer>>> futureList = arrayList.stream().map(i -> taskExecutor.submit(() -> getList(i))).collect(Collectors.toList());
        for (Future<List<Integer>> future : futureList) {
            List<Integer> subOrgIds;
            subOrgIds = future.get();
            System.out.println(GsonUtils.toJson(subOrgIds));
        }
    }

    @Test
    public void syncName() {
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
    }

    public List<Integer> getList(int i) {
        return Lists.newArrayList(i, 2, 3, 4, 5, 6, 7);
    }
}
