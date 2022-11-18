package com.goblin.log.config;

import java.util.regex.Pattern;

/**
 * @author wangpeng
 * @version jdk1.8
 */
public class PatternConfig {
    /**
     * 通用正则匹配
     */
    public static Pattern[] COMMON_PATTERNS = new Pattern[]{
            //手机号正则
            Pattern.compile("(?<!\\d)((?:(?:\\+|00)86)?1\\d{10})(?!\\d)"),
            //身份证正则
            Pattern.compile("(?<!\\d)((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))(?!\\d)"),
            //邮箱正则
            Pattern.compile("\\w[-\\w.+]@([A-Za-z0-9][-A-Za-z0-9]+.)+[A-Za-z]{2,14}"),
            //URL正则
            Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]"),
            //GPS正则
            Pattern.compile("(?<!\\d)[0-9]+(.[0-9]+),[0-9]+(.[0-9]+)")
    };
    /**
     * 通用key-value正则匹配
     */
    public static Pattern COMMON_KV_PATTERN = Pattern.compile("(?i)(password|passwd|pwd|secret|token|Authorization|session|clientSecret)\"{0,1}\\s*[:|=]\\s*\"{0,1}[a-zA-Z0-9\\@\\_\\-]+\"{0,1}", Pattern.CASE_INSENSITIVE);
}
