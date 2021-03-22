package com.ahmedhamdy.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.ahmedhamdy.healthcare.reminders.Reminder;

public class UserReminders extends AppCompatActivity implements Reminder.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reminders);
        Reminder rm=new Reminder();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,rm).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}