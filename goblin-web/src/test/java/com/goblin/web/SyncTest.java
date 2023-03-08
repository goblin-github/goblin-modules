package com.goblin.web;

import com.goblin.utils.GsonUtils;
import com.goblin.utils.Log;
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
    public void FutureTest() throws ExecutionException, InterruptedException {
        List<Integer> arrayList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List<Future<List<Integer>>> futureList = arrayList.stream().map(i -> taskExecutor.submit(() -> getList(i))).collect(Collectors.toList());
        for (Future<List<Integer>> future : futureList) {
            List<Integer> subOrgIds;
            subOrgIds = future.get();
            System.out.println(GsonUtils.toJson(subOrgIds));
        }
    }

    @Test
    public void syncException() throws ExecutionException, InterruptedException {
        Future<?> submit = taskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入了task方法！！！");
                int i = 1 / 0;
            }
        });
        submit.get();

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入了task方法！！！");
                int i = 1 / 0;
            }
        });
    }

    private List<Integer> getList(int i) {
        return Lists.newArrayList(i, 2, 3, 4, 5, 6, 7);
    }
}
