package com.constructora.domain.model;

public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String url;
    private String message;
    private T data;

    public ApiResponse(boolean success, int code, String url, String message, T data) {
        this.success = success;
        this.code = code;
        this.url = url;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(int code, String url, String message, T data) {
        return new ApiResponse<>(true, code, url, message, data);
    }

    public static <T> ApiResponse<T> error(int code, String url, String message) {
        return new ApiResponse<>(false, code, url, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
