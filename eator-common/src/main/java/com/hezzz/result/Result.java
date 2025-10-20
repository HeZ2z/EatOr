package com.hezzz.result;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }


    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> failed() {
        return new Result<>(ResultCode.FAILED);
    }

    public static <T> Result<T> failed(String customMessage) {
        Result<T> result = new Result<>(ResultCode.FAILED);
        result.setMessage(customMessage);
        return result;
    }

}
