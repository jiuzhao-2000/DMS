package com.jiuzhao73.util;

import com.github.pagehelper.PageInfo;

public class Result<T> {
    private int code;
    private String message;
    private T data;
    private PageInfo<Object> pageInfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PageInfo<Object> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<Object> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Result(int code, String message, T data, PageInfo<Object> pageInfo) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public Result() {
    }

    public static <T> boolean isEmpty(Result<T> result) {
        return result.code == 0;
    }

    public static <T> Result<T> success(String message, T data, PageInfo<Object> pageInfo) {
        return new Result<>(200, message, data, pageInfo);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(200, message, data, PageInfo.EMPTY);
    }

    public static <T> Result<T> warning(String message, T data, PageInfo<Object> pageInfo) {
        return new Result<>(300, message, data, pageInfo);
    }

    public static <T> Result<T> warning(String message, T data) {
        return new Result<T>(300, message, data, PageInfo.EMPTY);
    }

    public static <T> Result<T> error(String message, T data, PageInfo<Object> pageInfo) {
        return new Result<>(400, message, data, pageInfo);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result<T>(400, message, data, PageInfo.EMPTY);
    }

    public static <T> boolean isSuccess(Result<T> result) {
        return result.code == 200;
    }

    public static <T> Result<T> getEmptyResult() {
        return new Result<T>(0, "", null, PageInfo.EMPTY);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", pageInfo=" + pageInfo +
                '}';
    }
}
