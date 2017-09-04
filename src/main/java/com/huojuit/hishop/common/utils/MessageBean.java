package com.huojuit.hishop.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by john on 14-4-25.
 */
@JsonInclude
public class MessageBean<T> implements Serializable {


    private static final long serialVersionUID = 8265397891697319656L;

    private int status = 1; //状态码 1 成功 2失败

    private String message; //消息

    private T data;


    public MessageBean() {
    }

    public MessageBean(int status) {
        this.status = status;
    }

    public MessageBean(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public MessageBean setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static List<String> setValidErrMsg(List<ObjectError> errorList) {
        List<String> errors = new ArrayList<String>();
        for (ObjectError objectError : errorList) {
            errors.add(objectError.getDefaultMessage());
        }
        return errors;
    }
}
