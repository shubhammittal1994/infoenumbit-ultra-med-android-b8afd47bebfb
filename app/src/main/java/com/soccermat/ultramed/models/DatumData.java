
package com.soccermat.ultramed.models;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class DatumData {

    @SerializedName("categoryId")
    private String mCategoryId;
    @SerializedName("name")
    private String mName;
    @SerializedName("timer")
    private String mTimer;

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTimer() {
        return mTimer;
    }

    public void setTimer(String timer) {
        mTimer = timer;
    }

}
