package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.soccermat.ultramed.R;

public class InstructionActivity extends AppCompatActivity implements View.OnClickListener {
    CardView btnContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        findViews();

    }

    private void findViews() {
        btnContinue = findViewById(R.id.btnContinue);

        clickListener();
    }

    private void clickListener() {
        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue:
                startActivity(new Intent(this, HomeActivity.class));
                finishAffinity();

                break;
        }
    }
}
