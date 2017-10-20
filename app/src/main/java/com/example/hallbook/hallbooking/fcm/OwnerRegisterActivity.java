package com.example.hallbook.hallbooking.fcm;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.example.hallbook.hallbooking.R;
import com.example.hallbook.hallbooking.entity.Response;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ARUN SUTHAR on 24-09-2017.
 */

public class OwnerRegisterActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener, Callback<Response> {

    private Button btnOwnerRegister;
    private Button btnOwnerLogin;
    private EditText inputHallName;
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
    private RadioButton  veg;
    private RadioButton nonveg;
    private RadioGroup radioFoodGroup;
    private RadioButton  yes;
    private RadioButton not;


    private Button btnClick;
    private ImageView preview;
    static final int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;
    public final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 1;
    public String ownercity , ownerstate ,s1,s2,s3,s4 ,ownerfood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register);

        inputHallName = (EditText) findViewById(R.id.hall_name);
        inputOwnerFullName = (EditText) findViewById(R.id.hall_ownername);
        inputOwnerEmail = (EditText) findViewById(R.id.hall_owneremail);
        inputOwnerPassword = (EditText) findViewById(R.id.hall_ownerpassword);
        inputOwnerPassword = (EditText) findViewById(R.id.hall_ownerphoneno);
        inputOwnerPhoneno = (EditText) findViewById(R.id.hall_ownerphoneno);
        inputOwnerPhoneno2 = (EditText) findViewById(R.id.hall_ownerphoneno2);
        inputOwnerAddress = (EditText) findViewById(R.id.hall_owneraddress);
        spinnerOwnerCity = (Spinner) findViewById(R.id.hall_ownerspinnercity);
        spinnerOwnerState = (Spinner) findViewById(R.id.hall_ownerspinnerstate);
        btnOwnerRegister = (Button) findViewById(R.id.btnOwnerRegister);
        btnOwnerLogin= (Button) findViewById(R.id.btnOwnerLogin);
        btnClick = (Button) findViewById(R.id.btnCapturePicture);
        radioAcGroup = (RadioGroup) findViewById(R.id.radioAC);
        radioFoodGroup = (RadioGroup) findViewById(R.id.radio_food);
        inputSeatCapacity = (EditText) findViewById(R.id.seat_capacity);
        inputDinningCapacity = (EditText) findViewById(R.id.dinning_capacity);
        inputParking = (EditText) findViewById(R.id.parking);
        preview = (ImageView) findViewById(R.id.imgPreview);
        yes = (RadioButton) findViewById(R.id.radio_yes);
        not = (RadioButton) findViewById(R.id.radio_no);
        veg = (RadioButton) findViewById(R.id.radio_veg);
        nonveg= (RadioButton) findViewById(R.id.radio_nonveg);


        btnOwnerLogin.setOnClickListener(this);
        btnOwnerRegister.setOnClickListener(this);
        btnClick.setOnClickListener(this);
        spinnerOwnerState.setOnItemSelectedListener(this);

        spinnerOwnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ownercity= spinnerOwnerCity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


     radioAcGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected

                if(checkedId == R.id.radio_yes) {

                   s1= yes.getText().toString();

                    Toast.makeText(OwnerRegisterActivity.this, s1, Toast.LENGTH_SHORT).show();

                   // Toast.makeText(getApplicationContext(), "choice: yes", Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.radio_no) {


                    s1 = not.getText().toString();

                    Toast.makeText(getApplicationContext(),"choice:no" ,Toast.LENGTH_SHORT).show();
                }

                //radioAcGroup.getCheckedRadioButtonId();
            }

        });

        radioFoodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected

                if(checkedId == R.id.radio_veg) {

                    s2= veg.getText().toString();

                    Toast.makeText(getApplicationContext(), "choice: veg", Toast.LENGTH_SHORT).show();

                } else if(checkedId == R.id.radio_nonveg) {


                    s2 = nonveg.getText().toString();

                    Toast.makeText(getApplicationContext(),"choice:nonveg" ,Toast.LENGTH_SHORT).show();
                }
            }

        });







    }

    @Override

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {

        ownerstate= spinnerOwnerState.getSelectedItem().toString();
        Toast.makeText(this, ownerstate, Toast.LENGTH_SHORT).show();


        if(ownerstate.contentEquals("Gujarat")) {

            Toast.makeText(this, ownercity, Toast.LENGTH_SHORT).show();
            List<String> list = new ArrayList<String>();
            list.add("Ahemdabad");
            list.add ("surat");
            list.add("Others");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            spinnerOwnerCity.setAdapter(dataAdapter2);
            Toast.makeText(this, ownercity, Toast.LENGTH_SHORT).show();
        }
        if(ownerstate.contentEquals("Tamilnadu")) {
            List<String> list = new ArrayList<String>();
            list.add("erode");
            list.add("salem");
            list.add("coimbatore");
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter3.notifyDataSetChanged();
            spinnerOwnerCity.setAdapter(dataAdapter3);

                        Toast.makeText(this, ownercity, Toast.LENGTH_SHORT).show();

        }
    }
    @Override

    public void onNothingSelected(AdapterView<?> arg0) {

    }




    private void validatedata() {
        String hallname = inputHallName.getText().toString().trim();
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
        String ac= radioAcGroup.toString().trim();
        String food= radioFoodGroup.toString().trim();


        boolean error = false;

        if (TextUtils.isEmpty(hallname)) {
            error = true;
            inputHallName.setError("Please enter the hall Name");
        }

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

        if (TextUtils.isEmpty(ownerphoneno) ) {
            error = true;
            inputOwnerPhoneno.setError("Please enter the Phone number");

        }

        if(ownerphoneno.length() <9 || ownerphoneno.length() >11 && !Patterns.PHONE.matcher(ownerphoneno).matches() )
        {
            error = true;
            inputOwnerPhoneno.setError("Please enter the valid Phone number");

        }

        if (TextUtils.isEmpty(ownerphoneno2)) {
            error = true;
            inputOwnerPhoneno2.setError("Please enter the Contact number");
        }

        if(ownerphoneno2.length() <9 || ownerphoneno2.length() >11 && !Patterns.PHONE.matcher(ownerphoneno2).matches() )
        {
            error = true;
            inputOwnerPhoneno.setError("Please enter the valid Phone number");

        }

        if (TextUtils.isEmpty(owneraddress)) {
            error = true;
            inputOwnerAddress.setError("Please enter the address");
        }

        if (TextUtils.isEmpty(ownercity)) {
            error = true;
            Utils.showToast(this, "Please select");
        }

        if (TextUtils.isEmpty(ownerstate)) {
            error = true;
            Utils.showToast(this, "Please select");
        }

        if (TextUtils.isEmpty(ac)) {
            error = true;
            Utils.showToast(this, "Please select");
        }

        if (TextUtils.isEmpty(food)) {
            error = true;
            Utils.showToast(this, "Please select");
        }

        if (TextUtils.isEmpty(seatcapacity)) {
            error = true;
            inputSeatCapacity.setError("Pleases enter the seat capacity");
        }

        if (TextUtils.isEmpty(dinningcapacity)) {
            error = true;
            inputDinningCapacity.setError("Pleases enter the dinning capacity");
        }

        if (TextUtils.isEmpty(parking)) {
            error = true;
            inputParking.setError("Pleases enter the parking details");
        }



        File imageFile = null;
        if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
            imageFile = new File(mCurrentPhotoPath);
            if (imageFile == null || !imageFile.exists() || !(imageFile.length() > 0)) {
                error = true;
                Utils.showToast(this, "Please capture the image");
            }
        } else {
            error = true;
            Utils.showToast(this, "Please capture the image");
        }

        if (error) {
            Utils.showToast(this, "Please fill the required details");
            btnOwnerRegister.setEnabled(true);
            return;
        }
        GetOwnerInterface service = HallBookingApplication.getInstance().getRetrofit().
                create(GetOwnerInterface.class);


        // create part for file (photo)
        MultipartBody.Part body = RetrofitUtils.prepareFilePart("imageurl", Uri.fromFile(imageFile),
                this);


        // create a map of data to pass along

        RequestBody hallnameBody = RetrofitUtils.createPartFromString(hallname);
        RequestBody ownernameBody = RetrofitUtils.createPartFromString(ownername);
        RequestBody owneremailBody = RetrofitUtils.createPartFromString(owneremail);
        RequestBody ownerpasswordBody = RetrofitUtils.createPartFromString(ownerpassword);
        RequestBody ownerphonenoBody = RetrofitUtils.createPartFromString(ownerphoneno);
        RequestBody ownerphoneno2Body = RetrofitUtils.createPartFromString(ownerphoneno2);
        RequestBody owneraddressBody = RetrofitUtils.createPartFromString(owneraddress);
        RequestBody ownercityBody = RetrofitUtils.createPartFromString(ownercity);
        RequestBody ownerstateBody = RetrofitUtils.createPartFromString(ownerstate);
        RequestBody seatcapacityBody = RetrofitUtils.createPartFromString(seatcapacity);
        RequestBody dinningcapacityBody = RetrofitUtils.createPartFromString(dinningcapacity);
        RequestBody parkingBody = RetrofitUtils.createPartFromString(parking);
        RequestBody acBody = RetrofitUtils.createPartFromString(ac);
        RequestBody foodBody = RetrofitUtils.createPartFromString(food);





        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("hallname", hallnameBody);
        map.put("ownername", ownernameBody);
        map.put("owneremail",owneremailBody);
        map.put("ownerpassword",ownerpasswordBody);
        map.put("ownerphoneno",ownerphonenoBody);
        map.put("ownerphoneno2",ownerphoneno2Body);
        map.put("owneraddress",owneraddressBody);
        map.put("ownercity",ownercityBody);
        map.put("ownerstate",ownerstateBody);

        map.put("seatcapacity",seatcapacityBody);
        map.put("dinningcapacity",dinningcapacityBody);
        map.put("parking",parkingBody);
        map.put("ac",acBody);
        map.put("food",foodBody);


        Call<Response> cn = service.addOwner(map,body);
        cn.enqueue(this);
        btnOwnerRegister.setText("Submitting.....");

    }

        @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOwnerRegister:

                validatedata();
                break;
            case R.id.btnOwnerLogin:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.btnCapturePicture:
                if(hasPermissions(this,  new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    dispatchTakePictureIntent();
                } else {
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                        checkMultiplePermissions(REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS,this);
                    }
                }
                break;
    }
    }


    private File createImageFile() throws IOException {
        // Create an image file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    boolean showRationale1 = shouldShowRequestPermissionRationale(
                            Manifest.permission.CAMERA);
                    boolean showRationale2 = shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    );
                    boolean showRationale3 = shouldShowRequestPermissionRationale(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    );
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //check for camera and storage access permissions
    @TargetApi(Build.VERSION_CODES.M)
    private void checkMultiplePermissions(int permissionCode, Context context) {
        String[] PERMISSIONS = {Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, permissionCode);
        } else {
            dispatchTakePictureIntent();
        }
    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("Fail", "Failed");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.hallbook.hallbooking.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                List<ResolveInfo> resInfoList = getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
                    File imageFile = new File(mCurrentPhotoPath);
                    if (imageFile != null && imageFile.exists() && imageFile.length() > 0) {
                        Picasso.with(this).load(imageFile).into(preview);
                        preview.setVisibility(View.VISIBLE);
                        btnClick.setText("Retake");
                    } else {
                        Utils.showToast(this, "Try again");
                    }
                } else {
                    Utils.showToast(this, "Failed");
                }
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled
                Utils.showToast(this, "Try again");
            } else {
                // failed to click
                Utils.showToast(this, "Sorry! Failed to click");
            }
        }
    }


    /*    */


    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (Utils.isActivityAlive(this)) {
            btnOwnerRegister.setText("Success");
            btnOwnerRegister.setEnabled(true);
            if (response.isSuccessful()) {
                Response responseBody = response.body();
                if (responseBody.getSuccess() == 1) {
                    btnOwnerRegister.setText("Success");
                    /*PreferenceUtils.set(getApplicationContext(), PreferenceUtils.SAVED_USER_NAME,
                            responseBody.getUser().getName());
                    PreferenceUtils.set(getApplicationContext(), PreferenceUtils.SAVED_USER_ID,
                            responseBody.getUser().getId());*/
                  /*  FirebaseMessaging.getInstance().subscribeToTopic("usedbooks_" + responseBody
                            .getUser().getId());   */
                    Utils.showToast(OwnerRegisterActivity.this, "Success!" + responseBody.getMessage());
                  /* Intent listIntent = new Intent(this, HomeActivity.class);
                    listIntent.setAction(HomeActivity.RESTART_ACTION);
                    startActivity(listIntent);*/
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
