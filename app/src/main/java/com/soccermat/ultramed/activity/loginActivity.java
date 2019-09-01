package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soccermat.ultramed.R;

public class loginActivity extends AppCompatActivity {

    EditText edittext_email,edittext_password;
    Button btn_login;
    TextView text_forgotpassword,text_register;
    ImageView image_hidepass,image_showpass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edittext_email=findViewById(R.id.edittext_email);
        edittext_password=findViewById(R.id.edittext_password);
        btn_login=findViewById(R.id.btn_login);
        text_forgotpassword=findViewById(R.id.text_forgotpassword);
        text_register=findViewById(R.id.text_register);
        image_hidepass=findViewById(R.id.image_hidepass);
        image_showpass=findViewById(R.id.image_showpass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext_email.getText().toString().trim().length() > 0) {
                    if (edittext_email.getText().toString().trim().matches(emailPattern)) {

                        if(edittext_password.getText().toString().length()>8){
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "password should be at least 8 characters", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                }
            }
        });
        text_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginActivity.this,forgotpasswordActivity.class);
                startActivity(intent);
            }
        });
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });

        image_hidepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_showpass.setVisibility(View.VISIBLE);
                image_hidepass.setVisibility(View.GONE);
                edittext_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
        image_showpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_hidepass.setVisibility(View.VISIBLE);
                image_showpass.setVisibility(View.GONE);
                edittext_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });

    }
}
