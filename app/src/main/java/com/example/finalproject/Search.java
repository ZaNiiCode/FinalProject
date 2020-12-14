package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {


    // initializing all variables
    private TextView editText;
    private TextView editTextTextPersonName;
    private Button search;
    private Button locate;
    private EditText busy;
    private EditText locy;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int REQUEST_CODE_PERMISSION = 2;

    // GPSTracker class
    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // giving variable value settings
        editText = findViewById(R.id.busyText);
        editTextTextPersonName = findViewById(R.id.locyText);
        search = findViewById(R.id.search);
        locate = findViewById(R.id.locate);
        busy = findViewById(R.id.busyText);
        locy = findViewById(R.id.locyText);


        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }



//locate button, upon user click the gps will display relevant businesses around users actual location area
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new GPSTracker(Search.this);
                Intent intent = new Intent(getApplicationContext(), Results.class);
                if(gps.canGetLocation()){
                    String actual_lat= "";
                    String actual_long= "";
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    actual_lat = String.valueOf(latitude);
                    actual_long = String.valueOf(longitude);

                    intent.putExtra("lat", actual_lat);
                    intent.putExtra("long", actual_long);
                    startActivity(intent);



                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }


        });

        //search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(locy.getText())) {
                    locy.setHint("Enter Location");

//sends search request with gps data to locate searched for business around user specified area
                } else {
                    Intent intent = new Intent(getApplicationContext(), Results.class);
                    String data1 = busy.getText().toString();
                    String data2 = locy.getText().toString();
                    String actualData1 = String.valueOf(data1);
                    String actualData2 = String.valueOf(data2);
                    intent.putExtra("actual_data1", actualData1);
                    intent.putExtra("actual_data2", actualData2);
                    startActivity(intent);
                }


            }
        });























//bottom nav menu
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.action_search:
                        Intent search = new Intent(getApplicationContext(), Search.class);
                        startActivity(search);
                        break;
                    case R.id.action_results:
                        Intent results = new Intent(getApplicationContext(), Results.class);
                        startActivity(results);
                        break;
                    case R.id.action_favorite:
                        Intent fav = new Intent(getApplicationContext(), Favorites.class);
                        startActivity(fav);
                        break;
                }
                return true;
            }
        });

    }
}