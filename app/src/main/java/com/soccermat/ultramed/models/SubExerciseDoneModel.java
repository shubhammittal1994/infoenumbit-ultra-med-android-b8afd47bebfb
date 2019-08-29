package com.soccermat.ultramed.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class SubExerciseDoneModel implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(
            columnName = "ExerciseId"
    )
    public String exerciseId;

    @DatabaseField(
            columnName = "SubExerciseId"
    )
    public String subExerciseId;

    @DatabaseField(
            columnName = "Date"
    )
    public String date;

    @DatabaseField(
            columnName = "Status"
    )
    public String status;

    public SubExerciseDoneModel(String exerciseId, String subExerciseId, String date, String status) {
        this.exerciseId = exerciseId;
        this.subExerciseId = subExerciseId;
        this.date = date;
        this.status = status;
    }

    public SubExerciseDoneModel() {
    }
}

