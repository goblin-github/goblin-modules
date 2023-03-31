package com.goblin.web.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.goblin.dao.moudle.UserInfo;
import com.goblin.manage.UserInfoManage;
import com.goblin.utils.GsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author wangpeng
 * @version v1.0
 * @since 2023/3/2
 */
@SpringBootTest
@Slf4j
public class UserInfoManageTests {
    @Resource
    private UserInfoManage userInfoManage;

    @Test
    public void list() {
        IPage<UserInfo> list = userInfoManage.list(1, 5);
        System.out.println(GsonUtils.toJson(list));
    }

    @Test
    public void test() {
        Long nowOrgId = 10090L;
        List<Map<String, String>> origin = Lists.newArrayList();
        buildData(origin);
        List<Map<String, String>> origin1 = Lists.newArrayList();
        origin1.addAll(origin);
        long l = System.currentTimeMillis();

        Map<String, List<Map<String, String>>> originGroupByUid = origin.stream().sorted(Comparator.comparingLong(x -> Long.parseLong(x.get("orgId")))).collect(Collectors.groupingBy(x -> x.get("uid")));
        List<Map<String, String>> list = Lists.newArrayList();
        for (Map.Entry<String, List<Map<String, String>>> entry : originGroupByUid.entrySet()) {
            List<Map<String, String>> value = entry.getValue();
            Map<String, String> useInfo = value.stream().filter(e -> Objects.equals(Long.parseLong(e.get("orgId")), nowOrgId)).findAny().orElse(value.get(0));
            list.add(useInfo);
        }
        origin.retainAll(list);
        long b = System.currentTimeMillis();

        List<Map<String, String>> list1 = Lists.newArrayList();
        Map<String, List<Map<String, String>>> map = origin1.stream().collect(Collectors.groupingBy(x -> x.get("uid"), LinkedHashMap::new, Collectors.toList()));

        for (Map.Entry<String, List<Map<String, String>>> entry : map.entrySet()) {
            List<Map<String, String>> value = entry.getValue();
            if (value.size() == 1) {
                list1.add(entry.getValue().get(0));
                continue;
            }
            value.sort((t1, t2) -> {
                if (Integer.parseInt(t1.get("orgId")) == Integer.parseInt(t2.get("orgId")) && t1.get("orgId").equals(String.valueOf(nowOrgId))) {
                    return 0;
                }
                // 当前单位排第一个
                if (t1.get("orgId").equals(String.valueOf(nowOrgId)))
                    return -1;
                if (t2.get("orgId").equals(String.valueOf(nowOrgId))) {
                    return 1;
                }
                return Integer.parseInt(t1.get("orgId")) - Integer.parseInt(t2.get("orgId"));
            });
            list1.add(entry.getValue().get(0));
        }
        origin1.retainAll(list1);

        long c = System.currentTimeMillis();

        System.out.println(b-l);
        System.out.println(c-b);

    }

    private void buildData(List<Map<String, String>> data) {
        List<String> uids = Lists.newArrayList("22222", "33333", "44444", "55555", "66666", "77777", "88888", "999999", "11111");
        List<String> orgIds = Lists.newArrayList("10086", "10087", "10088", "10089", "10090", "10091", "10092", "10093", "10094");
        for (int i = 0; i < 500000; i++) {
            Map<String, String> map = Maps.newHashMap();
            String uid = uids.get((int) (Math.random() * uids.size()));
            map.put("uid", uid);
            map.put("orgId", orgIds.get((int) (Math.random() * orgIds.size())));
            map.put("name", uid);
            map.put("_score", i + "");
            data.add(map);
        }
    }

}
