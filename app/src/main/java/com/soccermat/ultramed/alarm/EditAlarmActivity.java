/**************************************************************************
 *
 * Copyright (C) 2012-2015 Alex Taradov <alex@taradov.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *************************************************************************/

package com.soccermat.ultramed.alarm;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.soccermat.ultramed.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditAlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private Alarm mAlarm;
    private DateTime mDateTime;

    private GregorianCalendar mCalendar;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private AutoCompleteTextView mOccurence, mTimeButton;
    String array[] = new String[]{"Diario", "Lunes a Viernes", "Fin de Semana"};
    CardView btnAdd;
    LinearLayout backLL;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_alertas);

        findViews();
        updateButtons();
    }

    private void findViews() {

        mOccurence = findViewById(R.id.spinnerSecond);
        mTimeButton = findViewById(R.id.spinnerFirst);
        btnAdd = findViewById(R.id.btnAdd);
        backLL = findViewById(R.id.backLL);

        btnAdd.setOnClickListener(this);
        mTimeButton.setOnClickListener(this);
        mOccurence.setOnClickListener(this);
        backLL.setOnClickListener(this);

        initializeAlarm();
        setFrequencyAdapterAndClick();
    }

    private void setFrequencyAdapterAndClick() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);
        mOccurence.setAdapter(adapter);

        mOccurence.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAlarm.setTitle(mOccurence.getText().toString());
                mAlarm.setOccurence(i);
                updateButtons();
            }
        });
    }

    private void initializeAlarm() {
        mAlarm = new Alarm(this);
        mAlarm.fromIntent(getIntent());

        mDateTime = new DateTime(this);
        mAlarm.setEnabled(true);

        mCalendar = new GregorianCalendar();
        mCalendar.setTimeInMillis(mAlarm.getDate());
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);

        mTimeButton.setText(mDateTime.formatTime(mAlarm));
        if (!mAlarm.getTitle().equalsIgnoreCase(""))
            mOccurence.setText(mAlarm.getTitle());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Intent intent = new Intent();

                mAlarm.toIntent(intent);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.spinnerSecond:
                mOccurence.showDropDown();
                break;
 case R.id.backLL:
                onBackPressed();
                break;

            case R.id.spinnerFirst:

                String time = mDateTime.formatTime(mAlarm);

                String splitTimeAmPm[] = time.split(" ");
                String splitTime[] = null;
                final int hour;
                int minute;

                if (splitTimeAmPm[1].equalsIgnoreCase("AM")) {
                    splitTime = splitTimeAmPm[0].split(":");
                    if (splitTime[0].equalsIgnoreCase("12")) {
                        hour = 0;
                    } else {
                        hour = Integer.parseInt(splitTime[0]);
                    }
                    minute = Integer.parseInt(splitTime[1]);

                } else {
                    splitTime = splitTimeAmPm[0].split(":");
                    if (splitTime[0].equalsIgnoreCase("12"))
                        hour = Integer.parseInt(splitTime[0]);
                    else
                        hour = Integer.parseInt(splitTime[0]) + 12;
                    minute = Integer.parseInt(splitTime[1]);
                }

                Log.v("akram", "time " + time);
                Calendar mcurrentTime = Calendar.getInstance();
                // hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                //   minute = mcurrentTime.get(Calendar.MINUTE);

                Log.v("akram", "hours " + hour);
                Log.v("akram", "minute " + minute);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditAlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String AM_PM = "AM";
                        String hourStr = "";
                        String minutesStr = "";
                        mHour = selectedHour;
                        mMinute = selectedMinute;


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

                        mTimeButton.setText(hourStr + ":" + minutesStr + " " + AM_PM);


                        mCalendar = new GregorianCalendar(mYear, mMonth, mDay, mHour, mMinute);
                        mAlarm.setDate(mCalendar.getTimeInMillis());

                        updateButtons();
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED, null);
        finish();
    }


    private void updateButtons() {
      /*  if (Alarm.ONCE == mAlarm.getOccurence()) {
        }*/
        //mDateButton.setText(mDateTime.formatDate(mAlarm));

        if (Alarm.WEEK_DAYS == mAlarm.getOccurence()) {

            final boolean[] days = mDateTime.getDays(mAlarm);

            days[0] = false;
            days[6] = false;
            mDateTime.setDays(mAlarm, days);
            //mDateButton.setText(mDateTime.formatDays(mAlarm));
        } else if (Alarm.WEEK_END == mAlarm.getOccurence()) {
            final boolean[] days = mDateTime.getDays(mAlarm);

            days[1] = false;
            days[2] = false;
            days[3] = false;
            days[4] = false;
            days[5] = false;

            mDateTime.setDays(mAlarm, days);
            //mDateButton.setText(mDateTime.formatDays(mAlarm));
        } else {

            mCalendar = new GregorianCalendar(mYear, mMonth, mDay, mHour, mMinute);
            mAlarm.setDate(mCalendar.getTimeInMillis());

            mAlarm.setTitle(mOccurence.getText().toString());
            final boolean[] days = mDateTime.getDays(mAlarm);
            mDateTime.setDays(mAlarm, days);
        }
        //
    }


}

