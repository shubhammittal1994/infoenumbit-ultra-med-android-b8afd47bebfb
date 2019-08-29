package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;
import com.soccermat.ultramed.R;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.fragment.AlertasFragment;
import com.soccermat.ultramed.fragment.MyExerciseFragment;
import com.soccermat.ultramed.fragment.NotasFragment;
import com.soccermat.ultramed.fragment.ReportsFragment;
import com.soccermat.ultramed.helper.StaticSharedpreference;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;

import java.io.File;
import java.sql.SQLException;

public class HomeActivity extends AppCompatActivity {
    AHBottomNavigationAdapter navigationAdapter;
    AHBottomNavigation bottomNavigation;
    OrmLiteDB ormLiteDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startService(new Intent(getBaseContext(), MyService.class));
        initViews();
    }

    private void initViews() {
        bottomNavigation = findViewById(R.id.bottom_navigation);

        setNavigation();
    }

    private void setNavigation() {
        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FDF000"));
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setAnimation(null);

        goToFragment(new MyExerciseFragment());
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0)
                    goToFragment(new MyExerciseFragment());
                else if (position == 1)
                    goToFragment(new ReportsFragment());
                else if (position == 2)
                    goToFragment(new AlertasFragment());
                else
                    goToFragment(new NotasFragment());
                return true;
            }
        });
    }

    public void goToFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //  fragmentTransaction.setCustomAnimations(R.anim.slide_to_left, R.anim.slide_from_right);
        fragmentTransaction.add(R.id.id_holder, fragment, "Fragment");
        fragmentTransaction.commit();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();




    }


    public OrmLiteDB getHelper() {
        if (ormLiteDb == null) {
            ormLiteDb = OpenHelperManager.getHelper(HomeActivity.this, OrmLiteDB.class);
        }
        return ormLiteDb;
    }
    @Override
    protected void onStop() {
        super.onStop();



    }



}
