package com.soccermat.ultramed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.activity.MonthlyProgressActivity;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportsFragment extends Fragment implements View.OnClickListener {
    View rootView;
    CardView btnProgressMonthly;
    CardView btnCuello, btnHumbros, btnCodos, btnCadera, btnMunecas, btnDedos, btnRodillas, btnTobillos;
    ImageView imgWatch1, imgWatch2, imgWatch3, imgWatch4, imgWatch5, imgWatch6, imgWatch7, imgWatch8;
    TextView tvTime1, tvTime2, tvTime3, tvTime4, tvTime5, tvTime6, tvTime7, tvTime8;
    ImageView imgBody1, imgBody2, imgBody3, imgBody4, imgBody5, imgBody6, imgBody7, imgBody8;
    OrmLiteDB ormLiteDb;
    ImageView imgStatus1, imgStatus2, imgStatus3, imgStatus4, imgStatus5, imgStatus6, imgStatus7, imgStatus8,imgRodilla;
    String strImg1="",strImg2="",strImg3="",strImg4="",strImg5="",strImg6="",strImg7="",strImg8="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_reports, container, false);
        initViews();

        return rootView;
    }

    private void initViews() {
        btnProgressMonthly = rootView.findViewById(R.id.btnProgressMonthly);

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

        imgBody1 = rootView.findViewById(R.id.imgBody1);
        imgBody2 = rootView.findViewById(R.id.imgBody2);
        imgBody3 = rootView.findViewById(R.id.imgBody3);
        imgBody4 = rootView.findViewById(R.id.imgBody4);
        imgBody5 = rootView.findViewById(R.id.imgBody5);
        imgBody6 = rootView.findViewById(R.id.imgBody6);
        imgBody7 = rootView.findViewById(R.id.imgBody7);
        imgBody8 = rootView.findViewById(R.id.imgBody8);
        imgRodilla = rootView.findViewById(R.id.imgRodilla);

        tvTime1 = rootView.findViewById(R.id.tvTime1);
        tvTime2 = rootView.findViewById(R.id.tvTime2);
        tvTime3 = rootView.findViewById(R.id.tvTime3);
        tvTime4 = rootView.findViewById(R.id.tvTime4);
        tvTime5 = rootView.findViewById(R.id.tvTime5);
        tvTime6 = rootView.findViewById(R.id.tvTime6);
        tvTime7 = rootView.findViewById(R.id.tvTime7);
        tvTime8 = rootView.findViewById(R.id.tvTime8);

        imgStatus1 = rootView.findViewById(R.id.imgStatus1);
        imgStatus2 = rootView.findViewById(R.id.imgStatus2);
        imgStatus3 = rootView.findViewById(R.id.imgStatus3);
        imgStatus4 = rootView.findViewById(R.id.imgStatus4);
        imgStatus5 = rootView.findViewById(R.id.imgStatus5);
        imgStatus6 = rootView.findViewById(R.id.imgStatus6);
        imgStatus7 = rootView.findViewById(R.id.imgStatus7);
        imgStatus8 = rootView.findViewById(R.id.imgStatus8);


        clickListener();
    }

    private void clickListener() {
        btnProgressMonthly.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnProgressMonthly:
                startActivity(new Intent(getActivity(), MonthlyProgressActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();


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
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    cuello++;

            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("4") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("5")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("6")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    hubros++;

            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("7") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("8")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("9")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    codos++;

            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("10") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("11")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("12")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("13")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("14")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("15")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    cadera++;

            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("16") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("17")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("18")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("19")||subExerciseDoneModel.subExerciseId.equalsIgnoreCase("20") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("21")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    munacas++;

            }
//            else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("20") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("21")) {
//                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
//                    dedos++;
//
//            }
            else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("22") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("23")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("24")|| subExerciseDoneModel.subExerciseId.equalsIgnoreCase("25")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    rodillas++;

            } else if (subExerciseDoneModel.subExerciseId.equalsIgnoreCase("26") || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("27")
                    || subExerciseDoneModel.subExerciseId.equalsIgnoreCase("28")) {
                if (subExerciseDoneModel.status.equalsIgnoreCase("1"))
                    tobillas++;

            }
        }

        if (cuello == 3) {
            strImg1="true";
            tvTime1.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch1.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnCuello.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody1.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_head_green));
            imgStatus1.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));

        } else if (cuello != 0) {
            strImg1="false";
            tvTime1.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnCuello.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus1.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody1.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_head_red));
        }

        if (hubros == 3) {
            strImg2="true";
            tvTime2.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch2.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnHumbros.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody2.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_sholder_green));
            imgStatus2.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
        } else if (hubros != 0) {
            strImg2="false";
            tvTime2.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnHumbros.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus2.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody2.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_sholder_red));
        }

        if (codos == 3) {
            strImg3="true";
            tvTime3.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch3.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnCodos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody3.setBackground(getActivity().getResources().getDrawable(R.drawable.exerciseelvow_green));
            imgStatus3.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
        } else if (codos != 0) {
            strImg3="false";
            tvTime3.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnCodos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus3.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody3.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_elvow_red));
        }

        if (cadera == 6) {
            strImg4="true";
            tvTime4.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch4.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnCadera.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody4.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_abdomen_green));
            imgStatus4.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
        } else if (cadera != 0) {
            strImg4="false";
            tvTime4.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnCadera.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus4.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody4.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_abdomen_red));
        }

        if (munacas == 6) {
            strImg5="true";
            tvTime5.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch5.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnMunecas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody5.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_wrist_green));
            imgStatus5.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_green));
            imgStatus6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
        } else if (munacas != 0) {
            strImg5="false";
            tvTime5.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnMunecas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus5.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody5.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_wrist_red));
            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_red));
        }

//        if (dedos == 2) {
//            strImg6="true";
//            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
//            imgWatch6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
//            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
//            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_green));
//            imgStatus6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
//
//        } else if (dedos != 0) {
//            strImg6="false";
//            tvTime6.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
//            btnDedos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
//            imgStatus6.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
//            imgBody6.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_thigh_red));
//        }

        if (rodillas == 4) {
            strImg7="true";
            tvTime7.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch7.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnRodillas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody7.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_knee_green));
            imgRodilla.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_calf_green));
            imgStatus7.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
        } else if (rodillas != 0) {
            strImg7="true";
            tvTime7.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnRodillas.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus7.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody7.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_knee_red));
            imgRodilla.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_calf_red));
        }
//        Por favor haz todo el ejercicio primero
            if (tobillas == 3) {
            strImg8="true";
            tvTime8.setTextColor(getActivity().getResources().getColor(R.color.watch_time_text_color));
            imgWatch8.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_watch_green));
            btnTobillos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_blue_color));
            imgBody8.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_ankle_green));
            imgStatus8.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_green_check));
        } else if (tobillas != 0) {
            strImg8="false";
            tvTime8.setTextColor(getActivity().getResources().getColor(R.color.button_red_color));
            btnTobillos.setCardBackgroundColor(getActivity().getResources().getColor(R.color.button_red_color));
            imgStatus8.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_circle_red_cross));
            imgBody8.setBackground(getActivity().getResources().getDrawable(R.drawable.exercise_ankle_red));
        }

    }

    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(getActivity(), OrmLiteDB.class);
        }
        return ormLiteDb;
    }

}
