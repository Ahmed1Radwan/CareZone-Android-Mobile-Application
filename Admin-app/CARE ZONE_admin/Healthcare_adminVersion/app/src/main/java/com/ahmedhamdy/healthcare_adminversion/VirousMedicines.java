package com.ahmedhamdy.healthcare_adminversion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class VirousMedicines extends AppCompatActivity implements IPickResult {

    EditText medicineNameET;
    EditText medicineDescriptionET;
    EditText medicineWebsiteUrlET;
    ImageView medicineImageView;

    boolean image = false;
    Uploaded newPublish = Uploaded.getInstance();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virous_medicines);
        medicineNameET = findViewById(R.id.medicineNameET);
        medicineDescriptionET = findViewById(R.id.medicineDescriptionET);
        medicineWebsiteUrlET = findViewById(R.id.medicineWebsiteUrlET);
        medicineImageView = findViewById(R.id.medicineImage);
        medicineImageView.setImageResource(R.drawable.ic_addphoto);
        image = false;

        YoYo.with(Techniques.Pulse).duration(100).playOn(findViewById(R.id.publishButton));
    }

    public void Publish(View view) {

        YoYo.with(Techniques.FadeIn).duration(100).playOn(findViewById(R.id.publishButton));
        if(image && !medicineNameET.getText().toString().isEmpty() && !medicineDescriptionET.getText().toString().isEmpty()){

            Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.WEBP, 50, "" + medicineNameET.getText().toString() + ".webp", "uploaded", new AsyncCallback<BackendlessFile>() {
                @Override
                public void handleResponse(BackendlessFile response) {
                    MedicalVirusInfo medicalVirusInfo = new MedicalVirusInfo();
                    medicalVirusInfo.setVirusName(newPublish.virousName);
                    medicalVirusInfo.setVirusImageUrl(newPublish.virousImageUrl);
                    medicalVirusInfo.setMedicineName(medicineNameET.getText().toString());
                    medicalVirusInfo.setMedicineDescription(medicineDescriptionET.getText().toString());
                    medicalVirusInfo.setMedicineImageUrl(response.getFileURL());
                    if(!medicineWebsiteUrlET.getText().toString().isEmpty())
                        medicalVirusInfo.setMedicineWebsiteUrl(medicineWebsiteUrlET.getText().toString());
                    else
                        medicalVirusInfo.setMedicineWebsiteUrl("");

                    Backendless.Data.of(MedicalVirusInfo.class).save(medicalVirusInfo, new AsyncCallback<MedicalVirusInfo>() {
                        @Override
                        public void handleResponse(MedicalVirusInfo response) {
                            Toast.makeText(VirousMedicines.this, "Sucess", Toast.LENGTH_SHORT).show();
                            medicineNameET.setText("");
                            medicineDescriptionET.setText("");
                            medicineWebsiteUrlET.setText("");
                            medicineImageView.setImageResource(R.drawable.ic_addphoto);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(VirousMedicines.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(VirousMedicines.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toasty.error(this, "Please fill Medicine total information", Toast.LENGTH_LONG, true).show();
        }
    }


    @Override
    public void onPickResult(PickResult r) {
        if(r.getError() == null){
            medicineImageView.setImageBitmap(r.getBitmap());
            image = true;
            bitmap = r.getBitmap();
        }else{
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Message",r.getError().getMessage()+"");
        }
    }

    public void AddMedicinePhoto(View view) {
        PickImageDialog.build(new PickSetup()).show(this);
    }
}