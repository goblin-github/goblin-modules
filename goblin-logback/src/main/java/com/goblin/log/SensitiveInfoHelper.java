package com.goblin.log;

import com.goblin.log.config.PatternConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangpeng
 * @version jdk1.8
 */
public class SensitiveInfoHelper {
    /**
     * 敏感数据替换字符
     */
    private static final char SENSITIVE_STR = '*';

    /**
     * 通用数据脱敏处理
     *
     * @param content 日志原始内容
     */
    public static void filterCommon(StringBuilder content) {
        Pattern[] commonPatterns = PatternConfig.COMMON_PATTERNS;
        for (Pattern commonPattern : commonPatterns) {
            Matcher matcher = commonPattern.matcher(content);
            while (matcher.find()) {
                content.replace(matcher.start(), matcher.end(), facade(matcher.group()));
            }
        }
    }


    /**
     * 通用key-value日志脱敏处理
     *
     * @param content 日志原始内容
     */
    public static void filterCommonKv(StringBuilder content) {
        Pattern commonKvConfig = PatternConfig.COMMON_KV_PATTERN;
        Matcher matcher = commonKvConfig.matcher(content);
        while (matcher.find()) {
            String group = matcher.group();
            int i = group.indexOf(":");
            if (i == -1) {
                i = group.indexOf("=");
            }
            if (i == -1) {
                return;
            }
            content.replace(matcher.start(), matcher.start() + i, facade(group.substring(0, i)));
            content.replace(matcher.start() + i + 1, matcher.end(), facade(group.substring(i + 1)));
            //进行再次匹配，保证日志中符合要求的kv全部被替换
            matcher = commonKvConfig.matcher(content);
        }
    }

    /**
     * 混淆，但是不能改变字符串的长度
     *
     * @param source 待脱敏的字符串
     * @return 脱敏后字符串
     */
    public static String facade(String source) {
        int length = source.length();
        StringBuilder sb = new StringBuilder();
        //长度大于等于11的，保留前三、后四，中间全部*替换
        if (length >= 11) {
            sb.append(source, 0, 3).append(repeat(length - 7)).append(source.substring(length - 4));
            return sb.toString();
        }
        //长度小于等于3的，全部*替换
        if (length <= 3) {
            sb.append(repeat(length));
            return sb.toString();
        }
        //长度大于4小于11的，保留前一、后一，中间全部*替换
        sb.append(source, 0, 1).append(repeat(length - 2)).append(source.substring(length - 1));
        return sb.toString();
    }

    /**
     * 生成指定长度脱敏字符串
     *
     * @param length 指定长度
     * @return 脱敏字符串
     */
    private static String repeat(int length) {
        char[] r = new char[length];
        for (int i = 0; i < length; i++) {
            r[i] = SENSITIVE_STR;
        }
        return new String(r);
    }

}
