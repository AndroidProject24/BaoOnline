package com.toan_itc.data.exception;


/**
 * Created by Toan.IT
 * Date: 09/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class APIException extends RuntimeException {
    private ApiErrorModel mApiErrorModel;

    public APIException() {
        super();
    }

    public APIException(ApiErrorModel apiErrorModel) {
        mApiErrorModel = apiErrorModel;
    }

    public ApiErrorModel getApiErrorModel() {
        return mApiErrorModel;
    }
}
