package com.example.moyeju;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SmapActivity extends AppCompatActivity { //4번째화면

    private Button gangnam_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smap);

        gangnam_bt = findViewById(R.id.gn_bt);

        gangnam_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TmapActivity.class);
                startActivity(intent);
            }
        });

    }
}