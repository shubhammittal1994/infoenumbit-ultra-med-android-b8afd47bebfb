package com.soccermat.ultramed.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.soccermat.ultramed.R;

public class PreferenceManager {


    Context context;

    public PreferenceManager(Context context)
    {
        this.context=context;
    }

    public SharedPreferences getPreferences() {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }



    public void setStringValues(String key , String DataToSave ) {
        SharedPreferences.Editor editor = getPreferences().edit();
        Log.e("DatatoDave",DataToSave);
        editor.putString(key, DataToSave);
        editor.apply();

    }

    public String getStringValues(String key) {
        return getPreferences().getString(key, null);
    }
    public void setBooleanValues(String key , boolean DataToSave ) {
        SharedPreferences.Editor editor = getPreferences().edit();
      //  Log.e("DatatoDave",DataToSave);
        editor.putBoolean(key, DataToSave);
        editor.apply();

    }

   public boolean getBooleanValues(String key) {
        return getPreferences().getBoolean(key, false);
    }

    public void clearPrefrence(){
        SharedPreferences preferences = getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


}
