package com.example.moyeju;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WarningActivity extends AppCompatActivity { //2번째 화면(주의사항)

    private ImageButton warning_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        warning_bt = findViewById(R.id.imageButton);

        warning_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FimapActivity.class);
                startActivity(intent);
            }
        });

    }
}