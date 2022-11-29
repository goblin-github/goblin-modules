package com.goblin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/22
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 线程池的核心线程数，线程池维护线程的最少数量，即使没有任务需要执行，也会一直存活
        taskExecutor.setCorePoolSize(2);
        // 最大线程池数量，当线程数>=corePoolSize，且任务队列已满时，线程池会创建新的线程来处理任务
        taskExecutor.setMaxPoolSize(20);
        // 队列容量,阻塞队列 当核心线程数达到最大时，新的任务会放在队列中排队等待执行
        taskExecutor.setQueueCapacity(500);
        // 当线程空闲时间达到KeepAliveSeconds时，线程会推出，知道线程数量=CorePoolSize
        // 允许线程空闲时间为60s，当MaxPoolSize的线程在空闲时间到达的时候销毁
        // 如果AllowCoreThreadTimeOut=true，则会直到线程数量=0
        taskExecutor.setKeepAliveSeconds(60);
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix("common-thread-pool-");
        // 任务执行之后再shutdown
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池中任务销毁的等待时间, 如果超过这个时间还没有销毁就强制销毁, 以确保应用最后能够被关闭, 而不是阻塞住
        taskExecutor.setAwaitTerminationSeconds(20);
        // 如果设置setAllowCoreThreadTimeOut=true（默认false）时，核心线程会超时关闭
        taskExecutor.setAllowCoreThreadTimeOut(true);
        // 线程池拒绝策略 当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy()：交由调用方线程运行，比如 main 线程；如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行
        // AbortPolicy()：该策略是线程池的默认策略，如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常。
        // DiscardPolicy()：如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常
        // DiscardOldestPolicy()：丢弃队列中最老的任务，队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
