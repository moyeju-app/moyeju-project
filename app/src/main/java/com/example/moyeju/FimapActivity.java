package com.example.moyeju;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FimapActivity extends AppCompatActivity { //3번째 화면

    private ImageButton seoul_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmap);

        seoul_bt = findViewById(R.id.seoulbt);

        seoul_bt.setOnClickListener(new View.OnClickListener() {  //서울버튼 클릭
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SmapActivity.class);
                startActivity(intent);
            }
        });

    }
}