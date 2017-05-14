package com.toan_itc.data.exception;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class NetworkError extends Throwable {
    private final Throwable error;

    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
    }

    public String getMessage() {
        return error.getMessage();
    }

    public boolean isAuthFailure() {
        return error instanceof HttpException &&
                ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return "No Internet Connection!";
        if (this.error instanceof JSONException) return "Parse json error.";
        if (this.error instanceof TimeoutException) return "Connect timeout.";
        if (this.error instanceof HttpException) return "Something went wrong! Please try again.";
        if (this.error instanceof Exception) return "There is no internet connection";
        return "Error-Connect";
    }
    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
