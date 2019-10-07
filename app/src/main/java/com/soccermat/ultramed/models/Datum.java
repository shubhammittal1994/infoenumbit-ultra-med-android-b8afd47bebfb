
package com.soccermat.ultramed.models;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Datum {

    @SerializedName("categoryId")
    private String mCategoryId;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("equipment")
    private String mEquipment;
    @SerializedName("exerciseId")
    private String mExerciseId;
    @SerializedName("files")
    private String mFiles;
    @SerializedName("limitation")
    private String mLimitation;
    @SerializedName("observation")
    private String mObservation;
    @SerializedName("position")
    private String mPosition;
    @SerializedName("repetition")
    private String mRepetition;
    @SerializedName("title")
    private String mTitle;

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEquipment() {
        return mEquipment;
    }

    public void setEquipment(String equipment) {
        mEquipment = equipment;
    }

    public String getExerciseId() {
        return mExerciseId;
    }

    public void setExerciseId(String exerciseId) {
        mExerciseId = exerciseId;
    }

    public String getFiles() {
        return mFiles;
    }

    public void setFiles(String files) {
        mFiles = files;
    }

    public String getLimitation() {
        return mLimitation;
    }

    public void setLimitation(String limitation) {
        mLimitation = limitation;
    }

    public String getObservation() {
        return mObservation;
    }

    public void setObservation(String observation) {
        mObservation = observation;
    }

    public String getPosition() {
        return mPosition;
    }

    public void setPosition(String position) {
        mPosition = position;
    }

    public String getRepetition() {
        return mRepetition;
    }

    public void setRepetition(String repetition) {
        mRepetition = repetition;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
