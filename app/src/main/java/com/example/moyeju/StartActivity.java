package com.example.moyeju;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {  //첫번째 화면(시작버튼)

    private Button start_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_bt = findViewById(R.id.stbt);

        start_bt.setOnClickListener(new View.OnClickListener() {  //start 버튼 클릭
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WarningActivity.class);
                startActivity(intent);
            }
        });

    }
}
