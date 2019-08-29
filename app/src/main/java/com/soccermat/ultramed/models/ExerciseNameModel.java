package com.soccermat.ultramed.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class ExerciseNameModel implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(
            columnName = "ExerciseName"
    )
    public String exerciseName;


    public ExerciseNameModel(String exerciseName) {
        this.exerciseName = exerciseName;

    }

    public ExerciseNameModel() {
    }
}

