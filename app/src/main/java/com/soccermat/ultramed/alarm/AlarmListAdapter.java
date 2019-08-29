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

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soccermat.ultramed.R;

import com.soccermat.ultramed.calendar.utils.Constants;
import com.soccermat.ultramed.fragment.AlertasFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmListAdapter extends BaseAdapter {
    private final String TAG = "AlarmMe";

    private Context mContext;
    private DataSource mDataSource;
    private LayoutInflater mInflater;
    private DateTime mDateTime;
    private int mColorOutdated;
    private int mColorActive;
    private AlarmManager mAlarmManager;
    Fragment mActivity;

    public AlarmListAdapter(Context context, Fragment activity) {
        mContext = context;
        mActivity = activity;
        mDataSource = DataSource.getInstance(context);

        Log.i(TAG, "AlarmListAdapter.create()");

        mInflater = LayoutInflater.from(context);
        mDateTime = new DateTime(context);

        mColorOutdated = mContext.getResources().getColor(R.color.alarm_title_outdated);
        mColorActive = mContext.getResources().getColor(R.color.alarm_title_active);

        mAlarmManager = (AlarmManager) context.getSystemService(mContext.ALARM_SERVICE);

        dataSetChanged();
    }

    public void save() {
        mDataSource.save();
    }

    public void update(Alarm alarm) {
        mDataSource.update(alarm);
        dataSetChanged();
    }

    public void updateAlarms() {
        Log.i(TAG, "AlarmListAdapter.updateAlarms()");
        for (int i = 0; i < mDataSource.size(); i++)
            mDataSource.update(mDataSource.get(i));
        dataSetChanged();
    }

    public void add(Alarm alarm) {
        mDataSource.add(alarm);
        dataSetChanged();
    }

    public void delete(int index) {
        cancelAlarm(mDataSource.get(index));
        mDataSource.remove(index);
        dataSetChanged();
    }

    public void onSettingsUpdated() {
        mDateTime.update();
        dataSetChanged();
    }

    public int getCount() {
        if(mDataSource.size()==0){
            AlertasFragment.tvNoAlerts.setVisibility(View.VISIBLE);
        }else{
            AlertasFragment.tvNoAlerts.setVisibility(View.GONE);
        }

        return mDataSource.size();
    }

    public Alarm getItem(int position) {
        return mDataSource.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Alarm alarm = mDataSource.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_alertas, null);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.tvFrequency);
            holder.cardMain = convertView.findViewById(R.id.cardMain);
            holder.details = convertView.findViewById(R.id.tvTime);
            holder.imgEdit = convertView.findViewById(R.id.imgEdit);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(alarm.getTitle());
        //holder.details.setText(mDateTime.formatDetails(alarm) + (alarm.getEnabled() ? "" : " [disabled]"));
        holder.details.setText(mDateTime.formatTime(alarm) + (alarm.getEnabled() ? "" : " [disabled]"));

        if (alarm.getOutdated())
            holder.title.setTextColor(mColorOutdated);
        else
            holder.title.setTextColor(mColorActive);


        holder.imgEdit.setTag(position);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (Integer) view.getTag();

                Intent intent = new Intent(mContext, EditAlarmActivity.class);

                AlertasFragment.mCurrentAlarm = getItem(pos);
                AlertasFragment.mCurrentAlarm.toIntent(intent);
                mActivity.startActivityForResult(intent, 1);
            }
        });

        holder.imgDelete.setTag(position);
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (Integer) view.getTag();

                deleteDialog(pos);
            }
        });

        return convertView;
    }

    private void deleteDialog(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("¿Desea eliminar esta alerta?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        delete(pos);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }



    private void dataSetChanged() {
        for (int i = 0; i < mDataSource.size(); i++)
            setAlarm(mDataSource.get(i));

        notifyDataSetChanged();
    }

    @SuppressLint("NewApi")
    private void setAlarm(Alarm alarm) {
        PendingIntent sender;
        Intent intent;

        if (alarm.getEnabled() && !alarm.getOutdated()) {
            mAlarmManager = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);

//=======================================
            Constants.strTitle=alarm.getTitle();
            Constants.strTime= String.valueOf(mDateTime.formatTime(alarm) + (alarm.getEnabled() ? "" : " [disabled]"));
            Intent alertIntent=new Intent(mContext,AlarmReceiver.class);
            alertIntent.putExtra("tittle",alarm.getTitle());
            AlarmManager alarmManager=(AlarmManager)
                    mContext.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.getDate(),
                    PendingIntent.getBroadcast(mContext,1, alertIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT));
//            ===============

            Log.i(TAG, "AlarmListAdapter.setAlarm(" + alarm.getId() + ", '" + alarm.getTitle() + "', " + alarm.getDate() + ")");
        }
    }

    private void cancelAlarm(Alarm alarm) {
        PendingIntent sender;
        Intent intent;
        intent = new Intent(mContext, AlarmReceiver.class);
        sender = PendingIntent.getBroadcast(mContext, (int) alarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.cancel(sender);
    }

static class ViewHolder {
    TextView title;
    TextView details;
    CardView cardMain;
    ImageView imgEdit, imgDelete;
}
}

