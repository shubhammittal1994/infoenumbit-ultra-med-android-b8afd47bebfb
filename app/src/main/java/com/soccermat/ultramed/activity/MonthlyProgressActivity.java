package com.soccermat.ultramed.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;


import com.soccermat.ultramed.R;
import com.soccermat.ultramed.calendar.CalendarView;
import com.soccermat.ultramed.calendar.exceptions.OutOfDateRangeException;
import com.soccermat.ultramed.helper.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthlyProgressActivity extends AppCompatActivity implements View.OnClickListener {
    CardView btnDailyReport;
    CalendarView calendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_progress);

        initViews();
    }

    private void initViews() {
        btnDailyReport = findViewById(R.id.btnDailyReport);
        calendarView = findViewById(R.id.calendarView);

        clickListener();

        String dateStr = "01/09/2018";

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c);

        String monthYear[] = formattedDate.split("/");
        Constants.monthCalendar = Integer.parseInt(monthYear[1]);
        Constants.yearCalendar = Integer.parseInt(monthYear[2]);

        Date dateObj = null;

        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dateObj = curFormater.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            // calendarView.setDate(c);
            calendarView.setDate(dateObj);
        } catch (OutOfDateRangeException e) {

            Log.v("akram", "ex " + e);
        }

    }

    private void clickListener() {
        btnDailyReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDailyReport:

                onBackPressed();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("akram", "onDestroy");
        Constants.monthCalendar = 0;
        Constants.yearCalendar = 0;
    }
}
