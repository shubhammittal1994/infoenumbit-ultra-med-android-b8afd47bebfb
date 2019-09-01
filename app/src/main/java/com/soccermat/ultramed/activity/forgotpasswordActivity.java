package com.soccermat.ultramed.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.soccermat.ultramed.R;

public class forgotpasswordActivity extends AppCompatActivity {

    EditText edittext_email;
    Button buttonResetPasswrd;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        edittext_email = findViewById(R.id.edittext_email);
        buttonResetPasswrd= findViewById(R.id.buttonResetPasswrd);
        buttonResetPasswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext_email.getText().toString().trim().length() > 0) {
                    if (edittext_email.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
