package com.soccermat.ultramed.models;

import com.google.gson.annotations.SerializedName;

public class loginResponse {



        @SerializedName("data")
        private Data mData;
        @SerializedName("message")
        private String mMessage;
        @SerializedName("success")
        private Boolean mSuccess;

        public Data getData() {
            return mData;
        }

        public void setData(Data data) {
            mData = data;
        }

        public String getMessage() {
            return mMessage;
        }

        public void setMessage(String message) {
            mMessage = message;
        }

        public Boolean getSuccess() {
            return mSuccess;
        }

        public void setSuccess(Boolean success) {
            mSuccess = success;
        }



}
