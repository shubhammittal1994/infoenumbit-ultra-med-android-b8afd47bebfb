package com.soccermat.ultramed.activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.fragment.AlertasFragment;
import com.soccermat.ultramed.models.AlertsModels;

import java.util.Calendar;

public class AddAlertasActivity extends AppCompatActivity implements View.OnClickListener {

    CardView btnAdd;
    String array[] = new String[]{"Diario", "Lunes a Viernes", "Fin de Semana"};
    AutoCompleteTextView spinnerSecond, spinnerFirst;
    long strMiloSecds ;
    LinearLayout backLL;
   ImageView backIMG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alertas);
        initViews();

    }

    private void initViews() {
        spinnerFirst = findViewById(R.id.spinnerFirst);
        btnAdd = findViewById(R.id.btnAdd);
        spinnerSecond = findViewById(R.id.spinnerSecond);
        backIMG = findViewById(R.id.backIMG);

        clickListener();
    }

    private void clickListener() {
        spinnerFirst.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        spinnerSecond.setOnClickListener(this);
        backIMG.setOnClickListener(this);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);
        spinnerSecond.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spinnerFirst:

                Calendar mcurrentTime = Calendar.getInstance();
                final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddAlertasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String AM_PM = "AM";
                        String hourStr = "";
                        String minutesStr = "";


                        if (selectedHour > 12) {
                            AM_PM = "PM";
                            selectedHour = selectedHour - 12;
                            //   hourStr = selectedHour + "";
                        }

                        if (selectedHour == 12)
                            AM_PM = "PM";

                        if (selectedHour == 0)
                            selectedHour = 12;

                        if (selectedHour <= 9)
                            hourStr = "0" + selectedHour;
                        else
                            hourStr = "" + selectedHour;

                        if (selectedMinute <= 9)
                            minutesStr = "0" + selectedMinute;
                        else
                            minutesStr = "" + selectedMinute;
                        strMiloSecds= mcurrentTime.getTimeInMillis();
                        spinnerFirst.setText(hourStr + ":" + minutesStr + " " + AM_PM);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

                break;

            case R.id.btnAdd:

                AlertsModels alertsModels = new AlertsModels(spinnerFirst.getText().toString(), spinnerSecond.getText().toString(), strMiloSecds);
//                alertsModels.add(alertsModels);
                finish();

                break;

            case R.id.spinnerSecond:

                spinnerSecond.showDropDown();
                break;

                case R.id.backIMG:
finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
