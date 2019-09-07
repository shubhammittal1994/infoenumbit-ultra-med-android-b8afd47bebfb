package com.soccermat.ultramed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonParser;
import com.soccermat.ultramed.R;
import com.soccermat.ultramed.connection.RetrofitClient;
import com.soccermat.ultramed.helper.StaticSharedpreference;
import com.soccermat.ultramed.models.RegisterResponse;
import com.soccermat.ultramed.utils.DialogueUtils;
import com.soccermat.ultramed.utils.KeyBoardUtils;
import com.soccermat.ultramed.utils.PhimpmeProgressBarHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener, DialogueUtils.AlertDialogListener {
    CardView btnSubmit;
    TextView tvAviso;
    EditText edtName, edtSurName, edtEmail, edtPhone, edtParents, edtCity, edtPostal, edtPassword, edtConfirmpassword;

    String deviceToken;
    PhimpmeProgressBarHandler phimpmeProgressBarHandler;
    DialogueUtils alertDialogHelper;
    LinearLayout linearLayoutInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        phimpmeProgressBarHandler = new PhimpmeProgressBarHandler(this);

        try {
            // instance of the helper class

            alertDialogHelper = new DialogueUtils(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        deviceToken = FirebaseInstanceId.getInstance().getToken();
        initViews();
    }

    private void initViews() {

        btnSubmit = findViewById(R.id.btnSubmit);
        tvAviso = findViewById(R.id.tvAviso);
        linearLayoutInfo = findViewById(R.id.lnrInfo);

        edtName = findViewById(R.id.edtName);
        edtSurName = findViewById(R.id.edtSurName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmpassword = findViewById(R.id.edtConfirmpassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtParents = findViewById(R.id.edtParents);
        edtCity = findViewById(R.id.edtCity);
        edtPostal = findViewById(R.id.edtPostal);
        /*edtName.setText("kdkad");
        edtSurName.setText("kdkad");
        edtEmail.setText("kdkassd@mail.com");
        edtPassword.setText("kdkad12345");
        edtConfirmpassword.setText("kdkad12345");
        edtPhone.setText("5561234s5");
        edtParents.setText("fsdfs");
        edtCity.setText("fdsfsdf");
        edtPostal.setText("589635");*/

        clickListener();
        setData();
    }

    private void clickListener() {
        btnSubmit.setOnClickListener(this);
    }

    private void setData() {

        String mystring = new String("Aviso de Privancidad");
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        tvAviso.setText(content);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (isValidate()) {

                    registerUser();

                }
                sendEmail();

                break;
        }
    }

    private boolean isValidate() {

        if (edtName.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Nombre", Toast.LENGTH_SHORT).show();
        } else if (edtSurName.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Apellido", Toast.LENGTH_SHORT).show();
        } else if (edtEmail.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(edtEmail.getText().toString())) {
            Toast.makeText(this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
        } else if (edtPassword.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
        } else if (edtPassword.getText().length() < 8) {
            Toast.makeText(this, "Password should be at least 8 characters", Toast.LENGTH_SHORT).show();
        } else if (edtConfirmpassword.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please confirm Password", Toast.LENGTH_SHORT).show();
        } else if (!edtPassword.getText().toString().equals(edtConfirmpassword.getText().toString())) {
            Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
        } else if (edtPhone.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Teléfono", Toast.LENGTH_SHORT).show();
        } else if (edtParents.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter país", Toast.LENGTH_SHORT).show();
        } else if (edtCity.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Ciudad", Toast.LENGTH_SHORT).show();
        } else if (edtPostal.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter Código Postal", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }


    void registerUser() {

        KeyBoardUtils.hideKeyboard(this);
        phimpmeProgressBarHandler.show();
        RetrofitClient.getClient()
                .registerUser(edtName.getText().toString(), edtSurName.getText().toString(), edtParents.getText().toString(), edtCity.getText().toString(), edtEmail.getText().toString(),
                        edtPassword.getText().toString(), "0", deviceToken, edtPostal.getText().toString())
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        Log.v("->>>", response.toString());
                        phimpmeProgressBarHandler.hide();
                        try {

                            if (response.body().getStatus_code() == "200") {
                                //Print Activation message in dialog and once user click on the ok you will get event in on positive button in bottom of this class
                                alertDialogHelper.showAlertDialog("Draft", "Discard draft ?", "Discard", "", "", 1, true);
                                startActivity(new Intent(InformationActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                if (response.body().getErrors().length > 0) {
                                    String[] errorResponse = response.body().getErrors();
                                    String aa = errorResponse[0];
                                    PhimpmeProgressBarHandler.showSnackBar(linearLayoutInfo, aa, 5000);
                                } else {

                                    PhimpmeProgressBarHandler.showSnackBar(linearLayoutInfo, response.body().getMessage(), 5000);
                                    // Toast.makeText(InformationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        phimpmeProgressBarHandler.hide();
                        PhimpmeProgressBarHandler.showSnackBar(linearLayoutInfo, t.getMessage(), 5000);
                        // Toast.makeText(InformationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });

    }


    public boolean isValidEmail(String address) {

        if (address != null || !address.equals("")) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(address).matches();
        } else {
            return false;
        }
    }

    private void sendEmail() {
        String to = "";
        to = "edgar@ultramed.com.mx";

        String from = "";
        //from = "postmaster@sandbox38f54f88940347c8a239c1ca1861e68f.mailgun.org";
        from = "edgar@ultramed.com.mx";

        String subject = "UltraMed Information - " + edtName.getText().toString() + " " + edtSurName.getText().toString();
        String message1 = "<br><strong><font size=\"5\">User Information</font></strong><br>";
        message1 += "<strong><font size=\"3\">Name </font></strong>";
        message1 += "<strong><font size=\"3\">Akram</font></strong><br>";

        String message = "<br> Nombre: " + edtName.getText().toString() + "<br> Apellido: " + edtSurName.getText().toString()
                + "<br> Email: " + edtEmail.getText().toString() + "<br> contraseña: " + edtPassword.getText().toString() + "<br> Confirmar contraseña: " + edtConfirmpassword.getText().toString() + "<br> Teléfono: " + edtPhone.getText().toString() +
                "<br> país: " + edtParents.getText().toString() + "<br> Ciudad: " + edtCity.getText().toString()
                + "<br> Código Postal: " + edtPostal.getText().toString();

        RetrofitClient.getInstance()
                .getApi()
                .sendEmail(from, to, subject, message)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Log.v("akram", "response " + response);
                        if (response.code() == HTTP_OK) {
                            try {
                                JSONObject obj = new JSONObject(response.body().string());
                                // Toast.makeText(InformationActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                                StaticSharedpreference.saveInfo("page", "notas", InformationActivity.this);
                                startActivity(new Intent(InformationActivity.this, MedicalGradeActivity.class));

                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(InformationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });

    }


    @Override
    public void onPositiveClick(int from) {
        //as you show only one button you wil get the click in this method

        //either finish this or move to the login screen if come from login and login is not finished call finish otherwise use intent
        finish();

        //Toast.makeText(InformationActivity.this, "positive", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNegativeClick(int from) {
        //  Toast.makeText(InformationActivity.this, "negtive", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNeutralClick(int from) {
        //Toast.makeText(InformationActivity.this, "neutral", Toast.LENGTH_LONG).show();

    }
}
