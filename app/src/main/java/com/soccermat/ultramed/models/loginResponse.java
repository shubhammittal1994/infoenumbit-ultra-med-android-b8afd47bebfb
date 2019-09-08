
package com.soccermat.ultramed.models;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class LoginResponse {

    @SerializedName("data")
    private DataLogin mData;
    @SerializedName("errors")
    private Errors mErrors;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("status_code")
    private Long mStatusCode;

    public DataLogin getData() {
        return mData;
    }

    public void setData(DataLogin data) {
        mData = data;
    }

    public Errors getErrors() {
        return mErrors;
    }

    public void setErrors(Errors errors) {
        mErrors = errors;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Long getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(Long statusCode) {
        mStatusCode = statusCode;
    }

}

 class DataLogin {


}

