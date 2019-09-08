package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.soccermat.ultramed.R;
import com.soccermat.ultramed.connection.RetrofitClient;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.helper.Constants;
import com.soccermat.ultramed.helper.StaticSharedpreference;
import com.soccermat.ultramed.models.ExerciseNameModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;
import com.soccermat.ultramed.utils.PreferenceManager;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {
    OrmLiteDB ormLiteDb;
//    String exerciseName[] = {"cuello", "humbros", "codos", "cadera", "munacas", "dedos", "rodillas", "tobillos"};
    String exerciseName[] = {"cuello", "humbros", "codos", "cadera", "munacas", "rodillas", "tobillos"};

    LinkedHashMap<String, String> subExerciseMap = new LinkedHashMap<>();

    PreferenceManager pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setSubExerciseData();
        saveExerciseAndSubExercise();
        pref=new PreferenceManager(this);
        updateData();
       /* try {
            RetrofitClient.disableSSLCertificateChecking();
        }catch (Exception e)
        {
            e.printStackTrace();
        }*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (pref.getBooleanValues(Constants.IS_LOGGED_IN)) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                } else if (StaticSharedpreference.getInfo("page", SplashActivity.this).equalsIgnoreCase("notas")) {
                    startActivity(new Intent(SplashActivity.this, MedicalGradeActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }


//                if (StaticSharedpreference.getInfo("page", SplashActivity.this) == null ||
//                        StaticSharedpreference.getInfo("page", SplashActivity.this).equalsIgnoreCase("")) {
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                } else if (StaticSharedpreference.getInfo("page", SplashActivity.this).equalsIgnoreCase("notas")) {
//                    startActivity(new Intent(SplashActivity.this, MedicalGradeActivity.class));
//                } else {
//                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                }
                finish();


            }
        }, 4000);
    }


    private void saveExerciseAndSubExercise() {

        int value = StaticSharedpreference.getInt("first", this);

        if (value == 0) {
            StaticSharedpreference.saveInt("first", 1, this);

            for (String anExerciseName : exerciseName) {
                ExerciseNameModel exerciseNameModel = new ExerciseNameModel(anExerciseName);
                try {
                    Dao<ExerciseNameModel, Integer> dao = getHelper().exerciseName();
                    dao.create(exerciseNameModel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            for (Map.Entry<String, String> entry : subExerciseMap.entrySet()) {

                SubExerciseNameModel subExerciseModel = new SubExerciseNameModel(entry.getValue(), entry.getKey());

                try {
                    Dao<SubExerciseNameModel, Integer> dao = getHelper().exerciseSub();
                    dao.create(subExerciseModel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            try {
                Dao<SubExerciseNameModel, Integer> dao = getHelper().exerciseSub();
                List<SubExerciseNameModel> list = dao.queryForAll();

                for (int i = 0; i < list.size(); i++) {
                    SubExerciseNameModel exerciseDoneModel1 = list.get(i);
                    Log.v("akram", "list name exe id  = " + exerciseDoneModel1.id);
                    Log.v("akram", "list name exe name  = " + exerciseDoneModel1.subExerciseName);
                    Log.v("akram", "======================== ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void updateData() {


        try {
            Dao<SubExerciseNameModel, Integer> dao = getHelper().exerciseSub();

            UpdateBuilder<SubExerciseNameModel, Integer> updateBuilder = dao.updateBuilder();
            Where whereClause = updateBuilder.where();

            whereClause.eq("ExerciseName", "cuello").and().eq("SubExerciseName", "cuello_second");

            updateBuilder.updateColumnValue("SubExerciseName", "hello");
            updateBuilder.update();
            // dao.update(exerciseDoneModel1);
        } catch (SQLException e) {
            Log.v("akram", "ex" + e);
            e.printStackTrace();
        }

        try {
            Dao<SubExerciseNameModel, Integer> dao = getHelper().exerciseSub();
            List<SubExerciseNameModel> list = dao.queryForAll();

            for (int i = 0; i < list.size(); i++) {
                SubExerciseNameModel exerciseDoneModel1 = list.get(i);
                Log.v("akram", "exercise name  = " + exerciseDoneModel1.exerciseName);
                Log.v("akram", "sub name=====  = " + exerciseDoneModel1.subExerciseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private void setSubExerciseData() {

//        String subExerciseName[] = {"cuello_first", "cuello_second", "cuello_third", "humbros_first", "humbros_second", "humbros_third",
//                "codos_first", "codos_second","codos_third", "cadera_first", "cadera_second", "cadera_third","cadera_fourth","cadera_fifth","cadera_sixth", "munacas_first", "munacas_second",
//                "munacas_third","munacas_fourth", "dedos_first", "dedos_second", "rodillas_first", "rodillas_second","rodillas_third","rodillas_fourth",
//                "tobillos_first", "tobillos_second", "tobillos_third"};

        String subExerciseName[] = {"cuello_first", "cuello_second", "cuello_third", "humbros_first", "humbros_second", "humbros_third",
                "codos_first", "codos_second","codos_third", "cadera_first", "cadera_second", "cadera_third","cadera_fourth","cadera_fifth","cadera_sixth", "munacas_first", "munacas_second",
                "munacas_third","munacas_fourth", "munacas_fifth", "munacas_sixth", "rodillas_first", "rodillas_second","rodillas_third","rodillas_fourth",
                "tobillos_first", "tobillos_second", "tobillos_third"};

        subExerciseMap.put(subExerciseName[0], exerciseName[0]);
        subExerciseMap.put(subExerciseName[1], exerciseName[0]);
        subExerciseMap.put(subExerciseName[2], exerciseName[0]);

        subExerciseMap.put(subExerciseName[3], exerciseName[1]);
        subExerciseMap.put(subExerciseName[4], exerciseName[1]);
        subExerciseMap.put(subExerciseName[5], exerciseName[1]);

        subExerciseMap.put(subExerciseName[6], exerciseName[2]);
        subExerciseMap.put(subExerciseName[7], exerciseName[2]);
        subExerciseMap.put(subExerciseName[8], exerciseName[2]);

        subExerciseMap.put(subExerciseName[9],  exerciseName[3]);
        subExerciseMap.put(subExerciseName[10], exerciseName[3]);
        subExerciseMap.put(subExerciseName[11], exerciseName[3]);
        subExerciseMap.put(subExerciseName[12], exerciseName[3]);
        subExerciseMap.put(subExerciseName[13], exerciseName[3]);
        subExerciseMap.put(subExerciseName[14], exerciseName[3]);

        subExerciseMap.put(subExerciseName[15], exerciseName[4]);
        subExerciseMap.put(subExerciseName[16], exerciseName[4]);
        subExerciseMap.put(subExerciseName[17], exerciseName[4]);
        subExerciseMap.put(subExerciseName[18], exerciseName[4]);
        subExerciseMap.put(subExerciseName[19], exerciseName[4]);
        subExerciseMap.put(subExerciseName[20], exerciseName[4]);

        subExerciseMap.put(subExerciseName[21], exerciseName[5]);
        subExerciseMap.put(subExerciseName[22], exerciseName[5]);
        subExerciseMap.put(subExerciseName[23], exerciseName[5]);
        subExerciseMap.put(subExerciseName[24], exerciseName[5]);

        subExerciseMap.put(subExerciseName[25], exerciseName[6]);
        subExerciseMap.put(subExerciseName[26], exerciseName[6]);
        subExerciseMap.put(subExerciseName[27], exerciseName[6]);

    }


    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(SplashActivity.this, OrmLiteDB.class);
        }
        return ormLiteDb;
    }
}
