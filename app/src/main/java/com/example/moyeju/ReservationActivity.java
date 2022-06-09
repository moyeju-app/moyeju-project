package com.example.moyeju;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity { //7번째 화면(시간 선택)

    private TextView dateView;
    private TextView ftimeView;
    private TextView stimeView;
    private TimePicker ftimePicker;
    private TimePicker stimePicker;
    private Button bt_next;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        dateView = findViewById(R.id.date_show);
        ftimePicker = findViewById(R.id.ftimePircker);
        ftimeView = findViewById(R.id.ftime_show);
        stimePicker = findViewById(R.id.stimePircker);
        stimeView = findViewById(R.id.stime_show);
        bt_next = findViewById(R.id.button2);

        Date date = new Date();       //오늘 날짜 표시
        String time = mFormat.format(date);
        dateView.setText(time);

        ftimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {  //입차시간 선택 및 출력

                ftimeView.setText("입차시간 : " + hourOfDay + "시" + minute + "분");
            }
        });

        stimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {  //출차시간 선택 및 출력

                stimeView.setText("출차시간 : " + hourOfDay + "시" + minute + "분");
            }
        });


        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}