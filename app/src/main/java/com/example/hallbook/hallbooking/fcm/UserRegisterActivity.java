package com.example.hallbook.hallbooking.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;

import com.example.hallbook.hallbooking.R;
import com.example.hallbook.hallbooking.entity.Response;
import retrofit2.Callback;
import retrofit2.Call;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener,Callback<Response> {

    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputPhoneno;
    private EditText inputPhoneno2;
    private EditText inputAddress;
    private Spinner spinnerCity;
    private Spinner spinnerState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputPhoneno = (EditText) findViewById(R.id.phoneno);
        inputPhoneno2 = (EditText) findViewById(R.id.phoneno2);
        inputAddress = (EditText) findViewById(R.id.address);
        spinnerCity = (Spinner) findViewById(R.id.spinner);
        spinnerState = (Spinner) findViewById(R.id.spinner2);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        spinnerCity.setOnItemSelectedListener(this);
        spinnerState.setOnItemSelectedListener(this);

    }


    @Override

    public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {

        parent.getItemAtPosition(pos);

        if (pos == 0) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.state_arrays,

                            android.R.layout.simple_spinner_item);

            spinnerCity.setAdapter(adapter);

        } else if (pos == 1) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_Tamilnadu,

                            android.R.layout.simple_spinner_item);

            spinnerCity.setAdapter(adapter);

        } else if (pos == 2) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_Gujarat,

                            android.R.layout.simple_spinner_item);

            spinnerCity.setAdapter(adapter);

        }

    }


    @Override

    public void onNothingSelected(AdapterView<?> arg0) {

    }


    private void validatedata() {
        String name = inputFullName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String phoneno = inputPhoneno.getText().toString().trim();
        String phoneno2 = inputPhoneno2.getText().toString().trim();
        String address = inputAddress.getText().toString().trim();
        String city = spinnerCity.toString().trim();
        String state = spinnerState.toString().trim();

        boolean
                error = false;
        if (TextUtils.isEmpty(name)) {
            error = true;
            inputFullName.setError("Please enter the Full Name");
        }

        if (TextUtils.isEmpty(password)) {
            error = true;
            inputPassword.setError("Pleases enter the password");
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = true;
            inputEmail.setError("Please enter the valid Email Id");
        }

        if (TextUtils.isEmpty(phoneno) || !Patterns.PHONE.matcher(phoneno).matches()) {
            error = true;
            inputPhoneno.setError("Please enter the Phone number");
        }

        if (TextUtils.isEmpty(phoneno2)) {
            error = true;
            inputPhoneno2.setError("Please enter the Contact number");
        }

        if (TextUtils.isEmpty(address)) {
            error = true;
            inputAddress.setError("Please enter the address");
        }

        if (TextUtils.isEmpty(city)) {
            error = true;
            //spinnerCity.setError("Please enter the City");
        }

        if (TextUtils.isEmpty(state)) {
            error = true;
            //spinnerState.setError("Please enter the state");
        }

        if (error) {
            Utils.showToast(this, "Please fill the required details");
            btnRegister.setEnabled(true);
            return;
        }

        GetUserInterface service = HallBookingApplication.getInstance().getRetrofit().
                create(GetUserInterface.class);

        Call<Response> cn = service.addUser(name,email,password,phoneno,phoneno2,address,city,state);
        cn.enqueue(this);
        btnRegister.setText("Submitting.....");

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                validatedata();
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (Utils.isActivityAlive(this)) {
            btnRegister.setText("Success");
            btnRegister.setEnabled(true);
            if (response.isSuccessful()) {
                Response responseBody = response.body();
                if (responseBody.getSuccess() == 1) {
                    btnRegister.setText("Success");
                    PreferenceUtils.set(getApplicationContext(), PreferenceUtils.SAVED_USER_NAME,
                            responseBody.getUser().getName());
                    PreferenceUtils.set(getApplicationContext(), PreferenceUtils.SAVED_USER_ID,
                            responseBody.getUser().getId());
                  /*  FirebaseMessaging.getInstance().subscribeToTopic("usedbooks_" + responseBody
                            .getUser().getId());   */
                    Utils.showToast(UserRegisterActivity.this, "Success!" + responseBody.getMessage());
                   /* Intent listIntent = new Intent(this, HomeActivity.class);
                    listIntent.setAction(HomeActivity.RESTART_ACTION);
                    startActivity(listIntent); */
                    finish();
                } else {
                    btnRegister.setText("Submit");
                    Utils.showToast(UserRegisterActivity.this, "Failed! " + responseBody.getMessage());
                }
            } else {
                btnRegister.setText("Submit");
                Utils.showToast(UserRegisterActivity.this, "Something went wrong");
            }
        }
    }

    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        if (Utils.isActivityAlive(this)) {
            Utils.showToast(this, "Something went wrong while posting data");
            btnRegister.setEnabled(true);
            btnRegister.setText("Submit");
        }
    }
}

