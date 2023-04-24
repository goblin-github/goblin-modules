package com.goblin.web;

import com.goblin.utils.GsonUtils;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.Arrays;
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


    @Test
    public void test() {
        String s1 = "/baas-admin/postmanage/getOneOrgPost,/admin-portal/content/getPortal,/admin-portal/content/getPortalPageList,/admin-portal/content/savePageBrief,/admin-portal/template/getPortal,/admin-portal/content/updateStatusById,/baas-app-1/appstore/app/list,/admin-portal/datasource/list,/baas-app-1/group/list,/baas-news/category/queryNewsCategory,/admin-portal/content/appPortalLittleList,/admin-portal/template/savePortal,/admin-portal/content/getContentById,/material-center/admin/folder/findTree,/material-center/admin/image/findPage,/baas-account/admin/myself,/sfs/webUpload/file,/admin-portal/content/copy,/admin-portal/content/del,/admin-portal/template/webPortalLittleList,/admin-portal/template/appPortalLittleList,/admin-portal/content/pcPortalLittleList,/smallapp/cardManager/list,/baas-app-1/appstore/myApps,/admin-portal/template/updateStatusById,/admin-portal/template/getTemplateList";
        String s2 = "/baas-app-1/subscribe/list,/baas-admin/postmanage/getOneOrgPost,/admin-portal/menu/order,/admin-portal/content/getSiteLittleList,/admin-portal/content/weight/update,/admin-portal/content/saveSite,/admin-portal/content/getSite,/admin-portal/group/getMenuRecordByGroupId,/admin-portal/content/getPortalPageList,/admin-portal/group/appMenuList,/admin-portal/group/appMenuRelateSave,/admin-portal/group/appMenuRelateRecordList,/material-center/admin/folder/findTree,/material-center/admin/image/findPage,/admin-portal/content/updateStatusById,/admin-portal/menu/appSave,/admin-portal/content/appPortalLittleList,/admin-portal/content/copySite,/admin-portal/content/dispatch/list,/baas-admin/common/org/myList,/admin-portal/content/dispatch,/admin-portal/content/del,/admin-portal/menu/setIndex,/baas-app-1/appstore/app/list,/admin-portal/datasource/list,/baas-app-1/group/list,/admin-portal/menu/del,/admin-portal/template/savePortal,/admin-portal/content/getContentById,/baas-account/admin/myself,/baas-news/category/queryNewsCategory,/sfs/webUpload/file,/admin-portal/menu/authorities/list,/admin-portal/menu/authorities/save,/admin-portal/menu/get,/admin-portal/menu/saveMenuItem,/admin-portal/content/authorities/list,/admin-portal/content/webPortalLittleList,/admin-portal/menu/webSave,/admin-portal/group/saveMenuGroup,/admin-portal/group/get,/admin-portal/content/authorities/save,/admin-portal/group/webMenuRecordList,/admin-portal/content/pcPortalLittleList,/admin-portal/group/getMenuGroupList,/baas-todocenter/admin/todo-task/viewConfig,/admin-portal/content/middlePortalLittleList,/baas-app-1/appstore/myApps";
        String s3 = "/baas-admin/postmanage/getOneOrgPost,/admin-portal/template/getTemplateList,/admin-portal/template/saveAppPortalRecord,/admin-portal/content/updateStatusById,/admin-portal/template/copy,/admin-portal/template/del,/admin-portal/content/getPortal,/baas-app-1/appstore/app/list,/admin-portal/content/appPortalLittleList,/admin-portal/datasource/list,/baas-admin/common/org/myList,/baas-admin/common/listAll,/baas-app-1/group/list,/material-center/admin/folder/findTree,/material-center/admin/image/findPage,/baas-news/category/queryNewsCategory,/admin-portal/template/savePortal,/admin-portal/content/getContentById,/baas-account/admin/myself,/sfs/webUpload/file,/admin-portal/template/updateStatusById,/admin-portal/template/saveWebPortalRecord,/admin-portal/template/savePcPortalRecord,/baas-app-1/appstore/myApps,/baas-todocenter/admin/todo-task/viewConfig";
        String s4 = "/material-center/admin/folder/findTree,/material-center/admin/image/findPage,/material-center/admin/image/update,/sfs/file,/material-center/admin/image/deleteBatch,/sfs/webUpload/file,/material-center/admin/image/insertBatch,/material-center/admin/folder/insert,/material-center/admin/folder/delete,/material-center/admin/folder/update,/material-center/admin/video/insertBatch,/material-center/admin/video/update,/material-center/admin/video/findPage,/material-center/admin/video/deleteBatch,/material-center/admin/document/insertBatch,/material-center/admin/document/deleteBatch,/material-center/admin/document/update,/material-center/admin/document/findPage";
        String s5 = "/admin-portal/datasource/list,/admin-portal/datasource/del,/baas-news/category/queryNewsCategory,/baas-news/notice/selectCategory,/admin-portal/datasource/save";
        List<String> list1 = Arrays.stream(s1.split(",")).collect(Collectors.toList());
        List<String> list2 = Arrays.stream(s2.split(",")).collect(Collectors.toList());
        List<String> list3 = Arrays.stream(s3.split(",")).collect(Collectors.toList());
        List<String> list4 = Arrays.stream(s4.split(",")).collect(Collectors.toList());
        List<String> list5 = Arrays.stream(s5.split(",")).collect(Collectors.toList());

        List<String> list = Lists.newArrayList();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.addAll(list5);

        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(GsonUtils.toJson(collect));
    }
}
