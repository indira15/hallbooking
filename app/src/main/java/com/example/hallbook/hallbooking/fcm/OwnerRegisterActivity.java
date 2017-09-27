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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hallbook.hallbooking.R;
import com.example.hallbook.hallbooking.entity.Response;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ARUN SUTHAR on 24-09-2017.
 */

public class OwnerRegisterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, Callback<Response> {

    private Button btnOwnerRegister;
    private Button btnOwnerLinkToLogin;
    private EditText inputOwnerFullName;
    private EditText inputOwnerEmail;
    private EditText inputOwnerPassword;
    private EditText inputOwnerPhoneno;
    private EditText inputOwnerPhoneno2;
    private EditText inputOwnerAddress;
    private Spinner spinnerOwnerCity;
    private Spinner spinnerOwnerState;
    private EditText inputSeatCapacity;
    private EditText inputDinningCapacity;
    private EditText inputParking;
    private RadioGroup radioAcGroup;
    private RadioButton  radioAcButton;
    private RadioGroup radioFoodGroup;
    private RadioButton  radioFoodButton;
    private Button btnClick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register);

        inputOwnerFullName = (EditText) findViewById(R.id.hall_ownername);
        inputOwnerEmail = (EditText) findViewById(R.id.hall_owneremail);
        inputOwnerPassword = (EditText) findViewById(R.id.hall_ownerpassword);
        inputOwnerPassword = (EditText) findViewById(R.id.hall_ownerphoneno);
        inputOwnerPhoneno = (EditText) findViewById(R.id.hall_ownerphoneno);
        inputOwnerPhoneno2 = (EditText) findViewById(R.id.hall_ownerphoneno2);
        inputOwnerAddress = (EditText) findViewById(R.id.hall_owneraddress);
        spinnerOwnerCity = (Spinner) findViewById(R.id.hall_ownerspinner);
        spinnerOwnerState = (Spinner) findViewById(R.id.hall_ownerspinner2);
        btnOwnerRegister = (Button) findViewById(R.id.btnOwner_Hall_Register);
        btnOwnerLinkToLogin= (Button) findViewById(R.id.btnOwner_Hall_Login);
        btnClick = (Button) findViewById(R.id.btnCapturePicture);
        radioAcGroup = (RadioGroup) findViewById(R.id.radioAC);
        radioFoodGroup = (RadioGroup) findViewById(R.id.radio_food);
        inputSeatCapacity = (EditText) findViewById(R.id.seat_capacity);
        inputDinningCapacity = (EditText) findViewById(R.id.dinning_capacity);
        inputParking = (EditText) findViewById(R.id.parking);
        btnOwnerLinkToLogin.setOnClickListener(this);
        btnOwnerRegister.setOnClickListener(this);
        btnClick.setOnClickListener(this);
        spinnerOwnerCity.setOnItemSelectedListener(this);
        spinnerOwnerState.setOnItemSelectedListener(this);

    }




    private void validatedata() {
        String ownername = inputOwnerFullName.getText().toString().trim();
        String owneremail = inputOwnerEmail.getText().toString().trim();
        String ownerpassword = inputOwnerPassword.getText().toString().trim();
        String ownerphoneno = inputOwnerPhoneno.getText().toString().trim();
        String ownerphoneno2 = inputOwnerPhoneno2.getText().toString().trim();
        String owneraddress = inputOwnerAddress.getText().toString().trim();
        String ownercity = spinnerOwnerCity.toString().trim();
        String ownerstate = spinnerOwnerState.toString().trim();
        String seatcapacity = inputSeatCapacity.getText().toString().trim();
        String dinningcapacity = inputDinningCapacity.getText().toString().trim();
        String parking = inputParking.getText().toString().trim();

        boolean
                error = false;
        if (TextUtils.isEmpty(ownername)) {
            error = true;
            inputOwnerFullName.setError("Please enter the Full Name");
        }

        if (TextUtils.isEmpty(ownerpassword)) {
            error = true;
            inputOwnerPassword.setError("Pleases enter the password");
        }

        if (TextUtils.isEmpty(owneremail) || !Patterns.EMAIL_ADDRESS.matcher(owneremail).matches()) {
            error = true;
            inputOwnerEmail.setError("Please enter the valid Email Id");
        }

        if (TextUtils.isEmpty(ownerphoneno) || !Patterns.PHONE.matcher(ownerphoneno).matches()) {
            error = true;
            inputOwnerPhoneno.setError("Please enter the Phone number");
        }

        if (TextUtils.isEmpty(ownerphoneno2)) {
            error = true;
            inputOwnerPhoneno2.setError("Please enter the Contact number");
        }

        if (TextUtils.isEmpty(owneraddress)) {
            error = true;
            inputOwnerAddress.setError("Please enter the address");
        }

        if (TextUtils.isEmpty(ownercity)) {
            error = true;
            //spinnerCity.setError("Please enter the City");
        }

        if (TextUtils.isEmpty(ownerstate)) {
            error = true;
            //spinnerState.setError("Please enter the state");
        }

        if (TextUtils.isEmpty(seatcapacity)) {
            error = true;
            inputOwnerPassword.setError("Pleases enter the seat capacity");
        }

        if (TextUtils.isEmpty(dinningcapacity)) {
            error = true;
            inputOwnerPassword.setError("Pleases enter the dinning capacity");
        }

        if (TextUtils.isEmpty(parking)) {
            error = true;
            inputOwnerPassword.setError("Pleases enter the parking details");
        }

        if (error) {
            Utils.showToast(this, "Please fill the required details");
            btnOwnerRegister.setEnabled(true);
            return;
        }
        GetOwnerInterface service = HallBookingApplication.getInstance().getRetrofit().
                create(GetOwnerInterface.class);

        Call<Response> cn = service.addOwner(ownername,owneremail,ownerpassword,ownerphoneno,ownerphoneno2,owneraddress,ownercity,ownerstate);
        cn.enqueue(this);
        btnOwnerRegister.setText("Submitting.....");

    }

    @Override

    public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {

        parent.getItemAtPosition(pos);

        if (pos == 0) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.state_arrays,

                            android.R.layout.simple_spinner_item);

            spinnerOwnerCity.setAdapter(adapter);

        } else if (pos == 1) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_Tamilnadu,

                            android.R.layout.simple_spinner_item);

            spinnerOwnerState.setAdapter(adapter);

        } else if (pos == 2) {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter

                    .createFromResource(this, R.array.city_Gujarat,

                            android.R.layout.simple_spinner_item);

            spinnerOwnerCity.setAdapter(adapter);

        }

    }


    @Override

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_yes:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_no:
                if (checked)
                    // Ninjas rule
                    break;

            case R.id.radio_veg:
                if (checked)
                    // Ninjas rule
                    break;

            case R.id.radio_nonveg:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

        @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOwner_Hall_Register:
                validatedata();
                break;
            case R.id.btnOwner_Hall_Login:
                startActivity(new Intent(this, HomeActivity.class));
    }
    }


    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (Utils.isActivityAlive(this)) {
            btnOwnerRegister.setText("Success");
            btnOwnerRegister.setEnabled(true);
            if (response.isSuccessful()) {
                Response responseBody = response.body();
                if (responseBody.getSuccess() == 1) {
                    btnOwnerRegister.setText("Success");
                    PreferenceUtils.set(getApplicationContext(), PreferenceUtils.SAVED_USER_NAME,
                            responseBody.getUser().getName());
                    PreferenceUtils.set(getApplicationContext(), PreferenceUtils.SAVED_USER_ID,
                            responseBody.getUser().getId());
                  /*  FirebaseMessaging.getInstance().subscribeToTopic("usedbooks_" + responseBody
                            .getUser().getId());   */
                    Utils.showToast(OwnerRegisterActivity.this, "Success!" + responseBody.getMessage());
                   /* Intent listIntent = new Intent(this, HomeActivity.class);
                    listIntent.setAction(HomeActivity.RESTART_ACTION);
                    startActivity(listIntent); */
                    finish();
                } else {
                    btnOwnerRegister.setText("Submit");
                    Utils.showToast(OwnerRegisterActivity.this, "Failed! " + responseBody.getMessage());
                }
            } else {
                btnOwnerRegister.setText("Submit");
                Utils.showToast(OwnerRegisterActivity.this, "Something went wrong");
            }

        }
        }


    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        if (Utils.isActivityAlive(this)) {
            Utils.showToast(this, "Something went wrong while posting data");
            btnOwnerRegister.setEnabled(true);
            btnOwnerRegister.setText("Submit");
        }
    }

}
