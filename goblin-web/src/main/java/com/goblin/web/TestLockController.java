package com.goblin.web;

import com.goblin.base.ApiResult;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/12/7
 */
@RestController
@RequestMapping("test-lock")
public class TestLockController {
    @Resource
    private Redisson redisson;

    @GetMapping("test")
    public ApiResult<String> test() {
        // 1.获取锁，只要锁的名字一样，获取到的锁就是同一把锁。
        RLock lock = redisson.getLock("WuKong-lock");
        // 2.加锁
        lock.lock();
        try {
            System.out.println("加锁成功，执行后续代码。线程 ID：" + Thread.currentThread().getId());
            TimeUnit.MINUTES.sleep(1);
        } catch (Exception e) {
            //TODO
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
            // 3.解锁
            System.out.println("Finally，释放锁成功。线程 ID：" + Thread.currentThread().getId());
        }
        return ApiResult.success("test lock ok");
    }
}
