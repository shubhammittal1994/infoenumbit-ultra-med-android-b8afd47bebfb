package com.soccermat.ultramed.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.table.TableUtils;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.models.CalendarModel;
import com.soccermat.ultramed.models.ExerciseNameModel;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;

import java.sql.SQLException;


public class MyService extends Service {
    OrmLiteDB ormLiteDb;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ClearFromRecentService", "Service Started");
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ClearFromRecentService", "Service Destroyed");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("ClearFromRecentService", "END");
        //Code here
        try {
            ormLiteDb=new OrmLiteDB(this);
////            TableUtils.clearTable(ormLiteDb.getConnectionSource(),ExerciseNameModel.class);
            TableUtils.clearTable(ormLiteDb.getConnectionSource(),SubExerciseDoneModel.class);
            TableUtils.clearTable(ormLiteDb.getConnectionSource(),CalendarModel.class);
////            TableUtils.clearTable(ormLiteDb.getConnectionSource(),SubExerciseNameModel.class);
//
//
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(getApplicationContext(), OrmLiteDB.class);
        }
        return ormLiteDb;
    }
}
