package com.common.base.controller;

import com.common.utils.date.DateEditor;
import com.common.web.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

public class StandardController {
    public StandardController() {
    }

    @InitBinder
    protected void initBinder(WebDataBinder webdatabinder) {
        webdatabinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webdatabinder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    public Message successMessage(Object data) {
        return Message.success(data);
    }

    public Message successMessage(String content) {
        return Message.success(content);
    }

    public Message successMessage(String content, Object[] args) {
        return Message.success(content, args);
    }

    public Message successMessage(String content, Object[] args, Object data) {
        return Message.success(content, args, data);
    }

    public Message warnMessage(Object data) {
        return Message.warn(data);
    }

    public Message warnMessage(String content) {
        return Message.warn(content);
    }

    public Message warnMessage(String content, Object[] args) {
        return Message.warn(content, args);
    }

    public Message warnMessage(String content, Object[] args, Object data) {
        return Message.warn(content, args, data);
    }

    public Message errorMessage(Object data) {
        return Message.error(data);
    }

    public Message errorMessage(String content) {
        return Message.error(content);
    }

    public Message errorMessage(String content, Object[] args) {
        return Message.error(content, args);
    }

    public Message errorMessage(String content, Object[] args, Object data) {
        return Message.error(content, args, data);
    }

    public Message successMessage4Save() {
        return this.successMessage("message.success.save");
    }

    public Message successMessage4Save(Object data) {
        return this.successMessage("message.success.save", (Object[])null, data);
    }

    public Message successMessage4Delete() {
        return this.successMessage("message.success.delete");
    }

    public Message successMessage4Delete(Object data) {
        return this.successMessage("message.success.delete", (Object[])null, data);
    }

    public Message successMessage4Update() {
        return this.successMessage("message.success.update");
    }

    public Message successMessage4Update(Object data) {
        return this.successMessage("message.success.update", (Object[])null, data);
    }

    public Message warnMessage4Save() {
        return this.warnMessage("message.warn.save");
    }

    public Message warnMessage4Save(Object data) {
        return this.warnMessage("message.warn.save", (Object[])null, data);
    }

    public Message warnMessage4Delete() {
        return this.warnMessage("message.warn.delete");
    }

    public Message warnMessage4Delete(Object data) {
        return this.warnMessage("message.warn.delete", (Object[])null, data);
    }

    public Message warnMessage4Update() {
        return this.warnMessage("message.warn.update");
    }

    public Message warnMessage4Update(Object data) {
        return this.warnMessage("message.warn.update", (Object[])null, data);
    }

    public Message errorMessage4Save() {
        return this.errorMessage("message.error.save");
    }

    public Message errorMessage4Save(Object data) {
        return this.errorMessage("message.error.save", (Object[])null, data);
    }

    public Message errorMessage4Delete() {
        return this.errorMessage("message.error.delete");
    }

    public Message errorMessage4Delete(Object data) {
        return this.errorMessage("message.error.delete", (Object[])null, data);
    }

    public Message errorMessage4Update() {
        return this.errorMessage("message.error.update");
    }

    public Message errorMessage4Update(Object data) {
        return this.errorMessage("message.error.update", (Object[])null, data);
    }

    public Message successMessage4Option() {
        return this.successMessage("message.success.operation");
    }

    public Message successMessage4Option(Object obj) {
        return this.successMessage("message.success.operation", (Object[])null, obj);
    }

    public Message errorMessage4Option() {
        return this.errorMessage("message.error.operation");
    }

    public Message errorMessage4Option(Object obj) {
        return this.errorMessage("message.error.operation", (Object[])null, obj);
    }
}
