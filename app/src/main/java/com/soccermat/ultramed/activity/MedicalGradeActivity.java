package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.helper.StaticSharedpreference;

public class MedicalGradeActivity extends AppCompatActivity implements View.OnClickListener {
    CardView btnAccept;
    CheckBox checkbox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_grade);

        initViews();
    }

    private void initViews() {
        btnAccept = findViewById(R.id.btnAccept);
        checkbox = findViewById(R.id.checkbox);

        clickListener();
    }

    private void clickListener() {
        btnAccept.setOnClickListener(this);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btnAccept.setClickable(true);
                    btnAccept.setCardBackgroundColor(getResources().getColor(R.color.active));
                } else {
                    btnAccept.setClickable(false);
                    btnAccept.setCardBackgroundColor(getResources().getColor(R.color.accept_button));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAccept:
                if (checkbox.isChecked()) {
                    StaticSharedpreference.saveInfo("page", "home", MedicalGradeActivity.this);
                    startActivity(new Intent(this, DescriptionActivity.class));
                } else {
                    Toast.makeText(this, "por favor, verifique", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
