package com.goblin.utils;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * js脚本安全检查工具类
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public class JavaScriptSafeCheckUtils {
    /**
     * 安全关键字黑名单
     */
    private static final Set<String> BLACK_LIST = Sets.newHashSet(
            // Java 全限定类名
            "java.io.File", "java.io.RandomAccessFile", "java.io.FileInputStream", "java.io.FileOutputStream",
            "java.lang.Class", "java.lang.ClassLoader", "java.lang.Runtime", "java.lang.System", "System.getProperty",
            "java.lang.Thread", "java.lang.ThreadGroup", "java.lang.reflect.AccessibleObject", "java.net.InetAddress",
            "java.net.DatagramSocket", "java.net.DatagramSocket", "java.net.Socket", "java.net.ServerSocket",
            "java.net.MulticastSocket", "java.net.MulticastSocket", "java.net.URL", "java.net.HttpURLConnection",
            "java.security.AccessControlContext", "java.lang.ProcessBuilder",
            //反射关键字
            "invoke", "newinstance",
            // JavaScript 方法
            "eval", "new function",
            //引擎特性
            "Java.type", "importPackage", "importClass", "JavaImporter"
    );

    /**
     * 检查js脚本是否安全
     *
     * @param code js脚本
     * @throws Exception
     */
    public static void checkInsecureKeyword(String code) throws Exception {

        // 去除注释
        String removeComment = RegExUtils.replacePattern(code, "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*[\n\r\u2029\u2028])", " ");
        //去除特殊字符
        removeComment = RegExUtils.replacePattern(removeComment, "[\u2028\u2029\u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\ufeff]", "");
        // 去除空格
        String removeWhitespace = RegExUtils.replacePattern(removeComment, "\\s+", "");
        // 多个空格替换为一个
        String oneWhiteSpace = RegExUtils.replacePattern(removeComment, "\\s+", " ");
        //System.out.println(removeWhitespace);
        //System.out.println(oneWhiteSpace);
        Set<String> insecure = BLACK_LIST.stream().filter(s -> StringUtils.containsIgnoreCase(removeWhitespace, s) ||
                StringUtils.containsIgnoreCase(oneWhiteSpace, s)).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(insecure)) {
            //System.out.println("存在不安全的关键字:" + insecure);
            throw new Exception("存在安全问题");
        } else {
            ScriptEngineManager manager = new ScriptEngineManager(null);
            ScriptEngine engine = manager.getEngineByName("js");
            engine.eval(code);
        }
    }
}
