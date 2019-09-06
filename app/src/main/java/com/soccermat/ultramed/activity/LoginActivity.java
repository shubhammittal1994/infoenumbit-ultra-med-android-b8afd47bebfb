package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.soccermat.ultramed.R;
import com.soccermat.ultramed.connection.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edittext_email, edittext_password;
    Button btn_login;
    TextView text_forgotpassword, text_register;
    ImageView image_hidepass, image_showpass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edittext_email = findViewById(R.id.edittext_email);
        edittext_password = findViewById(R.id.edittext_password);
        btn_login = findViewById(R.id.btn_login);
        text_forgotpassword = findViewById(R.id.text_forgotpassword);
        text_register = findViewById(R.id.text_register);
        image_hidepass = findViewById(R.id.image_hidepass);
        image_showpass = findViewById(R.id.image_showpass);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login: {
                loginUser();

            }
            case R.id.text_forgotpassword: {
                forgetPassword();

            }
            case R.id.text_register: {
                register();

            }
            case R.id.image_hidepass: {
                imageHidePass();

            }
            case R.id.image_showpass: {
                imageshowpass();

            }

        }

    }



    boolean validateInfo(){




        if (edittext_email.getText().toString().trim().length() > 0) {
            if (edittext_email.getText().toString().trim().matches(emailPattern)) {

                if (edittext_password.getText().toString().length() > 8) {
                    return true;
                    //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "password should be at least 8 characters", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    void loginUser() {
        if(validateInfo()){
            hitLoginApi();
        }



    }

    private void hitLoginApi() {
        RetrofitClient.getClient()
                .loginUser("")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Log.v("->>>" , response.toString());
                        if (response.code() == HTTP_OK) {
                            try {
                                JSONObject obj = new JSONObject(response.body().string());
                                // Toast.makeText(InformationActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                                //StaticSharedpreference.saveInfo("page", "notas", InformationActivity.this);
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });

    }

    void forgetPassword() {
        Intent intent = new Intent(LoginActivity.this, forgotpasswordActivity.class);
        startActivity(intent);
    }

    void register() {
        Intent intent = new Intent(LoginActivity.this, InformationActivity.class);
        startActivity(intent);
    }

    void imageHidePass() {
        image_showpass.setVisibility(View.VISIBLE);
        image_hidepass.setVisibility(View.GONE);
        edittext_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

    }

    void imageshowpass() {
        image_hidepass.setVisibility(View.VISIBLE);
        image_showpass.setVisibility(View.GONE);
        edittext_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

    }

}
