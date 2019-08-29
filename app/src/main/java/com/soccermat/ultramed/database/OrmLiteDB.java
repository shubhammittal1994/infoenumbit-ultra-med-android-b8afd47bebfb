package com.soccermat.ultramed.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.models.CalendarModel;
import com.soccermat.ultramed.models.ExerciseNameModel;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;


public class OrmLiteDB extends OrmLiteSqliteOpenHelper {
    /*"/mnt/sdcard/" +*/
    private static final String DATABASE_NAME = "UltraMed.db";
    private static final int DATABASE_VERSION = 1;
    public Dao<ExerciseNameModel, Integer> exerciseNameModels;
    public Dao<SubExerciseNameModel, Integer> subExerciseModels;
    public Dao<SubExerciseDoneModel, Integer> subExerciseDoneModels;
    public Dao<CalendarModel, Integer> calendarModels;


    public OrmLiteDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, ExerciseNameModel.class);
            TableUtils.createTable(connectionSource, SubExerciseNameModel.class);
            TableUtils.createTable(connectionSource, SubExerciseDoneModel.class);
            TableUtils.createTable(connectionSource, CalendarModel.class);

        } catch (SQLException e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {

            TableUtils.dropTable(connectionSource, ExerciseNameModel.class, true);
            TableUtils.dropTable(connectionSource, SubExerciseNameModel.class, true);
            TableUtils.dropTable(connectionSource, SubExerciseDoneModel.class, true);
            TableUtils.dropTable(connectionSource, CalendarModel.class, true);
            this.onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<ExerciseNameModel, Integer> exerciseName() throws SQLException {

        if (exerciseNameModels == null) {
            exerciseNameModels = getDao(ExerciseNameModel.class);
        }
        return exerciseNameModels;
    }

    public Dao<SubExerciseNameModel, Integer> exerciseSub() throws SQLException {

        if (subExerciseModels == null) {
            subExerciseModels = getDao(SubExerciseNameModel.class);
        }
        return subExerciseModels;
    }

    public Dao<SubExerciseDoneModel, Integer> exerciseSubDone() throws SQLException {

        if (subExerciseDoneModels == null) {
            subExerciseDoneModels = getDao(SubExerciseDoneModel.class);
        }
        return subExerciseDoneModels;
    }

    public Dao<CalendarModel, Integer> doaCalndar() throws SQLException {

        if (calendarModels == null) {
            calendarModels = getDao(CalendarModel.class);
        }
        return calendarModels;
    }
}
