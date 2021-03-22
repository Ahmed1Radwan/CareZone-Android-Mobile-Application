package com.ahmedhamdy.healthcare.Medical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedhamdy.healthcare.R;
import com.ahmedhamdy.healthcare.Web.WebsiteView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequiredMedicines extends AppCompatActivity {

    private GestureDetectorCompat gestureDetectorCompat;
    private List<Integer> medicineNames, descriptions, images;
    private TextView medicineName, medicineDescription, medicineIndex;
    private ImageView medicineImage;
    byte index = 0, TOTAL_MEDICINES;
    String virousName = "";
    MedicinesController medicinesController = MedicinesController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_medicines);

        virousName = getIntent().getStringExtra("virousName");
        /*
        if(MedicinesController.index < images.size())
            this.setTitle(getResources().getStringArray(R.array.medicalProblems)[MedicinesController.index]);
        else
            this.setTitle(virousName);
        */
        this.setTitle(virousName);
        //medicinesController.medicinesNames.size()
        if(MedicinesController.index < 9){
            medicineNames = medicinesController.getMedicines();
            descriptions = medicinesController.getDescription();
            images = medicinesController.getImagesId();
            TOTAL_MEDICINES = (byte) medicineNames.size();
        }else{
            TOTAL_MEDICINES = (byte) medicinesController.cloudMedicinesNames.get(virousName).size();
        }

        //setContentView(R.layout.activity_required_medicines);

        gestureDetectorCompat = new GestureDetectorCompat(this, new Gesture());

        medicineName =  findViewById(R.id.medicineName);
        medicineDescription =  findViewById(R.id.medicineDescription);
        medicineImage =  findViewById(R.id.medicineImage);
        medicineIndex = findViewById(R.id.indexView);

        fillData(index);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void buyOnlineButton(View view) {
        MedicinesController.index1 = index;
        Intent intent = new Intent(RequiredMedicines.this, WebsiteView.class);
        intent.putExtra("virusName",virousName);
        startActivity(intent);
    }

    void fillData(byte i){
        // medicinesController.medicinesNames.size()
        if(MedicinesController.index < 9){
            medicineName.setText(medicineNames.get(i));
            medicineDescription.setText(descriptions.get(i));
            medicineImage.setImageResource(images.get(i));
            medicineIndex.setText((i+1) + "/" + TOTAL_MEDICINES);
        }else{
            medicineName.setText(medicinesController.cloudMedicinesNames.get(virousName).get(i));
            medicineDescription.setText(medicinesController.cloudDescriptions.get(virousName).get(i));
            Picasso.get().load(medicinesController.cloudImages.get(virousName).get(i)).into(medicineImage);
            medicineIndex.setText((i+1) + "/" + TOTAL_MEDICINES);
        }
    }

    class Gesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e2.getX() < e1.getX()){
                if(index < TOTAL_MEDICINES-1){
                    index++;
                    fillData(index);
                }
            }else if(e2.getX() > e1.getX()){
                if(index > 0){
                    index--;
                    fillData(index);
                }
            }
            return true;
        }
    }
}