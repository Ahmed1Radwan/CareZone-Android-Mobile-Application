package com.ahmedhamdy.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import com.ahmedhamdy.healthcare.Medical.MedicalProblems;
import com.ahmedhamdy.healthcare.Medical.MedicinesController;
import com.ahmedhamdy.healthcare.NearbyLocations.GMap.ListHealthCenters;
import com.backendless.Backendless;



public class MainActivity extends AppCompatActivity {

    public static ProgressDialog progressDialog;
    MedicinesController medicinesController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicinesController = MedicinesController.getInstance();
        medicinesController.initialize();

        Backendless.initApp(this, "7E3CCAF9-D36E-FF61-FF05-DA734FAB6D00", "89C0327A-9300-4E6D-A234-97984C2D1F9E");

    }

    public void medicalProblems(View view) {
        loading("Loading...");
        Intent intent = new Intent(MainActivity.this, MedicalProblems.class);
        startActivity(intent);
    }

    public void hospitalLocations(View view) {
        if(isNetworkAvailable()){
            loading("Scanning Locations...");
            Intent intent = new Intent(MainActivity.this, ListHealthCenters.class);
            startActivity(intent);
        }else
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    void loading(String message){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}