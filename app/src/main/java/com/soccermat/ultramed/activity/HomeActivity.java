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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;
import com.soccermat.ultramed.R;
import com.soccermat.ultramed.connection.RetrofitClient;
import com.soccermat.ultramed.database.OrmLiteDB;
import com.soccermat.ultramed.fragment.AlertasFragment;
import com.soccermat.ultramed.fragment.MyExerciseFragment;
import com.soccermat.ultramed.fragment.NotasFragment;
import com.soccermat.ultramed.fragment.ReportsFragment;
import com.soccermat.ultramed.helper.Constants;
import com.soccermat.ultramed.helper.StaticSharedpreference;
import com.soccermat.ultramed.models.SubExerciseDoneModel;
import com.soccermat.ultramed.models.SubExerciseNameModel;

import com.soccermat.ultramed.utils.DialogueUtils;
import com.soccermat.ultramed.utils.PhimpmeProgressBarHandler;
import com.soccermat.ultramed.utils.PreferenceManager;

import java.io.File;
import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class HomeActivity extends AppCompatActivity implements DialogueUtils.AlertDialogListener {
    AHBottomNavigationAdapter navigationAdapter;
    AHBottomNavigation bottomNavigation;
    OrmLiteDB ormLiteDb;
    DialogueUtils alertDialogHelper;
    PhimpmeProgressBarHandler phimpmeProgressBarHandler;
    RelativeLayout relativeLayoutHome;
    PreferenceManager pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startService(new Intent(getBaseContext(), MyService.class));
        initViews();

        phimpmeProgressBarHandler = new PhimpmeProgressBarHandler(this);

        pref=new PreferenceManager(this);
        try {
            // instance of the helper class

            alertDialogHelper = new DialogueUtils(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        relativeLayoutHome = findViewById(R.id.RLHome);
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
    public void onBackPressed() {
      //  super.onBackPressed();
        alertDialogHelper.showAlertDialog("Draft", "Discard draft ?", "Logout", "Cancel", "", 1, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("call","call");



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
    private void hitLogoutApi() {

        phimpmeProgressBarHandler.show();
        RetrofitClient.getClient()
                .logoutUser(pref.getStringValues(Constants.AUTH_TOKEN))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        phimpmeProgressBarHandler.hide();

                        if (response.code() == HTTP_OK) {
                            try {

                               // PhimpmeProgressBarHandler.showSnackBar(relativeLayoutHome, HomeActivity.this.getString(R.string.successfully_logout), 000);
                                //clear prefrence here
                                pref.clearPrefrence();
                                Toast.makeText(getApplicationContext(), "token::"+pref.getStringValues(Constants.AUTH_TOKEN), Toast.LENGTH_SHORT).show();

                                pref.setBooleanValues(Constants.IS_LOGGED_IN,false);
                                pref.setStringValues(Constants.AUTH_TOKEN,null);
                                finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            //PhimpmeProgressBarHandler.showSnackBar(relativeLayoutHome, response.body().getMessage(), 5000);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        phimpmeProgressBarHandler.hide();
                        PhimpmeProgressBarHandler.showSnackBar(relativeLayoutHome, t.getMessage(), 5000);
                        // Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });

    }


    @Override
    public void onPositiveClick(int from) {
        hitLogoutApi();
    }


    @Override
    public void onNegativeClick(int from) {
        finish();
    }

    @Override
    public void onNeutralClick(int from) {

    }
}
