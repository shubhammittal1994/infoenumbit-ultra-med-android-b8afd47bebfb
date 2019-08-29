package com.soccermat.ultramed.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.activity.ExerciseDetailsActivity;
import com.soccermat.ultramed.activity.HomeActivity;
import com.soccermat.ultramed.activity.InformationActivity;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.helper.StaticSharedpreference;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyExerciseFragment extends Fragment implements View.OnClickListener {
    View rootView;

    public static boolean isChangedImage = false;
    ImageView imgDummy;
    CardView btnCuello, btnHumbros, btnCodos, btnCadera, btnMunecas, btnDedos, btnRodillas, btnTobillos;
    ImageView imgWatch1, imgWatch2, imgWatch3, imgWatch4, imgWatch5, imgWatch6, imgWatch7, imgWatch8;
    TextView tvTime1, tvTime2, tvTime3, tvTime4, tvTime5, tvTime6, tvTime7, tvTime8;
    OrmLiteDB ormLiteDb;
    ImageView imgRodilla,imgBody1, imgBody2, imgBody3, imgBody4, imgBody5, imgBody6, imgBody7, imgBody8;
    View click1, click2, click21, click3, click31, click4, click41, click5, click51, click7, click71, click8, click81;
    String strImg1="",strImg2="",strImg3="",strImg4="",strImg5="",strImg6="",strImg7="",strImg8="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_my_exercise, container, false);
        initViews();

        return rootView;
    }

    private void initViews() {

        imgDummy = rootView.findViewById(R.id.imgDummy);

        btnCuello = rootView.findViewById(R.id.btnCuello);
        btnHumbros = rootView.findViewById(R.id.btnHumbros);
        btnCodos = rootView.findViewById(R.id.btnCodos);
        btnCadera = rootView.findViewById(R.id.btnCadera);
        btnMunecas = rootView.findViewById(R.id.btnMunecas);
        btnDedos = rootView.findViewById(R.id.btnDedos);
        btnRodillas = rootView.findViewById(R.id.btnRodillas);
        btnTobillos = rootView.findViewById(R.id.btnTobillos);

        imgWatch1 = rootView.findViewById(R.id.imgWatch1);
        imgWatch2 = rootView.findViewById(R.id.imgWatch2);
        imgWatch3 = rootView.findViewById(R.id.imgWatch3);
        imgWatch4 = rootView.findViewById(R.id.imgWatch4);
        imgWatch5 = rootView.findViewById(R.id.imgWatch5);
        imgWatch6 = rootView.findViewById(R.id.imgWatch6);
        imgWatch7 = rootView.findViewById(R.id.imgWatch7);
        imgWatch8 = rootView.findViewById(R.id.imgWatch8);
        imgRodilla = rootView.findViewById(R.id.imgRodilla);

        imgBody1 = rootView.findViewById(R.id.imgBody1);
        imgBody2 = rootView.findViewById(R.id.imgBody2);
        imgBody3 = rootView.findViewById(R.id.imgBody3);
        imgBody4 = rootView.findViewById(R.id.imgBody4);
        imgBody5 = rootView.findViewById(R.id.imgBody5);
        imgBody6 = rootView.findViewById(R.id.imgBody6);
        imgBody7 = rootView.findViewById(R.id.imgBody7);
        imgBody8 = rootView.findViewById(R.id.imgBody8);

        tvTime1 = rootView.findViewById(R.id.tvTime1);
        tvTime2 = rootView.findViewById(R.id.tvTime2);
        tvTime3 = rootView.findViewById(R.id.tvTime3);
        tvTime4 = rootView.findViewById(R.id.tvTime4);
        tvTime5 = rootView.findViewById(R.id.tvTime5);
        tvTime6 = rootView.findViewById(R.id.tvTime6);
        tvTime7 = rootView.findViewById(R.id.tvTime7);
        tvTime8 = rootView.findViewById(R.id.tvTime8);


        click1 = rootView.findViewById(R.id.click1);
        click2 = rootView.findViewById(R.id.click2);
        click21 = rootView.findViewById(R.id.click21);
        click3 = rootView.findViewById(R.id.click3);
        click31 = rootView.findViewById(R.id.click31);
        click4 = rootView.findViewById(R.id.click4);
        click41 = rootView.findViewById(R.id.click41);
        click5 = rootView.findViewById(R.id.click5);
        click51 = rootView.findViewById(R.id.click51);
        click7 = rootView.findViewById(R.id.click7);
        click71 = rootView.findViewById(R.id.click71);
        click8 = rootView.findViewById(R.id.click8);
        click81 = rootView.findViewById(R.id.click81);

        click1.setOnClickListener(this);
        click2.setOnClickListener(this);
        click21.setOnClickListener(this);
        click3.setOnClickListener(this);
        click31.setOnClickListener(this);
        click4.setOnClickListener(this);
        click41.setOnClickListener(this);
        click5.setOnClickListener(this);
        click51.setOnClickListener(this);
        click7.setOnClickListener(this);
        click71.setOnClickListener(this);
        click8.setOnClickListener(this);
        click81.setOnClickListener(this);

        btnCuello.setOnClickListener(this);
        btnHumbros.setOnClickListener(this);
        btnCodos.setOnClickListener(this);
        btnCadera.setOnClickListener(this);
        btnMunecas.setOnClickListener(this);
        btnDedos.setOnClickListener(this);
        btnRodillas.setOnClickListener(this);
        btnTobillos.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ExerciseDetailsActivity.class);
        switch (view.getId()) {
            case R.id.btnCuello:
            case R.id.click1:

                intent.putExtra("id", "cuello");
                break;

            case R.id.btnHumbros:
            case R.id.click2:
            case R.id.click21:
                intent.putExtra("id", "humbros");
                break;

            case R.id.btnCodos:
            case R.id.click3:
            case R.id.click31:
                intent.putExtra("id", "codos");
                break;

            case R.id.btnCadera:
            case R.id.click4:
            case R.id.click41:
                intent.putExtra("id", "cadera");
                break;

            case R.id.btnMunecas:
            case R.id.click5:
            case R.id.click51:
                intent.putExtra("id", "munacas");
                break;
//                case R.id.btnDedos:
//                intent.putExtra("id", "dedos");
//                break;

            case R.id.btnRodillas:
            case R.id.click7:
            case R.id.click71:
                intent.putExtra("id", "rodillas");
                break;

            case R.id.btnTobillos:
            case R.id.click8:
            case R.id.click81:

                intent.putExtra("id", "tobillos");
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        tvTime1.setTextColor(getActivity().getResources().getColor(R.color.black));
        btnCuello.setCardBackgroundColor(getActivity().getResources().getColor(R.color.accept_button));
        imgBody1.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_head_gray));
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        List<SubExerciseDoneModel> list1 = new ArrayList<>();
        try {
            Dao<SubExerciseDoneModel, Integer> dao = getHelper().exerciseSubDone();

            list1 = dao.queryForEq("Date", formattedDate);
        } catch (SQLException e) {
            Log.v("akram", "exception " + e);
            e.printStackTrace();
        }

        int cuello = 0, hubros = 0, codos = 0, cadera = 0, munacas = 0, dedos = 0, rodillas = 0, tobillas = 0;

        for (int i = 0; i < list1.size(); i++) {
            SubExerciseDoneModel subExerciseDoneModel = list1.get(i);

            if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("1") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("2")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("3")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")){
                    cuello++;
                }else {
                    cuello=0;
                }


            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("4") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("5")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("6")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")){
                    hubros++;
                }else {
                    hubros=0;
                }


            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("7") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("8")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("9")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")){
                    codos++;
                }else {
                    codos=0;
                }


            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("10") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("11")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("12")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("13")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("14")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("15")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")){
                    cadera++;
                }else {
                    cadera=0;
                }


            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("16") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("17")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("18")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("19")||subExerciseDoneModel.subExerciseId.equalsIgnoreCase("20") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("21")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")){
                    munacas++;}else {
                    munacas=0;
                }

            }
//            else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("20") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("21")) {
//                if (subExerciseDoneModel.status.equalsIgnoreCase("1")) {
//                    dedos++;
//                }else {
//                    dedos=0;
//                }
//            }
            else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("22") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("23")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("24")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("25")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")) {
                    rodillas++;
                }else {
                    rodillas=0;
                }
            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("26") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("27")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("28")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1")) {
                    tobillas++;
                }else {
                    tobillas=0;
                }
            }
        }

        if (cuello == 3) {
            strImg1="true";
            tvTime1.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch1.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnCuello.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody1.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_head_green));
        } else if (cuello != 0) {
            strImg1="false";
            tvTime1.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnCuello.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody1.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_head_red));
            imgWatch1.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_black_watch));
        }

        if (hubros == 3) {
            strImg2="true";
            tvTime2.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch2.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnHumbros.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody2.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_sholder_green));
        } else if (hubros != 0) {
            strImg2="false";
            tvTime2.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnHumbros.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody2.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_sholder_red));
        }

        if (codos == 3) {
            strImg3="true";
            tvTime3.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch3.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnCodos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody3.setBackground(getActivity().getResources().getDrawable(R.drawable.exerciseelvow_green));
        } else if (codos != 0) {
            strImg3="false";
            tvTime3.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnCodos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody3.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_elvow_red));
        }

        if (cadera == 6) {
            strImg4="true";
            tvTime4.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch4.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnCadera.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody4.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_abdomen_green));
        } else if (cadera != 0) {
            strImg4="false";
            tvTime4.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnCadera.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody4.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_abdomen_red));
        }

        if (munacas == 6) {
            strImg5="true";
            tvTime5.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch5.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnMunecas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody5.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_wrist_green));
            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_green));
        } else if (munacas != 0) {
            strImg5="false";
            tvTime5.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnMunecas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody5.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_wrist_red));
            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_red));
        }

//        if (dedos == 2) {
//            strImg6="true";
//            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
//            imgWatch6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
//            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
//            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_green));
//        } else if (dedos != 0) {
//            strImg6="false";
//            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
//            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
//            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_red));
//        }

        if (rodillas == 4) {
            strImg7="true";
            tvTime7.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));

            imgWatch7.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));

            btnRodillas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody7.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_knee_green));
            imgRodilla.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_calf_green));
        } else if (rodillas != 0) {
            strImg7="false";
            tvTime7.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnRodillas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody7.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_knee_red));
            imgRodilla.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_knee_red));
            imgRodilla.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_calf_red));
        }

        if (tobillas == 3) {
            strImg8="true";
            tvTime8.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch8.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnTobillos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody8.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_ankle_green));

                if(!strImg1.equals("true")){
                    Toast.makeText(getActivity(), "No se realizaron todos los ejercicios.", Toast.LENGTH_LONG).show();
                }
                else if(!strImg2.equals("true")){
                    Toast.makeText(getActivity(), "No se realizaron todos los ejercicios.", Toast.LENGTH_LONG).show();
                }
                else if(!strImg3.equals("true")){
                    Toast.makeText(getActivity(), "No se realizaron todos los ejercicios.", Toast.LENGTH_LONG).show();
                }
                else if(!strImg4.equals("true")){
                    Toast.makeText(getActivity(), "No se realizaron todos los ejercicios.", Toast.LENGTH_LONG).show();
                }
                else if(!strImg5.equals("true")){
                    Toast.makeText(getActivity(), "No se realizaron todos los ejercicios.", Toast.LENGTH_LONG).show();
                }
//                else if(!strImg6.equals("true")){
//                    Toast.makeText(getActivity(), "Por favor haz todo el ejercicio primero", Toast.LENGTH_LONG).show();
//                }
                else if(!strImg7.equals("true")){
                    Toast.makeText(getActivity(), "No se realizaron todos los ejercicios.", Toast.LENGTH_LONG).show();
                }


        } else if (tobillas != 0) {
            strImg8="false";
            tvTime8.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnTobillos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgBody8.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_ankle_red));
        }

    }

    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(getActivity(), OrmLiteDB.class);
        }
        return ormLiteDb;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStop() {
        super.onStop();
        tvTime1.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
        imgWatch1.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
        btnCuello.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
        imgBody1.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_head_green));
    }
}
