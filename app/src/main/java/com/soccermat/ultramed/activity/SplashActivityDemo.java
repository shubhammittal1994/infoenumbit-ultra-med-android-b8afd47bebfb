package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/*https://stackoverflow.com/questions/12497287/how-to-update-column-with-ormlite*/
public class SplashActivityDemo extends AppCompatActivity {
    OrmLiteDB ormLiteDb;
    String exerciseName[] = {"cuello", "humbros", "codos", "cadera", "munacas", "dedos", "rodillas", "tobillos"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //saveExerciseAndSubExercise();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivityDemo.this, InformationActivity.class));
                finish();
            }
        }, 3000);
    }

   /* private void saveExerciseAndSubExercise() {

       // int value = StaticSharedpreference.getInt("first",this);

       // if(value==0){

            SubExerciseDoneModel exerciseDoneModel = new SubExerciseDoneModel("08-10-2018", "sdf", "fgh", "fh", "fgh", "gh", "ghj", "ghj", "gjh");
            try {
                Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseName();
                dao.create(exerciseDoneModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseDone();
                List<SubExerciseDoneModel> list = dao.queryForAll();
                Log.v("akram", "list done " + list.size());

                SubExerciseDoneModel exerciseDoneModel1 = list.get(0);

                Log.v("akram", "list date " + exerciseDoneModel1.date);
                Log.v("akram", "list cuello " + exerciseDoneModel1.cuello);

            } catch (SQLException e) {
                e.printStackTrace();
            }

           // SubExerciseDoneModel exerciseDoneModel1 = new SubExerciseDoneModel();
           // exerciseDoneModel1.setCuello("new Cuello");

            try {
                Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseDone();

                UpdateBuilder<SubExerciseDoneModel, Integer> updateBuilder = dao.updateBuilder();
// set the criteria like you would a QueryBuilder
                updateBuilder.where().eq("Date", "08-10-2018");
                updateBuilder.updateColumnValue("Cuello" *//* column *//*, "new Cuello" *//* value *//*);
                updateBuilder.update();
               // dao.update(exerciseDoneModel1);
            } catch (SQLException e) {
                Log.v("akram", "ex" + e);
                e.printStackTrace();
            }

            try {
                Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseDone();
                List<SubExerciseDoneModel> list = dao.queryForAll();
                Log.v("akram", "=================================================================");
                Log.v("akram", "list done " + list.size());

                SubExerciseDoneModel exerciseDoneModel112 = list.get(0);

                Log.v("akram", "list date " + exerciseDoneModel112.date);
                Log.v("akram", "list cuello " + exerciseDoneModel112.cuello);

            } catch (SQLException e) {
                e.printStackTrace();
            }
      //  }

    }*/

    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(SplashActivityDemo.this, OrmLiteDB.class);
        }
        return ormLiteDb;
    }
}
