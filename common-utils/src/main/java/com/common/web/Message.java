package com.common.web;

import com.common.web.i18n.LocaleMessageSourceUtils;
import com.common.web.serializer.EnumNullSerializer;
import com.common.web.serializer.StringNullSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Message<D extends Object> implements Serializable {

    /**
     * 消息类型
     */
    @JsonSerialize(nullsUsing = EnumNullSerializer.class)
    private MessageType type;
    /**
     * 内容提示
     */
    @JsonSerialize(nullsUsing = StringNullSerializer.class)
    private String content;
    /**
     * 数据
     */
    @JsonSerialize(nullsUsing = StringNullSerializer.class)
    private D data;
    /**
     * 错误码
     */
    @JsonSerialize(nullsUsing = StringNullSerializer.class)
    private String code = "1";

    public Message(MessageType type, String content, Object args[]) {
        this.type = type;
        this.content = LocaleMessageSourceUtils.getMessage(content, args);
        setDefaultCode(type);
    }

    public Message(MessageType type, String content, Object args[], D data) {
        this.data = data;
        this.type = type;
        this.content = LocaleMessageSourceUtils.getMessage(content, args);
        setDefaultCode(type);
    }

    public Message(MessageType type, String content, Object args[], D data, String code) {
        this.data = data;
        this.code = code;
        this.type = type;
        this.content = LocaleMessageSourceUtils.getMessage(content, args);
    }

    private void setDefaultCode(MessageType type) {
        int i = type.ordinal() + 1;
        this.code = i + "";
    }

    /**
     * 成功的提示
     *
     * @param content
     * @return
     */
    public static Message success(String content) {
        return new Message(MessageType.SUCCESS, content, null);
    }

    /**
     * 警告提示
     *
     * @param content
     * @return
     */
    public static Message warn(String content) {
        return new Message(MessageType.WARN, content, null);
    }

    /**
     * 错误提示
     *
     * @param content
     * @return
     */
    public static Message error(String content) {
        return new Message(MessageType.ERROR, content, null);
    }

    /**
     * 成功提示
     *
     * @param content
     * @param args
     * @return
     */
    public static Message success(String content, Object args[]) {
        return new Message(MessageType.SUCCESS, content, args);
    }

    /**
     * 警告提示
     *
     * @param content
     * @param args
     * @return
     */
    public static Message warn(String content, Object args[]) {
        return new Message(MessageType.WARN, content, args);
    }

    /**
     * 错误提示
     *
     * @param content
     * @param args
     * @return
     */
    public static Message error(String content, Object args[]) {
        return new Message(MessageType.ERROR, content, args);
    }

    /**
     * 成功提示
     *
     * @param data
     * @return
     */
    public static Message success(Object data) {
        return new Message(MessageType.SUCCESS, null, null, data);
    }

    /**
     * 警告提示
     *
     * @param data
     * @return
     */
    public static Message warn(Object data) {
        return new Message(MessageType.WARN, null, null, data);
    }

    /**
     * 错误提示
     *
     * @param data
     * @return
     */
    public static Message error(Object data) {
        return new Message(MessageType.ERROR, null, null, data);
    }

    /**
     * 成功提示
     *
     * @param content
     * @param args
     * @param data
     * @return
     */
    public static Message success(String content, Object args[], Object data) {
        return new Message(MessageType.SUCCESS, content, args, data);
    }

    /**
     * 警告提示
     *
     * @param content
     * @param args
     * @param data
     * @return
     */
    public static Message warn(String content, Object args[], Object data) {
        return new Message(MessageType.WARN, content, args, data);
    }

    /**
     * 错误提示
     *
     * @param content
     * @param args
     * @param data
     * @return
     */
    public static Message error(String content, Object args[], Object data) {
        return new Message(MessageType.ERROR, content, args, data);
    }

    /**
     * 请求超时
     *
     * @return
     */
    public static Message timeOut() {
        return new Message(MessageType.ERROR, "请求超时", null, null, "-1");
    }

}
