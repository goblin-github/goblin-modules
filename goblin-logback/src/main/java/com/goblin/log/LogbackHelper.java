package com.goblin.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import static com.goblin.log.constant.LogConfigurationKey.LOG_SENSITIVE;

/**
 * @author wangpeng
 * @version jdk1.8
 */
public class LogbackHelper extends MessageConverter {
    public static boolean SENSITIVE = Boolean.parseBoolean(System.getProperty(LOG_SENSITIVE));

    @Override
    public String convert(ILoggingEvent event) {
        // 获取原始日志
        String content = event.getFormattedMessage();
        if (content == null || content.isEmpty() || event.getLevel().levelInt <= Level.DEBUG.levelInt) {
            return content;
        }
        if (SENSITIVE) {
            StringBuilder builderContent = new StringBuilder(content);
            SensitiveInfoHelper.filterCommonKv(builderContent);
            SensitiveInfoHelper.filterCommon(builderContent);
            content = builderContent.toString();
        }
        return content;
    }

}
