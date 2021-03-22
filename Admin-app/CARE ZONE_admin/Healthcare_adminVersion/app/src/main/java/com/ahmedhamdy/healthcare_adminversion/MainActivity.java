package com.ahmedhamdy.healthcare_adminversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.backendless.persistence.DataQueryBuilder;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements IPickResult {

    EditText virusNameET;

    ImageView virousIconIV;
    Uploaded newPublish = Uploaded.getInstance();
    boolean image = false;
    Bitmap bitmap;

    CheckBox checkBox;
    StringBuilder stringBuilder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Backendless.initApp(this,"7E3CCAF9-D36E-FF61-FF05-DA734FAB6D00","89C0327A-9300-4E6D-A234-97984C2D1F9E");

        virusNameET = findViewById(R.id.virusNameET);
        virousIconIV = findViewById(R.id.virusIconIV);
        virousIconIV.setImageResource(R.drawable.ic_addphoto);
        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    virousIconIV.setVisibility(View.INVISIBLE);
                    virousIconIV.setClickable(false);
                }else{
                    virousIconIV.setClickable(true);
                    virousIconIV.setVisibility(View.VISIBLE);
                }
            }
        });
        image = false;

        YoYo.with(Techniques.Pulse).duration(150).repeat(1).playOn(findViewById(R.id.arrowButton));

        Toasty.Config.getInstance().tintIcon(true).setTextSize(12).allowQueue(true).apply();
    }

    public void NextButton(View view) {

        if(checkBox.isChecked() && !virusNameET.getText().toString().isEmpty()){
            System.out.println("herereee");

            if(stringBuilder.length()>0)
                stringBuilder.delete(0,stringBuilder.length());

            System.out.println(stringBuilder);
            //String where = "virusName = '"+virusNameET.getText().toString()+"'";
            stringBuilder.append("virusName = '").append(virusNameET.getText().toString()).append("'");
            System.out.println(stringBuilder.toString());
            DataQueryBuilder queryBuilder = DataQueryBuilder.create();
            queryBuilder.setWhereClause(stringBuilder.toString());

            Backendless.Data.of(MedicalVirusInfo.class).find(queryBuilder, new AsyncCallback<List<MedicalVirusInfo>>() {
                @Override
                public void handleResponse(List<MedicalVirusInfo> response) {
                    if(response != null && response.size() > 0){
                        newPublish.virousName = virusNameET.getText().toString();
                        newPublish.virousImageUrl = response.get(0).getVirusImageUrl();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        virusNameET.setText("");
                        virousIconIV.setImageResource(R.drawable.ic_addphoto);
                        virousIconIV.setVisibility(View.VISIBLE);
                        virousIconIV.setClickable(true);
                        checkBox.setChecked(false);
                        Intent in = new Intent(MainActivity.this, VirousMedicines.class);
                        startActivity(in);
                    }

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            return;
        }else if(checkBox.isChecked()){
            return;
        }

        if(image && !virusNameET.getText().toString().isEmpty()){
            image = false;
            newPublish.virousName = virusNameET.getText().toString();

            Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.WEBP, 50, "" + virusNameET.getText().toString() + "icon.webp", "uploaded", new AsyncCallback<BackendlessFile>() {
                @Override
                public void handleResponse(BackendlessFile response) {
                    newPublish.virousImageUrl = response.getFileURL();
                    Toast.makeText(MainActivity.this, "uploaded correct", Toast.LENGTH_SHORT).show();
                    virusNameET.setText("");
                    virousIconIV.setImageResource(R.drawable.ic_addphoto);
                    Intent in = new Intent(MainActivity.this, VirousMedicines.class);
                    startActivity(in);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            //Toast.makeText(this, "Please fill virous or disease total information", Toast.LENGTH_LONG).show();
            Toasty.error(this, "Please fill Virus or disease total information", Toast.LENGTH_LONG, true).show();
        }
        YoYo.with(Techniques.FadeIn).duration(300).repeat(1).playOn(findViewById(R.id.arrowButton));
    }


    @Override
    public void onPickResult(PickResult r) {
        if(r.getError() == null){
            virousIconIV.setImageBitmap(r.getBitmap());
            image = true;
            bitmap =r.getBitmap();
        }else{
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Message",r.getError().getMessage()+"");
        }
    }


    public void AddPhoto(View view) {
        PickImageDialog.build(new PickSetup()).show(this);
    }
}