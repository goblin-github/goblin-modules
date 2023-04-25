package com.goblin.web.utlis;

import com.goblin.utils.GsonUtils;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2023/4/25
 */
public class GsonUtilsTest {
    @Test
    public void list() {
        List<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        String json = GsonUtils.toJson(integers);
        System.out.println(json);

        List<Integer> integers1 = GsonUtils.fromJsonList(json, new TypeToken<List<Integer>>() {

        });
        integers1.forEach(System.out::println);
    }
}
