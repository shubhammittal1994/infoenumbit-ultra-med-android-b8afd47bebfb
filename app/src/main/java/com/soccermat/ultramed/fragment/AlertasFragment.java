package com.soccermat.ultramed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soccermat.ultramed.alarm.Alarm;
import com.soccermat.ultramed.alarm.AlarmListAdapter;
import com.soccermat.ultramed.alarm.EditAlarmActivity;
import com.soccermat.ultramed.R;

import static android.app.Activity.RESULT_OK;

public class AlertasFragment extends Fragment implements View.OnClickListener {

    public static TextView tvNoAlerts;
    View rootView;
    ImageView imgAdd;

    private ListView mAlarmList;
    private AlarmListAdapter mAlarmListAdapter;
    public static Alarm mCurrentAlarm;

    private final int NEW_ALARM_ACTIVITY = 0;
    private final int EDIT_ALARM_ACTIVITY = 1;
    private final int PREFERENCES_ACTIVITY = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_alertas, container, false);
        initViews();

        return rootView;
    }

    private void initViews() {

        tvNoAlerts = rootView.findViewById(R.id.tvNoAlerts);
        imgAdd = rootView.findViewById(R.id.imgAdd);
        mAlarmList = rootView.findViewById(R.id.alarm_list);

        mAlarmListAdapter = new AlarmListAdapter(getContext(), AlertasFragment.this);
        mAlarmList.setAdapter(mAlarmListAdapter);
        registerForContextMenu(mAlarmList);

        mCurrentAlarm = null;


        clickListener();
    }

    private void clickListener() {
        imgAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgAdd:
                Intent intent = new Intent(getContext(), EditAlarmActivity.class);

                mCurrentAlarm = new Alarm(getContext());
                mCurrentAlarm.toIntent(intent);

                startActivityForResult(intent, NEW_ALARM_ACTIVITY);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == NEW_ALARM_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                mCurrentAlarm.fromIntent(data);
                mAlarmListAdapter.add(mCurrentAlarm);
            }
            mCurrentAlarm = null;
        } else if (requestCode == EDIT_ALARM_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                mCurrentAlarm.fromIntent(data);
                mAlarmListAdapter.update(mCurrentAlarm);
            }
            mCurrentAlarm = null;
        } else if (requestCode == PREFERENCES_ACTIVITY) {
            mAlarmListAdapter.onSettingsUpdated();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAlarmListAdapter.updateAlarms();
    }
}
