package com.goblin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Gson序列化工具类
 *
 * @author wang
 * @version v1.1
 * @since 2023/4/25
 */
public class GsonUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setDateFormat(DATE_FORMAT)
                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                .create();
    }

    /**
     * 将对象转成json字符串
     *
     * @param obj 要转换的对象
     * @return json 字符串
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将对象转为json字符串，支持泛型对象
     */
    public static <T> String toJson(T object, Type type) {
        return gson.toJson(object, type);
    }

    /**
     * 将json字符串转成指定类型的对象
     *
     * @param json json 字符串
     * @param type 对象类型
     * @param <T>  对象类型
     * @return 对象
     */
    public static <T> T fromJson(String json, Class<T> type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

    /**
     * 将json字符串转为泛型对象
     */
    public static <T> T fromJson(String json, Type type) throws JsonSyntaxException {
        return gson.fromJson(json, type);
    }

    /**
     * 将json字符串转为对象列表
     */
    public static <T> List<T> fromJsonList(String json, TypeToken<List<T>> typeToken) throws JsonSyntaxException {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 将json字符串转为Map对象
     */
    public static <K, V> Map<K, V> fromJsonMap(String json, TypeToken<Map<K, V>> typeToken) throws JsonSyntaxException {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * 将对象转为Map对象
     */
    public static <K, V> Map<K, V> toMap(Object object) {
        Type type = new TypeToken<Map<K, V>>() {
        }.getType();
        return gson.fromJson(toJson(object), type);
    }
}
