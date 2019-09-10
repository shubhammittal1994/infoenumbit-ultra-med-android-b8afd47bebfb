
package com.soccermat.ultramed.models;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class loginResponse {

    @SerializedName("data")
    private Data mData;
    @SerializedName("errors")
    private Errors mErrors;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("status_code")
    private Long mStatusCode;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
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
