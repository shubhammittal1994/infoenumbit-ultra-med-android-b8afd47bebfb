package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.adapter.CustomPagerAdapter;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.models.CalendarModel;
import com.soccermat.ultramed.models.ExerciseNameModel;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.soccermat.ultramed.fragment.MyExerciseFragment.isChangedImage;

public class ExerciseDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    CardView btnRealizado, btnSaltar, btnAnterior,btnAnteriorFull;
    Handler handler;
    int delay = 500; //milliseconds
    RelativeLayout relativeSecond, relativeFirst;
    boolean iconVisible = false;
    String exerciseName;
    OrmLiteDB ormLiteDb;

    int arrCuello[] = {R.drawable.cuello_1, R.drawable.cuello_2, R.drawable.cuello_3};
    int arrHumbros[] = {R.drawable.hombros_1, R.drawable.hombros_2, R.drawable.hombros_3};
    int arrCodos[] = {R.drawable.codos_1, R.drawable.codos_2, R.drawable.codos_3};
    int arrCadera[] = {R.drawable.cadena_1, R.drawable.cadena_2, R.drawable.cadena_3, R.drawable.cadena_4, R.drawable.cadena_5, R.drawable.cadena_6};
    int arrMunacas[] = {R.drawable.muneca_1, R.drawable.muneca_2, R.drawable.muneca_3,R.drawable.muneca_4,R.drawable.dedos_1, R.drawable.dedos_2};
    int arrDedos[] = {R.drawable.dedos_1, R.drawable.dedos_2};
    int arrRodillas[] = {R.drawable.rodillas_1, R.drawable.rodillas_2, R.drawable.rodillas_3, R.drawable.rodillas_4};
    int arrTobillos[] = {R.drawable.tobillos_1, R.drawable.tobillos_2, R.drawable.tobillos_3};

    String subExerciseNameFirst[];
    String subExerciseNameSecond[];
    String subExerciseNameThird[];
    String subExerciseNameFourth[];

     int arrSubExerciseIds[];
     int exerciseId;
    ViewPager viewPager;
  int viewPagerCount = 0;
    TextView tvFirst, tvSecond, tvThird,tvFourth;
    LinearLayout linearTextThird, linearTextSecond,linearTextFourth,backLL;
    TextView tvExerciseTitle,tvCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        intiViews();

        Intent intent = getIntent();
        exerciseName = intent.getStringExtra("id");
        getExerciseData();
        setData();

    }

    private void intiViews() {
        backLL = findViewById(R.id.backLL);
        btnRealizado = findViewById(R.id.btnRealizado);
        btnSaltar = findViewById(R.id.btnSaltar);
        btnAnterior = findViewById(R.id.btnAnterior);
        btnAnteriorFull = findViewById(R.id.btnAnteriorFull);
        relativeSecond = findViewById(R.id.relativeSecond);
        relativeFirst = findViewById(R.id.relativeFirst);
        tvFirst = findViewById(R.id.tvFirst);
        tvSecond = findViewById(R.id.tvSecond);
        tvThird = findViewById(R.id.tvThird);
        tvFourth = findViewById(R.id.tvFourth);
        linearTextThird = findViewById(R.id.linearTextThird);
        linearTextSecond = findViewById(R.id.linearTextSecond);
        linearTextFourth= findViewById(R.id.linearTextFourth);
        viewPager = findViewById(R.id.viewpager);
        tvExerciseTitle = findViewById(R.id.tvExerciseTitle);
        tvCount = findViewById(R.id.tvCount);
        btnAnteriorFull.setVisibility(View.GONE);
        clickListener();

        setAnimation();

    }


    private void clickListener() {
        btnRealizado.setOnClickListener(this);
        btnSaltar.setOnClickListener(this);
        btnAnterior.setOnClickListener(this);
        backLL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRealizado:
                isChangedImage = true;

                updateSubCategoryData("1");

                if (viewPagerCount + 1 < arrSubExerciseIds.length) {

                    viewPagerCount++;
                    viewPager.setCurrentItem(viewPagerCount);
                    setText(viewPagerCount);
                } else {
                    onBackPressed();
                }

                tvCount.setText(""+(viewPagerCount+1));
                if(tvCount.getText().toString().equals("1")){
                    btnAnteriorFull.setVisibility(View.GONE);

                }else {
                    btnAnteriorFull.setVisibility(View.VISIBLE);
                }

                //  onBackPressed();
                break;

            case R.id.btnSaltar:

                updateSubCategoryData("0");

                if (viewPagerCount + 1 < arrSubExerciseIds.length) {
                    btnAnteriorFull.setVisibility(View.VISIBLE);
                    viewPagerCount++;
                    viewPager.setCurrentItem(viewPagerCount);
                    setText(viewPagerCount);
                } else {
                    onBackPressed();
                }
                tvCount.setText(""+(viewPagerCount+1));

                break;

            case R.id.btnAnterior:
                if (viewPagerCount > 0) {
                    viewPagerCount--;
                    if (viewPagerCount < arrSubExerciseIds.length) {

                        viewPager.setCurrentItem(viewPagerCount);
                    }
                    setText(viewPagerCount);

                }

                tvCount.setText(""+(viewPagerCount+1));
                if(tvCount.getText().toString().equals("1")){
                    btnAnteriorFull.setVisibility(View.GONE);

                }else {
                    btnAnteriorFull.setVisibility(View.VISIBLE);
                }
                break;
                 case R.id.backLL:
                onBackPressed();
                break;

        }
    }

    private void setData() {
        if (exerciseId == 1) {
            tvExerciseTitle.setText("Ejercicios para el Cuello");
//            btnAnteriorFull.setVisibility(View.GONE);
            subExerciseNameFirst = getResources().getStringArray(R.array.cuello1);
            subExerciseNameSecond = getResources().getStringArray(R.array.cuello2);
            subExerciseNameThird = getResources().getStringArray(R.array.cuello3);
            viewPager.setAdapter(new CustomPagerAdapter(this, arrCuello));
        } else if (exerciseId == 2) {
            tvExerciseTitle.setText("Ejercicios para los Hombros");
            subExerciseNameFirst = getResources().getStringArray(R.array.humbros1);
            subExerciseNameSecond = getResources().getStringArray(R.array.humbros2);
            subExerciseNameThird = getResources().getStringArray(R.array.humbros3);

            viewPager.setAdapter(new CustomPagerAdapter(this, arrHumbros));
        } else if (exerciseId == 3) {
//            tvExerciseTitle.setText("Ejercicios para los Columna");
            tvExerciseTitle.setText("Ejercicios para los Codos");
            subExerciseNameFirst = getResources().getStringArray(R.array.codos1);
            subExerciseNameSecond = getResources().getStringArray(R.array.codos2);
            subExerciseNameThird = getResources().getStringArray(R.array.codos3);

            viewPager.setAdapter(new CustomPagerAdapter(this, arrCodos));
        } else if (exerciseId == 4) {
//            tvExerciseTitle.setText("Ejercicios para los Cadera");
            tvExerciseTitle.setText("Ejercicios para la Columna");
            subExerciseNameFirst = getResources().getStringArray(R.array.cadena1);
            subExerciseNameSecond = getResources().getStringArray(R.array.cadena2);
            subExerciseNameThird = getResources().getStringArray(R.array.cadena3);
            subExerciseNameFourth = getResources().getStringArray(R.array.cadena4);
            viewPager.setAdapter(new CustomPagerAdapter(this, arrCadera));
        } else if (exerciseId == 5) {
            tvExerciseTitle.setText("Ejercicios para las Manos");
            subExerciseNameFirst = getResources().getStringArray(R.array.muneca1);
            subExerciseNameSecond = getResources().getStringArray(R.array.muneca2);


//        } else if (exerciseId == 6) {
//            tvExerciseTitle.setText("Ejercicios para los Manos");
//            tvExerciseTitle.setText("Ejercicios para las Manos");
//            subExerciseNameThird = getResources().getStringArray(R.array.dedos1);
//            subExerciseNameSecond = getResources().getStringArray(R.array.dedos2);
//            viewPager.setAdapter(new CustomPagerAdapter(this, arrDedos));
            viewPager.setAdapter(new CustomPagerAdapter(this, arrMunacas));
        } else if (exerciseId == 6) {
            tvExerciseTitle.setText("Ejercicios para las Rodillas");
            subExerciseNameFirst = getResources().getStringArray(R.array.rodillas1);
            subExerciseNameSecond = getResources().getStringArray(R.array.rodillas2);
            subExerciseNameThird = getResources().getStringArray(R.array.rodillas3);
            viewPager.setAdapter(new CustomPagerAdapter(this, arrRodillas));

        } else {
            tvExerciseTitle.setText("Ejercicios para los Tobillos");
            subExerciseNameFirst = getResources().getStringArray(R.array.tobillos1);
            subExerciseNameSecond = getResources().getStringArray(R.array.tobillos2);
            subExerciseNameThird = getResources().getStringArray(R.array.tobillos3);
            viewPager.setAdapter(new CustomPagerAdapter(this, arrTobillos));
        }
        setText(0);
    }

    private void setText(int viewPagerCount) {
        tvFirst.setText(subExerciseNameFirst[viewPagerCount]);

        if (subExerciseNameSecond == null || subExerciseNameSecond.length <= viewPagerCount ) {
            linearTextSecond.setVisibility(View.GONE);
        } else {
            linearTextSecond.setVisibility(View.VISIBLE);
            tvSecond.setText(subExerciseNameSecond[viewPagerCount]);
        }

        if (subExerciseNameThird == null || subExerciseNameThird.length <= viewPagerCount ) {
            linearTextThird.setVisibility(View.GONE);
        } else {

            if(subExerciseNameThird[viewPagerCount].equalsIgnoreCase("aa")){
                linearTextThird.setVisibility(View.GONE);
            }else {
                linearTextThird.setVisibility(View.VISIBLE);
                tvThird.setText(subExerciseNameThird[viewPagerCount]);
            }
        }if (subExerciseNameFourth == null || subExerciseNameFourth.length <= viewPagerCount ) {
            linearTextFourth.setVisibility(View.GONE);
        } else {

            if(subExerciseNameFourth[viewPagerCount].equalsIgnoreCase("aa")){
                linearTextFourth.setVisibility(View.GONE);
            }else {
                linearTextFourth.setVisibility(View.VISIBLE);
                tvFourth.setText(subExerciseNameFourth[viewPagerCount]);
            }
        }
    }

    String formattedDate;

    private void updateSubCategoryData(String status) {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c);

        //check subcategory also available in table or not
        List<SubExerciseDoneModel> list1 = new ArrayList<>();
        try {
            Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseSubDone();
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("ExerciseId", exerciseId + "");

                stringObjectMap.put("SubExerciseId", arrSubExerciseIds[viewPagerCount] + "");
            stringObjectMap.put("Date", formattedDate);

            list1 = dao.queryForFieldValues(stringObjectMap);
        } catch (SQLException e) {
            Log.v("akram", "exception " + e);
            e.printStackTrace();
        }

        //if subcategory not available
        if (list1.size() != 0) {

            SubExerciseDoneModel subExerciseDoneModel = list1.get(0);

            boolean checkCondition = true;
            if (status.equalsIgnoreCase("0"))
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    checkCondition = false;

            if (checkCondition) {
                try {
                    Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseSubDone();

                    UpdateBuilder<SubExerciseDoneModel, Integer> updateBuilder = dao.updateBuilder();
                    Where whereClause = updateBuilder.where();

                        whereClause.eq("ExerciseId", exerciseId + "").and()

                                .eq("SubExerciseId", arrSubExerciseIds[viewPagerCount] + "").and()
                                .eq("Date", formattedDate);

                    updateBuilder.updateColumnValue("Status", status);
                    updateBuilder.update();

                    // dao.update(exerciseDoneModel1);
                } catch (SQLException e) {
                    Log.v("akram", "ex" + e);
                    e.printStackTrace();
                }
            }
        } else {


            SubExerciseDoneModel subExerciseDoneModel = new SubExerciseDoneModel
                    (exerciseId + "", arrSubExerciseIds[viewPagerCount] + "", formattedDate, status);


            try {
                Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseSubDone();
                dao.create(subExerciseDoneModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }

        List<SubExerciseDoneModel> subExerciseDoneModels = new ArrayList<>();
        try {
            Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseSubDone();
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("Status", 1 + "");
            stringObjectMap.put("Date", formattedDate);

            subExerciseDoneModels = dao.queryForFieldValues(stringObjectMap);
        } catch (SQLException e) {
            Log.v("akram", "exception " + e);
            e.printStackTrace();
        }

        Log.v("akram", "array size " + subExerciseDoneModels.size());
        if (subExerciseDoneModels.size() == 21) {

            setCalendarData("1");
        } else if (subExerciseDoneModels.size() == 1) {
            setCalendarData("0");
        }

        /*try {
            Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseSubDone();
            List<SubExerciseDoneModel> list = dao.queryForAll();

            for (int i = 0; i < list.size(); i++) {
                SubExerciseDoneModel exerciseDoneModel1 = list.get(i);
                Log.v("akram", "id  = " + exerciseDoneModel1.id);
                Log.v("akram", "exer  = " + exerciseDoneModel1.exerciseId);
                Log.v("akram", "sub  = " + exerciseDoneModel1.subExerciseId);
                Log.v("akram", "status  = " + exerciseDoneModel1.status);
                Log.v("akram", "date  = " + exerciseDoneModel1.date);
                Log.v("akram", "=                                                                     =");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
}


    //if all subcategory exercise done then entry in new table
    private void setCalendarData(String status) {

        //get if current date available in table
        List<CalendarModel> list = new ArrayList<>();
        try {
            Dao<CalendarModel, Integer> dao = getHelper().doaCalndar();
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("Date", formattedDate);

            list = dao.queryForFieldValues(stringObjectMap);
        } catch (SQLException e) {
            Log.v("akram", "exception " + e);
            e.printStackTrace();
        }

        //if current date not available
        if (list.size() == 0) {

            String monthYearArr[] = formattedDate.split("-");


            CalendarModel calendarModel = new CalendarModel
                    (formattedDate, "0", monthYearArr[1], monthYearArr[2]);
            try {
                Dao<CalendarModel, Integer> dao = getHelper().doaCalndar();
                dao.create(calendarModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Dao<CalendarModel, Integer> dao = getHelper().doaCalndar();

                UpdateBuilder<CalendarModel, Integer> updateBuilder = dao.updateBuilder();
                Where whereClause = updateBuilder.where();

                whereClause.eq("Date", formattedDate);

                updateBuilder.updateColumnValue("Status", status);
                updateBuilder.update();

                // dao.update(exerciseDoneModel1);
            } catch (SQLException e) {
                Log.v("akram", "ex" + e);
                e.printStackTrace();
            }
        }


        try {
            Dao<CalendarModel, Integer> dao = getHelper().doaCalndar();
            List<CalendarModel> calendarModels = dao.queryForAll();

            for (int i = 0; i < calendarModels.size(); i++) {
                CalendarModel exerciseDoneModel1 = calendarModels.get(i);
                Log.v("akram", "exercise name calendra  = " + exerciseDoneModel1.date);
                Log.v("akram", "sub name===== calendra  = " + exerciseDoneModel1.month);
                Log.v("akram", "sub name===== calendra  = " + exerciseDoneModel1.year);
            }
        } catch (SQLException e) {
            Log.v("akram", "ex12" + e);
            e.printStackTrace();
        }


    }

    private void getExerciseData() {
        try {
            // Dao<SubExerciseNameModel, Integer> dao = getHelper().exerciseSub();
            List<SubExerciseNameModel> list = getHelper().exerciseSub().queryForEq("ExerciseName", exerciseName);
            arrSubExerciseIds = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                SubExerciseNameModel exerciseDoneModel1 = list.get(i);
                arrSubExerciseIds[i] = exerciseDoneModel1.id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<ExerciseNameModel> list = getHelper().exerciseName().queryForEq("ExerciseName", exerciseName);

            for (int i = 0; i < list.size(); i++) {
                ExerciseNameModel exerciseNameModel = list.get(i);
                exerciseId = exerciseNameModel.id;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setAnimation() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //do something
                if (iconVisible) {
                    iconVisible = false;
                    relativeSecond.setVisibility(View.GONE);
                    relativeFirst.setVisibility(View.VISIBLE);
                } else {
                    iconVisible = true;
                    relativeSecond.setVisibility(View.VISIBLE);
                    relativeFirst.setVisibility(View.GONE);
                }

                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(ExerciseDetailsActivity.this, OrmLiteDB.class);
        }
        return ormLiteDb;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }


}
