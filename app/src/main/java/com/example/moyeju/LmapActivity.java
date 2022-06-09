package com.example.moyeju;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LmapActivity extends AppCompatActivity { //6번째 화면(주차장 정보 선택)

    private ImageView park_impo;
    private ImageButton close_bt;
    private Button res_bt;
    private Button park_sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmap);

        park_impo = findViewById(R.id.park_im);
        close_bt = findViewById(R.id.clos);
        res_bt = findViewById(R.id.re_bt);
        park_sel = findViewById(R.id.parking_bt);

        park_impo.setVisibility(View.INVISIBLE);
        close_bt.setVisibility(View.INVISIBLE);
        res_bt.setVisibility(View.INVISIBLE);

        park_sel.setOnClickListener(new View.OnClickListener() {  //정보 띄우기
            @Override
            public void onClick(View view) {
                park_impo.setVisibility(View.VISIBLE);
                close_bt.setVisibility(View.VISIBLE);
                res_bt.setVisibility(View.VISIBLE);
            }
        });

        close_bt.setOnClickListener(new View.OnClickListener() { //정보 닫기
            @Override
            public void onClick(View view) {
                park_impo.setVisibility(View.INVISIBLE);
                close_bt.setVisibility(View.INVISIBLE);
                res_bt.setVisibility(View.INVISIBLE);
            }
        });

        res_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });

    }
}