package com.goblin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * gson序列化工具类
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public class GsonUtils {

    private static final Gson GSON = new GsonBuilder().create();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String jsonStr, Class<T> objClass) {
        return GSON.fromJson(jsonStr, objClass);
    }



}
