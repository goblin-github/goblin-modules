package com.goblin.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.Date;
import java.util.HashMap;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/29
 */
@Slf4j
public class Log extends HashMap<String, Object> {

    /**
     * traceId
     */
    public static final String TRACE_ID = "traceId";

    /**
     * spanId
     */
    public static final String SPAN_ID = "spanId";
    /**
     * 日志主题
     */
    public static final String LOG_TOPIC = "logTopic";
    /**
     * 当前线程
     */
    public static final String THREAD = "thread";
    /**
     * 日志时间
     */
    public static final String LOG_TIME = "logTime";
    /**
     * 日志时间格式化
     */
    private static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";


    protected static Log newLog() {
        Log log = new Log();
        String threadName = Thread.currentThread().getName();
        log.log(TRACE_ID, MDC.get(TRACE_ID));
        log.log(SPAN_ID, MDC.get(SPAN_ID));
        log.log(THREAD, threadName);
        log.log(LOG_TIME, DateFormatUtils.format(new Date(), DATA_FORMAT));
        return log;
    }

    public static Log topic(String topic) {
        Log log = newLog();
        log.put(LOG_TOPIC, topic);
        return log;
    }

    public Log log(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public void info() {
        log.info(GsonUtils.toJson(this));
    }

    public void info(Logger logger) {
        logger.info(GsonUtils.toJson(this));
    }

    public void error() {
        log.error(GsonUtils.toJson(this));
    }

    public void error(Logger logger) {
        logger.error(GsonUtils.toJson(this));
    }
}