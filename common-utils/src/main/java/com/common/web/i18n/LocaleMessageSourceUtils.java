package com.common.web.i18n;

import com.common.utils.spring.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LocaleMessageSourceUtils {
    private static final String MESSAGESOURCE = "messageSource";

    /**
     * 获取国际化信息
     *
     * @param code
     * @return
     */
    static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * getMessage
     *
     * @param code
     * @param args
     * @return
     */
    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, code);
    }

    /**
     * 获取国际化参数
     *
     * @param code
     * @param args
     * @param defaultMessage
     * @return
     */

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        MessageSource messageSource = SpringUtils.getBean(MESSAGESOURCE, MessageSource.class);
        String message = messageSource.getMessage(code, args, defaultMessage, locale);
        return StringUtils.isBlank(message) ? defaultMessage : message;
    }
}
