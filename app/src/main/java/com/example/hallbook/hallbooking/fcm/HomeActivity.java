package com.example.hallbook.hallbooking.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hallbook.hallbooking.R;

/**
 * Created by ARUN SUTHAR on 25-09-2017.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button OwnerRegister;
    private Button OwnerLogin;
    private Button UserRegister;
    private Button UserLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        OwnerRegister = (Button) findViewById(R.id.btnOwner_Hall_Register);
        OwnerLogin = (Button) findViewById(R.id.btnOwner_Hall_Login);
        UserRegister = (Button) findViewById(R.id.btnUser_Register);
        UserLogin = (Button) findViewById(R.id.btnUserl_Login);
        OwnerRegister.setOnClickListener(this);
        OwnerLogin.setOnClickListener(this);
        UserRegister.setOnClickListener(this);
        UserLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
            switch (view.getId()) {


                case R.id.btnOwner_Hall_Register:
                    startActivity(new Intent(this, OwnerRegisterActivity.class));
                    break;

                case R.id.btnUser_Register:
                    startActivity(new Intent(this,UserRegisterActivity.class));
                    break;
                case R.id.btnOwner_Hall_Login:
                    startActivity(new Intent(this, OwnerLoginActivty.class));
                    break;

                case R.id.btnUserl_Login:
                    startActivity(new Intent(this,UserLoginActivity.class));
                    break;


            }
    }
}




