
package com.soccermat.ultramed.models;


import com.google.gson.annotations.SerializedName;


public class RegisterResponse {

    private String status_code;

    private Data data;

    private String message;

    private String[] errors;

    private String status;

    public String getStatus_code ()
    {
        return status_code;
    }

    public void setStatus_code (String status_code)
    {
        this.status_code = status_code;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String[] getErrors ()
    {
        return errors;
    }

    public void setErrors (String[] errors)
    {
        this.errors = errors;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status_code = "+status_code+", data = "+data+", message = "+message+", errors = "+errors+", status = "+status+"]";
    }

}
class Data {

    @SerializedName("city")
    private String mCity;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("firstname")
    private String mFirstname;
    @SerializedName("isverified")
    private Long mIsverified;
    @SerializedName("lastname")
    private String mLastname;
    @SerializedName("name")
    private String mName;
    @SerializedName("user_id")
    private Long mUserId;
    @SerializedName("verification_message")
    private String mVerificationMessage;
    @SerializedName("zipcode")
    private String mZipcode;

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    public Long getIsverified() {
        return mIsverified;
    }

    public void setIsverified(Long isverified) {
        mIsverified = isverified;
    }

    public String getLastname() {
        return mLastname;
    }

    public void setLastname(String lastname) {
        mLastname = lastname;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public String getVerificationMessage() {
        return mVerificationMessage;
    }

    public void setVerificationMessage(String verificationMessage) {
        mVerificationMessage = verificationMessage;
    }

    public String getZipcode() {
        return mZipcode;
    }

    public void setZipcode(String zipcode) {
        mZipcode = zipcode;
    }

}

