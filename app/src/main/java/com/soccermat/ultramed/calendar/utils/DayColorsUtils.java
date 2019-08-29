package com.soccermat.ultramed.calendar.utils;

import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;


import com.soccermat.ultramed.R;
import com.soccermat.ultramed.helper.Constants;
import com.soccermat.ultramed.models.CalendarModel;

import java.util.Calendar;

/**
 * This class is used to set a style of calendar cells.
 * <p>
 * Created by Mateusz Kornakiewicz on 24.05.2017.
 */

public class DayColorsUtils {

    /**
     * This is general method which sets a color of the text, font type and a background of a TextView object.
     * It is used to set day cell (numbers) style.
     *
     * @param textView   TextView containing a day number
     * @param textColor  A resource of a color of the day number
     * @param typeface   A type of text style, can be set as NORMAL or BOLD
     * @param background A resource of a background drawable
     */
    public static void setDayColors(TextView textView, int textColor, int typeface, int background) {
        if (textView == null) {
            return;
        }

        textView.setTypeface(null, typeface);
        textView.setTextColor(textColor);
        textView.setBackgroundResource(background);
    }

    /**
     * This method sets a color of the text, font type and a background of a TextView object.
     * It is used to set day cell (numbers) style in the case of selected day (when calendar is in
     * the picker mode). It also colors a background of the selection.
     *
     * @param dayLabel           TextView containing a day number
     * @param calendarProperties A resource of a selection background color
     */
    public static void setSelectedDayColors(TextView dayLabel, CalendarProperties calendarProperties) {
        setDayColors(dayLabel, calendarProperties.getSelectionLabelColor(), Typeface.NORMAL,
                R.drawable.background_color_circle_selector);

        dayLabel.getBackground().setColorFilter(calendarProperties.getSelectionColor(),
                android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    /**
     * This method is used to set a color of texts, font types and backgrounds of TextView objects
     * in a current visible month. Visible day labels from previous and forward months are set using
     * setDayColors() method. It also checks if a day number is a day number of today and set it
     * a different color and bold face type.
     *
     * @param day                A calendar instance representing day date
     * @param today              A calendar instance representing today date
     * @param dayLabel           TextView containing a day number
     * @param calendarProperties A resource of a color used to mark today day
     */

  /*  public static void setCurrentMonthDayColors(Calendar day, Calendar today, TextView dayLabel,
                                                CalendarProperties calendarProperties) {

        //   Log.v("akramrqm", "day " + day.getTime().getMonth());
        //   Log.v("akram", "today " + today);

        dayLabel.setGravity(Gravity.BOTTOM | Gravity.END);


        String dayStr = day.getTime().toString();
        String dayArr[] = dayStr.split(" ");
        int dayInt = Integer.parseInt(dayArr[2]);

        int monCal = day.getTime().getMonth();
        int YearCal = day.getTime().getYear() + 1900;

        String monthStr = String.valueOf(monCal);
        String yearStr = String.valueOf(YearCal);


        String date = dayInt + "-" + monthStr + "-" + yearStr;


        Log.v("makramr", "date day " + date);
        if (today.equals(day)) {
            Log.v("akram", "toadaa " + day.getTime().getTime());
            Log.v("akram", "toadaa " + day.getTime().getYear());
            Log.v("akram", "toadaa " + day.getTime().getDay());
            Log.v("akram", "toadaa " + day.getTime());
            setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                    R.drawable.ic_calendar_yellow_block);
        } else {
            setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                    R.drawable.ic_calendar_yellow_block);
        }


        if (day.getTime().getMonth() == Constants.monthCalendar && YearCal == Constants.yearCalendar)

            setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                    R.drawable.ic_calendar_red_block);

         *//*   for (int i = 0; i < Constants.calendarList.size(); i++) {
                CalendarModel calendarModel = Constants.calendarList.get(i);
                if (calendarModel.date.equalsIgnoreCase(date)) {
                    setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                            R.drawable.ic_calendar_red_block);
                    return;

                }
            }*//*


        //    Log.v("akramrqm", "month inside if");


    }*/

    /*
     *//*
        Log.v("akram", "calendara " + day.getTime());


    }*/
    public static void setCurrentMonthDayColors(Calendar day, Calendar today, TextView dayLabel,
                                                CalendarProperties calendarProperties) {

        Log.v("akram", "day " + day);
        Log.v("akram", "today " + today);


        dayLabel.setGravity(Gravity.BOTTOM | Gravity.END);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, 2018);

      /*  if (today.equals(day)) {
            Log.v("akram", "toadaa " + day.getTime().getTime());
            Log.v("akram", "toadaa " + day.getTime().getYear());
            Log.v("akram", "toadaa " + day.getTime().getDay());
            Log.v("akram", "toadaa " + day.getTime());
            setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                    R.drawable.ic_calendar_yellow_block);
        } else {
            setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                    R.drawable.ic_calendar_yellow_block);
        }*/

        Log.v("akram", "calendara " + day.getTime());

        String calendarStr = calendar.getTime().toString();
        String calendarArr[] = calendarStr.split(" ");
        int calendarInt = Integer.parseInt(calendarArr[2]);

        String dayStr = day.getTime().toString();
        String dayArr[] = dayStr.split(" ");
        int dayInt = Integer.parseInt(dayArr[2]);

        int monCal = calendar.getTime().getMonth();
        int YearCal = calendar.getTime().getYear() + 1900;

        String monthStr = String.valueOf(monCal);
        String yearStr = String.valueOf(YearCal);

        Log.v("akramrq", "imonth " + day.getTime().getMonth());
        Log.v("akramrq", "year " + YearCal);
        Log.v("akramrq", "           =====  ");

        String date = dayInt + "-" + monthStr + "-" + yearStr;
       // if (day.getTime().getMonth() == Constants.monthCalendar && YearCal == Constants.yearCalendar) {

            for (int i = 0; i < Constants.calendarList.size(); i++) {
                CalendarModel calendarModel = Constants.calendarList.get(i);
                if (calendarModel.date.equalsIgnoreCase(date)) {
                    setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                            R.drawable.ic_calendar_red_block);
                    return;

                }
            }
      //  }


        if (day.getTime().getYear() == YearCal)
            if (day.getTime().getMonth() == monCal)
                if (dayInt == calendarInt)
                    setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                            R.drawable.ic_calendar_green_block);

        if (day.getTime().getYear() == YearCal)
            if (day.getTime().getMonth() == monCal)
                if (dayInt == (calendarInt + 1))
                    setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                            R.drawable.ic_calendar_red_block);

        if (day.getTime().getYear() == YearCal)
            if (day.getTime().getMonth() == monCal)
                if (dayInt == (calendarInt + 2))
                    setDayColors(dayLabel, calendarProperties.getDaysLabelsColor(), Typeface.NORMAL,
                            R.drawable.ic_calendar_green_block);

    }
}
