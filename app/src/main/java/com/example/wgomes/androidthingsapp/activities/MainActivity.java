package com.example.wgomes.androidthingsapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.wgomes.androidthingsapp.R;
import com.example.wgomes.androidthingsapp.model.RequestQueue;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    public String mChildRequestQueue = "request_queue";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        setupView();

    }

    public void setupView() {
        Switch switchButton = (Switch) findViewById(R.id.switchButton);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    registerRequest(true);
                } else {
                    registerRequest(false);
                }
            }
        });

    }

    public void registerRequest(Boolean requestToOn) {
        Date requestDate = new Date();
        RequestQueue request = new RequestQueue("AppAndroid",
                false, requestDate.toString(), "", requestToOn);
        databaseReference.child(mChildRequestQueue).setValue(request);
    }


}
