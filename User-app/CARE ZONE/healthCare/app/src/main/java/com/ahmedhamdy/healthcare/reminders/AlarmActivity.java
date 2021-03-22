package com.ahmedhamdy.healthcare.reminders;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ahmedhamdy.healthcare.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm);

        TextView tv=(TextView)findViewById(R.id.AlarmActtextView) ;
        TextView name = (TextView) findViewById(R.id.medname);
        TextView inst = (TextView) findViewById(R.id.instructions);

        Intent i=getIntent();
        int a=i.getExtras().getInt("AlarmActivityid");
        DatabaseHandler dh= new DatabaseHandler(this);
        ArrayList ar=dh.getReminder(a);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        tv.setText(date);
        name.setText(ar.get(1).toString());
        String instructions = ar.get(2).toString();
        if(instructions.compareTo("")==0)
            inst.setVisibility(View.GONE);
        else
            inst.setText(instructions);

        Button stop=(Button)findViewById(R.id.stop);

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                YoYo.with(Techniques.FadeIn).playOn(findViewById(R.id.stop));
                AlarmReceiver.ringtone.stop();
                finish();
                //android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlarmReceiver.ringtone.stop();
        finish();
    }
}