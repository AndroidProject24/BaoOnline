package com.toan_itc.data.exception;

/**
 * Created by Toan.IT
 * Date: 09/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class ApiErrorModel {
    private String error_code;
    private String error_msg;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
