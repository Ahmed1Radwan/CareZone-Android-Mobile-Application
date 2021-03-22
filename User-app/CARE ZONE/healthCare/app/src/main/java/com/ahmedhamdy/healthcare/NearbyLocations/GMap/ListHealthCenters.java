package com.ahmedhamdy.healthcare.NearbyLocations.GMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedhamdy.healthcare.Adapter.CustomPlacesAdapter;
import com.ahmedhamdy.healthcare.MainActivity;
import com.ahmedhamdy.healthcare.NearbyLocations.GeometryController;
import com.ahmedhamdy.healthcare.NearbyLocations.NearbyHospitalsDetail;
import com.ahmedhamdy.healthcare.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import mehdi.sakout.fancybuttons.FancyButton;

public class ListHealthCenters extends AppCompatActivity implements LocationListener {

    public static StringBuffer stringBuffer = new StringBuffer();
    ListView centersListView;
    double latitude, longitude;
    FancyButton scanButton, viewMapButton;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_health_centers);

        centersListView = findViewById(R.id.centersListView);
        centersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Selected=> ", position + "");
                listSelection(position);
            }
        });
        viewMapButton = findViewById(R.id.viewMapButton);
        scanButton = findViewById(R.id.scanButton);

        YoYo.with(Techniques.ZoomIn).duration(100).repeat(1).playOn(scanButton);
        YoYo.with(Techniques.ZoomIn).duration(100).repeat(1).playOn(viewMapButton);

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    updateLoc();
                    GeometryController.loading = true;
                    loadLocation(); // nearby locations
                    loadLocation();
                    loadLocation();

                    while (GeometryController.loading)
                        Log.d("Message=>>>", "Waiting");

                    fillList();
                    //System.out.println("Frommmmm main activity-------------------------");
                } catch (IllegalArgumentException e) {
                    Toast.makeText(ListHealthCenters.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    MainActivity.progressDialog.cancel();
                    e.printStackTrace();
                    finish();
                }
            }
        });


    }

    public void mapButtonPressed(View view) {
        YoYo.with(Techniques.FadeIn).duration(100).repeat(1).playOn(viewMapButton);
        Intent intent = new Intent(ListHealthCenters.this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }

    private void listSelection(int i) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(ListHealthCenters.this);
        dialog.setCancelable(true);
        dialog.setTitle(GeometryController.detailArrayList.get(i).getHospitalName());
        dialog.setMessage(GeometryController.detailArrayList.get(i).getAddress());
        dialog.setIcon(R.drawable.marker);
        dialog.show();
    }



    public void scanButtonPressed(View view) {
        YoYo.with(Techniques.FadeIn).duration(100).repeat(1).playOn(scanButton);

        try {
            updateLoc();
            GeometryController.loading = true;
            loadLocation(); // nearby locations
            while (GeometryController.loading)
                Log.d("Message=>>>", "Waiting");

            fillList();
            System.out.println("Frommmmm scan button activity-------------------------");
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    // update user current location
    private void updateLoc() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            throw new IllegalArgumentException("No GPS");
        else if (!isGooglePlayServicesAvailable(this))
            throw new IllegalArgumentException("NO Google Play Services Available");
        else
            getLocation();
    }

    private boolean isGooglePlayServicesAvailable(Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);
        return resultCode == ConnectionResult.SUCCESS;
    }


    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, perm, 1);

        } else {
            // here
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        if (location != null) {
            Log.d("Achieved latitude=>", location.getLatitude() + ", longitude=> " + location.getLongitude());
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//
//
//            return;
        }

        if (location == null) {
            Log.d("GPS Provider", "Enabled");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(this, perm, 2);
            }else{
                // here
                locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }

        if(location == null)
            throw new IllegalArgumentException("Cann't find location");

        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                try {
                    // here
                    locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
                    //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
                }catch (SecurityException e){
                    e.printStackTrace();
                }

            }else {
                Log.d("Message", "Not supported");
            }
        }else if(requestCode == 2){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                try {
                    //here
                    locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
                    //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                }catch (SecurityException e){
                    e.printStackTrace();
                }

            }else {
                Log.d("Message", "Not supported");
            }
        }
    }

    private void loadLocation(){
        try{
            new RetrieveFeedTask().execute();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (stringBuffer.length() == 0)
                        Log.d("Message", "buffer reading");

                    GeometryController.manipulateData(ListHealthCenters.stringBuffer);
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void fillList(){

        ArrayList<String> placeName = new ArrayList<>();
        for(int i = 0; i < GeometryController.detailArrayList.size(); i++){
            placeName.add(GeometryController.detailArrayList.get(i).getHospitalName());
        }
        ArrayList<String> ratingText = new ArrayList<>();
        for (int i = 0; i < GeometryController.detailArrayList.size(); i++) {
            ratingText.add(GeometryController.detailArrayList.get(i).getRating());
        }
        ArrayList<String> openNow = new ArrayList<>();
        for (int i = 0; i < GeometryController.detailArrayList.size(); i++) {
            openNow.add(GeometryController.detailArrayList.get(i).getOpeningHours());
        }


        CustomPlacesAdapter customPlacesAdapter = new CustomPlacesAdapter(this,placeName,ratingText,openNow);
        centersListView.setAdapter(customPlacesAdapter);

        MainActivity.progressDialog.cancel();




    }




    class RetrieveFeedTask extends AsyncTask<StringBuffer, StringBuffer, StringBuffer>{

        @Override
        protected StringBuffer doInBackground(StringBuffer... stringBuffers) {

            try {
                StringBuilder stringBuilder = new StringBuilder()
                        .append("https://maps.googleapis.com/maps/api/place/search/json?rankby=distance&keyword=hospital&location=")
                        .append(latitude)
                        .append(",")
                        .append(longitude)
                        .append("&key=AIzaSyC6-gwhsbRMAbtSNhR56y2EBV9S16bZhHE&sensor=false&libraries=places");
                System.out.println(stringBuilder.toString());
                // search for url location
                URL url = new URL(stringBuilder.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();

                String n = "";
                while((n=bufferedReader.readLine())!=null){
                    buffer.append(n);
                }
               // Log.d("loaded with size of = > ", "Size is " + buffer.length());
                ListHealthCenters.stringBuffer = buffer;
                return buffer;
            } catch (Exception e) {
                return null;
            }

        }
    }


}