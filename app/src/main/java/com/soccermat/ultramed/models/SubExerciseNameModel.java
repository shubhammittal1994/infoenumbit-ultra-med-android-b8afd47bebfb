package com.soccermat.ultramed.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class SubExerciseNameModel implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(
            columnName = "ExerciseName"
    )
    public String exerciseName;

    @DatabaseField(
            columnName = "SubExerciseName"
    )
    public String subExerciseName;



    public SubExerciseNameModel(String exerciseName, String subExerciseName) {
        this.exerciseName = exerciseName;
        this.subExerciseName = subExerciseName;

    }

    public SubExerciseNameModel() {
    }
}

