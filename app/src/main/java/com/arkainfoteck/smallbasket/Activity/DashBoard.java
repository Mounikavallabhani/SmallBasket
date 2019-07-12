package com.arkainfoteck.smallbasket.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arkainfoteck.smallbasket.DataBase.CartDatabse;
import com.arkainfoteck.smallbasket.Fragment.Categories;
import com.arkainfoteck.smallbasket.Fragment.Home;
import com.arkainfoteck.smallbasket.Fragment.Me;
import com.arkainfoteck.smallbasket.Fragment.Offers;
import com.arkainfoteck.smallbasket.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    ImageView cancelratting;
    LinearLayout homelinearlayout;
    RatingBar homeratting;
    FrameLayout fragment_container1,fragment_container;
    private Handler handler;
    private Runnable handlerTask;
    int profile_counts;

    CartDatabse cartDatabse;
    public static EditText search;
    public static Toolbar toolbar1;
    NavigationView navigationView;
    BottomNavigationView navigation;
    SharedPreferences sharedPreferences;
    LinearLayout linearlayout;
    TextView current_location_address;

    // for getting current location
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private LocationAddressResultReceiver addressResultReceiver;
    private Location currentLocation;
    private LocationCallback locationCallback;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_store:

                    fragment=new Home();
                    break;

                case R.id.navigation_delivary:

                    fragment=new Categories();
                    break;

                case R.id.navigation_wallet:

                    fragment=new Offers();
                    break;
                case R.id.navigation_me:

                    fragment=new Me();
                    break;


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };

        @Override
        protected void onCreate (final Bundle savedInstanceState){

                super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_dash_board);
            fragment_container1=findViewById(R.id.fragment_container1);
            fragment_container=findViewById(R.id.fragment_container);

            search=findViewById(R.id.search);
            toolbar1=findViewById(R.id.toolbar1);
            linearlayout=(LinearLayout)findViewById(R.id.linearlayout);

            cartDatabse=new CartDatabse(this);


            // for getting current location
            addressResultReceiver = new LocationAddressResultReceiver(new Handler());
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(DashBoard.this);
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    currentLocation = locationResult.getLocations().get(0);
                    getAddress();
                };
            };
            startLocationUpdates();



          /*  homeratting=findViewById(R.id.homeratting);
            cancelratting=findViewById(R.id.cancelratting);
            homelinearlayout=findViewById(R.id.homelinearlayout);*/
           navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Home()).commit();

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            current_location_address=(TextView)findViewById(R.id.current_location_address);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
         //   StartTimer();

/*
            sharedPreferences = getSharedPreferences("Logindetails", Context.MODE_PRIVATE);
            String uname = sharedPreferences.getString("password", null);


            if(sharedPreferences!=null) {
                if (uname != null || uname == "") {

                } else {
                    Intent it = new Intent(DashBoard.this, LoginActivity.class);
                    startActivity(it);
                }
            }

*/

            linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DashBoard.this,LcationActivity.class);
                    startActivity(intent);
                }
            });
    }

   /* void StartTimer(){
        handler = new Handler();
        handlerTask = new Runnable()
        {
            @Override
            public void run() {
                // do something

                profile_counts = cartDatabse.getProfilesCount();
                count.setText(Integer.toString(profile_counts));
                handler.postDelayed(handlerTask, 1000);
            }
        };
        handlerTask.run();
    }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (navigation.getSelectedItemId() == R.id.navigation_store) {
                super.onBackPressed();

            } else {
                navigation.setSelectedItemId(R.id.navigation_store);
            }
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent=new Intent(getApplicationContext(),OrderHistory.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }
        /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } */
        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "link";
            String shareSub = "Your subject here";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(DashBoard.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DashBoard.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getAddress() {

        if (!Geocoder.isPresent()) {
            Toast.makeText(DashBoard.this,
                    "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(DashBoard.this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                } else {
                    Toast.makeText(DashBoard.this, "Location permission not granted, " +
                                    "restart the app if you want the feature",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
    private class LocationAddressResultReceiver extends ResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultCode == 0) {
                //Last Location can be null for various reasons
                //for example the api is called first time
                //so retry till location is set
                //since intent service runs on background thread, it doesn't block main thread
                Log.d("Address", "Location null retrying");
                getAddress();
            }

            if (resultCode == 1) {
                Toast.makeText(DashBoard.this,
                        "Address not found, " ,
                        Toast.LENGTH_SHORT).show();
            }

            String currentAdd = resultData.getString("address_result");

            showResults(currentAdd);
        }
    }

    private void showResults(String currentAdd){
      //  Toast.makeText(DashBoard.this,""+currentAdd,Toast.LENGTH_SHORT).show(); ;
        current_location_address.setText(""+currentAdd);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
