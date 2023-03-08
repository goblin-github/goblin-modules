package com.goblin.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2023/1/12
 */
public class ThreadContext {
    private static ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(HashMap::new);

    public static void put(String key, String value) {
        threadLocal.get().put(key, value);
    }

    public static String get(String key) {
        return threadLocal.get().get(key).toString();
    }

    public static void putObj(String key, Object value) {
        threadLocal.get().put(key, value);
    }

    public static Object getObj(String key) {
        return threadLocal.get().get(key);
    }

    /**
     * 清理当前线程所有变量
     */
    public static void remove() {
        threadLocal.remove();
    }
}
