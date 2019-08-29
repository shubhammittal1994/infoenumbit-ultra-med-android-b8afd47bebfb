package com.soccermat.ultramed.calendar.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.calendar.CalendarView;
import com.soccermat.ultramed.calendar.utils.CalendarProperties;
import com.soccermat.ultramed.calendar.utils.DateUtils;
import com.soccermat.ultramed.calendar.utils.DayColorsUtils;
import com.soccermat.ultramed.calendar.utils.ImageUtils;
import com.soccermat.ultramed.calendar.utils.SelectedDay;
import com.soccermat.ultramed.helper.Constants;
import com.soccermat.ultramed.models.CalendarModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class is responsible for loading a one day cell.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

class CalendarDayAdapter extends ArrayAdapter<Date> {
    private CalendarPageAdapter mCalendarPageAdapter;
    private LayoutInflater mLayoutInflater;
    private int mPageMonth;
    private Calendar mToday = DateUtils.getCalendar();

    private CalendarProperties mCalendarProperties;

    CalendarDayAdapter(CalendarPageAdapter calendarPageAdapter, Context context, CalendarProperties calendarProperties,
                       ArrayList<Date> dates, int pageMonth) {
        super(context, calendarProperties.getItemLayoutResource(), dates);
        mCalendarPageAdapter = calendarPageAdapter;
        mCalendarProperties = calendarProperties;
        mPageMonth = pageMonth < 0 ? 11 : pageMonth;
        mLayoutInflater = LayoutInflater.from(context);


    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = mLayoutInflater.inflate(mCalendarProperties.getItemLayoutResource(), parent, false);
        }

        TextView dayLabel = (TextView) view.findViewById(R.id.dayLabel);
        ImageView dayIcon = (ImageView) view.findViewById(R.id.dayIcon);

        Calendar day = new GregorianCalendar();
        day.setTime(getItem(position));
        Log.v("akramqureshi", "CalendarDayAdapter " + day.getTime());

        // Loading an image of the event
        if (dayIcon != null) {
            // loadIcon(dayIcon, day);
        }


   /*     String dayStr = day.getTime().toString();
        String dayArr[] = dayStr.split(" ");
        int dayInt = Integer.parseInt(dayArr[2]);

        int monCal = day.getTime().getMonth();
        int YearCal = day.getTime().getYear() + 1900;

        String monthStr = String.valueOf(monCal);
        String yearStr = String.valueOf(YearCal);


        String date = dayInt + "-" + monthStr + "-" + yearStr;

        for (int i = 0; i < Constants.calendarList.size(); i++) {
            CalendarModel calendarModel = Constants.calendarList.get(i);
            if (calendarModel.date.equalsIgnoreCase(date)) {

                dayLabel.setTextColor(mCalendarProperties.getDaysLabelsColor());
                dayLabel.setBackgroundResource(R.drawable.ic_calendar_red_block);
                Log.v("makramr123", "date " + calendarModel.date);

            }
        }*/
        setLabelColors(dayLabel, day);


        dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));


        //dayLabel.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
        return view;
    }

    private void setLabelColors(TextView dayLabel, Calendar day) {
        // Setting not current month day color
        if (!isCurrentMonthDay(day)) {
            dayLabel.setGravity(Gravity.BOTTOM | Gravity.END);
            DayColorsUtils.setDayColors(dayLabel, mCalendarProperties.getAnotherMonthsDaysLabelsColor(),
                    Typeface.NORMAL, R.drawable.ic_calendar_gray_block);
            return;
        }

        // Set view for all SelectedDays
        if (isSelectedDay(day)) {
            Log.v("akram", "today day " + day);
            Stream.of(mCalendarPageAdapter.getSelectedDays())
                    .filter(selectedDay -> selectedDay.getCalendar().equals(day))
                    .findFirst().ifPresent(selectedDay -> selectedDay.setView(dayLabel));

            DayColorsUtils.setSelectedDayColors(dayLabel, mCalendarProperties);
            return;
        }

        // Setting disabled days color
        if (!isActiveDay(day)) {
            DayColorsUtils.setDayColors(dayLabel, mCalendarProperties.getDisabledDaysLabelsColor(),
                    Typeface.NORMAL, R.drawable.background_transparent);
            return;
        }
        Log.v("akramrr", "day = " + day.getTime());

        String dayStr = day.getTime().toString();
        String dayArr[] = dayStr.split(" ");
        int dayInt = Integer.parseInt(dayArr[2]);

        int monCal = day.getTime().getMonth() + 1;
        int YearCal = day.getTime().getYear() + 1900;

        String monthStr = String.valueOf(monCal);
        String yearStr = String.valueOf(YearCal);
        String dayCurrent = String.valueOf(dayInt);

        if (dayInt < 10)
            dayCurrent = "0" + dayCurrent;

        if (monCal < 10)
            monthStr = "0" + monthStr;

        String date = dayCurrent + "-" + monthStr + "-" + yearStr;

        dayLabel.setGravity(Gravity.BOTTOM | Gravity.END);
        dayLabel.setTextColor(mCalendarProperties.getDaysLabelsColor());
        dayLabel.setBackgroundResource(R.drawable.ic_calendar_yellow_block);

        for (int i = 0; i < Constants.calendarList.size(); i++) {
            CalendarModel calendarModel = Constants.calendarList.get(i);
            if (calendarModel.date.equalsIgnoreCase(date)) {
                if (calendarModel.status.equalsIgnoreCase("1"))
                    dayLabel.setBackgroundResource(R.drawable.ic_calendar_green_block);
                else
                    dayLabel.setBackgroundResource(R.drawable.ic_calendar_red_block);
                Log.v("makramr123", "date " + calendarModel.date);
            }
        }


        // dayLabel.setTypeface(null, typeface);
        //  dayLabel.setTextColor(mCalendarProperties.getDaysLabelsColor());
        //   dayLabel.setBackgroundResource( R.drawable.ic_calendar_red_block);

       /* for (int i = 0; i < Constants.calendarList.size(); i++) {
            CalendarModel calendarModel = Constants.calendarList.get(i);
            if (calendarModel.date.equalsIgnoreCase(date)) {
                DayColorsUtils.setCurrentMonthDayColors(day, mToday, dayLabel, mCalendarProperties);

                Log.v("makramr123", "date " + calendarModel.date);
            }
        }*/

        // Setting current month day color
        //  DayColorsUtils.setCurrentMonthDayColors(day, mToday, dayLabel, mCalendarProperties);
    }

    private boolean isSelectedDay(Calendar day) {
        return mCalendarProperties.getCalendarType() != CalendarView.CLASSIC && day.get(Calendar.MONTH) == mPageMonth
                && mCalendarPageAdapter.getSelectedDays().contains(new SelectedDay(day));
    }

    private boolean isCurrentMonthDay(Calendar day) {
        return day.get(Calendar.MONTH) == mPageMonth &&
                !((mCalendarProperties.getMinimumDate() != null && day.before(mCalendarProperties.getMinimumDate()))
                        || (mCalendarProperties.getMaximumDate() != null && day.after(mCalendarProperties.getMaximumDate())));
    }

    private boolean isActiveDay(Calendar day) {
        return !mCalendarProperties.getDisabledDays().contains(day);
    }

    private void loadIcon(ImageView dayIcon, Calendar day) {
        if (mCalendarProperties.getEventDays() == null || mCalendarProperties.getCalendarType() != CalendarView.CLASSIC) {
            dayIcon.setVisibility(View.GONE);
            return;
        }

        Stream.of(mCalendarProperties.getEventDays()).filter(eventDate ->
                eventDate.getCalendar().equals(day)).findFirst().executeIfPresent(eventDay -> {

            ImageUtils.loadResource(dayIcon, eventDay.getImageResource());

            // If a day doesn't belong to current month then image is transparent
            if (!isCurrentMonthDay(day) || !isActiveDay(day)) {
                dayIcon.setAlpha(0.12f);
            }

        });
    }
}
