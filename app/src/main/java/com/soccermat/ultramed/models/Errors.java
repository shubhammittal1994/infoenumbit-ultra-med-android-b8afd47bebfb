
package com.soccermat.ultramed.models;


import com.google.gson.annotations.SerializedName;


public class Errors {

    @SerializedName("error_code")
    private Long mErrorCode;
    @SerializedName("message")
    private String mMessage;

    public Long getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(Long errorCode) {
        mErrorCode = errorCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
