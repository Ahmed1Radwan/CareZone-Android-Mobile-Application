package com.ahmedhamdy.healthcare.Medical;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.ahmedhamdy.healthcare.Adapter.CustomMedicalProblemsAdapter;
import com.ahmedhamdy.healthcare.MainActivity;
import com.ahmedhamdy.healthcare.MedicalVirusInfo;
import com.ahmedhamdy.healthcare.R;
import com.ahmedhamdy.healthcare.UserReminders;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.property.ObjectProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicalProblems extends AppCompatActivity {

    List<MedicalVirusInfo> medicalVirusInfo;
    // arrays of meddicalProblemsImages and text
    ArrayList<Integer> problemsImages;
    ArrayList<String> problemText;

    ListView medicalListView;

    MedicinesController medicinesController = MedicinesController.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_problems);

        medicalListView = findViewById(R.id.listOfProblems);


        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fillProblemsImages();
                fillProblemsText();
                CustomMedicalProblemsAdapter adapter = new CustomMedicalProblemsAdapter(MedicalProblems.this, problemsImages, problemText,null);
                medicalListView.setAdapter(adapter);
            }
        });

        medicalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listItemSelector(position);
            }
        });

        if(MainActivity.progressDialog != null)
            MainActivity.progressDialog.cancel();


        Backendless.Data.of(MedicalVirusInfo.class).find(new AsyncCallback<List<MedicalVirusInfo>>() {

            @Override
            public void handleResponse(List<MedicalVirusInfo> response) {
                fillCloudData(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MedicalProblems.this, "Fail to get Cloud data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void listItemSelector(int i){
        MedicinesController.index = (byte) i;

        Intent intent = new Intent(MedicalProblems.this, RequiredMedicines.class);
        intent.putExtra("virousName",problemText.get(i));
        startActivity(intent);
    }

    void fillProblemsImages(){

        problemsImages = new ArrayList<>();
        problemsImages.add(R.drawable.corona_icon);
        problemsImages.add(R.drawable.fever);
        problemsImages.add(R.drawable.headac);
        problemsImages.add(R.drawable.coldd);
        problemsImages.add(R.drawable.vomitt);
        problemsImages.add(R.drawable.consiptt);
        problemsImages.add(R.drawable.blood);
        problemsImages.add(R.drawable.immue);
        problemsImages.add(R.drawable.daily);

    }
    void fillProblemsText(){
        problemText = new ArrayList<>();
        String[] problems = getResources().getStringArray(R.array.medicalProblems);
        for(int i = 0; i < problems.length; i++)
            problemText.add(problems[i]);

    }


    void fillCloudData(List<MedicalVirusInfo> response){
        if(response!=null){
            List<String> cloudVirousImages = new ArrayList<>();
//            HashSet<String> virousNames = new HashSet<>();
//            HashSet<String> virousImageUrl = new HashSet<>();

            HashMap<String,String> virous = new HashMap<>();

            HashMap<String,List<String>> medicineNames = new HashMap<>();
            HashMap<String,List<String>> medicineImageUrl = new HashMap<>();
            HashMap<String,List<String>> medicineDescriptions = new HashMap<>();
            HashMap<String,List<String>> webLink = new HashMap<>();

            for(int i = 0;i < response.size(); i++){

                String virousName = response.get(i).getVirusName();
//                virousNames.add(virousName);
//                virousImageUrl.add(response.get(i).getVirusImageUrl());

                String x = "";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    x = virous.getOrDefault(virousName,"");
                }
                if(x.equals("")){
                    virous.put(virousName,response.get(i).getVirusImageUrl());
                }

                List<String> temp = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    temp = medicineNames.getOrDefault(virousName, new ArrayList<>());
                }
                temp.add(response.get(i).getMedicineName());
                medicineNames.put(virousName,temp);

                List<String> temp1 = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    temp1 = medicineImageUrl.getOrDefault(virousName,new ArrayList<>());
                }
                temp1.add(response.get(i).getMedicineImageUrl());
                medicineImageUrl.put(virousName,temp1);

                List<String> temp2 = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    temp2 = medicineDescriptions.getOrDefault(virousName, new ArrayList<>());
                }
                temp2.add(response.get(i).getMedicineDescription());
                medicineDescriptions.put(virousName,temp2);

                if(!response.get(i).getMedicineWebsiteUrl().equals("empty")){
                    List<String> temp3 = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        temp3 = webLink.getOrDefault(virousName,new ArrayList<>());
                    }
                    temp3.add(response.get(i).getMedicineWebsiteUrl());
                    webLink.put(virousName,temp3);
                }
            }
//            problemText.addAll(virousNames);
//            cloudVirousImages.addAll(virousImageUrl);

            problemText.addAll(virous.keySet());
            cloudVirousImages.addAll(virous.values());

            medicinesController.cloudMedicinesNames = medicineNames;
            medicinesController.cloudDescriptions = medicineDescriptions;
            medicinesController.cloudImages = medicineImageUrl;
            medicinesController.clouldWebsites = webLink;


            CustomMedicalProblemsAdapter cmAdapter = new CustomMedicalProblemsAdapter(this,problemsImages,problemText,cloudVirousImages);
            medicalListView.setAdapter(cmAdapter);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cm_menu,menu);
        return true;
    }

    public void reminderButton(MenuItem menuItem){
        Intent intent = new Intent(this, UserReminders.class);
        startActivity(intent);
    }
}