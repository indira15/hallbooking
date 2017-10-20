package com.example.hallbook.hallbooking.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.preference.Preference;
import android.view.View.OnClickListener;

import com.example.hallbook.hallbooking.R;
import com.squareup.picasso.Picasso;
import com.example.hallbook.hallbooking.entity.Owner;
import com.example.hallbook.hallbooking.entity.Response;
import com.example.hallbook.hallbooking.entity.User;

import retrofit2.Call;
import retrofit2.Callback;


public class ContactActivity extends AppCompatActivity implements Callback<Response> {

    TextView hallnameView;

    TextView ownernameView;
    TextView owneraddressView;
    TextView ownercityView;
    TextView ownerstateView;

    TextView acView;
    TextView foodView;

    TextView parkingView;
    TextView dinningcapacityView;
    TextView seatcapacityView;
    ImageView imageurlView;

    TextView ownerphoneno;
    TextView ownerphoneno2;
    Button checkavailability;

    View requesterView;
    TextView requestText;
    TextView requestContact;
    int id;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetails);
        hallnameView= (TextView) findViewById(R.id.hall_name);
        ownernameView= (TextView) findViewById(R.id.hall_ownername);
        parkingView= (TextView) findViewById(R.id.hall_cost);
        owneraddressView = (TextView) findViewById(R.id.hall_owneraddress);
        ownercityView = (TextView) findViewById(R.id.hall_ownerspinnercity);
        ownerstateView = (TextView) findViewById(R.id.hall_ownerspinnerstate);
        acView= (TextView) findViewById(R.id.AC);
        foodView = (TextView) findViewById(R.id.Food_Allowed);
        seatcapacityView = (TextView) findViewById(R.id.seat_capacity);
        dinningcapacityView = (TextView)findViewById(R.id.dinning_capacity);
        ownerphoneno= (TextView) findViewById(R.id.hall_ownerphoneno);
        ownerphoneno2 = (TextView) findViewById(R.id.hall_ownerphoneno2);
        imageurlView = (ImageView) findViewById(R.id.hall_image);
        checkavailability = (Button) findViewById(R.id.checkavailability);

        //requesterView = findViewById(R.id.requesterDetails);
        //requestText = (TextView) findViewById(R.id.requestText);
        //requestContact = (TextView) findViewById(R.id.contactText);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final Owner owner = (Owner) extras.getSerializable("owner");
           // User user = book.getUser();
            hallnameView.setText("Hall Name:"+owner.getHallname());
            ownernameView.setText("Owner Name:"+owner.getOwnername());
            ownerphoneno.setText("Owner Phone number:" + owner.getOwnerphoneno());
            ownerphoneno2.setText("Another Contact number : "+ owner.getOwnerphoneno2());
            owneraddressView.setText("Hall Address :" +owner.getOwneraddress());
            ownercityView.setText("Hall Address:"+owner.getOwnercity());
            ownerstateView.setText("Hall State :"+owner.getOwnerstate());
            parkingView.setText("Hall Cost :"+owner.getparking());
            seatcapacityView.setText(" Hall Seating Capacity :"+owner.getSeatcapacity());
            dinningcapacityView.setText(" Hall Dinning Capacity :"+owner.getDinningcapacity());
            foodView.setText("Food Facility :"+owner.getFood());
            acView.setText("AC Faciltiy:"+ owner.getAc());


           Picasso.with(this).load(owner.getImageurl())
                    .placeholder(R.drawable.ic_launcher)
                    .resize(100, 100)
                    .into(imageurlView);
        }

    }


    @Override
    public void onResponse(final Call<Response> call, final retrofit2.Response<Response> response) {
        if (Utils.isActivityAlive(this)) {
         /*   notifyOwner.setEnabled(true);
            if (response.isSuccessful()) {
                Response responseBody = response.body();
                if (responseBody.getSuccess() == 1) {
                    notifyOwner.setText("Notified");
                } else {
                    notifyOwner.setText("Notify Owner");
                    Utils.showToast(this, "Failed! " + responseBody.getMessage());
                }
            } else {
                notifyOwner.setText("Notify Owner");
                Utils.showToast(this, "Something went wrong");
            }*/
        }
    }

    @Override
    public void onFailure(final Call<Response> call, final Throwable t) {
        if (Utils.isActivityAlive(this)) {
            Utils.showToast(this, "Something went wrong while posting data");
           // notifyOwner.setEnabled(true);
            //notifyOwner.setText("Notify Owner");
        }
    }
/*
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);

    }
*/
   // @Override
    /*public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                startActivity(new Intent(this, OwnersListActivity.class));
                PreferenceUtils.clear(this);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }*/
}

