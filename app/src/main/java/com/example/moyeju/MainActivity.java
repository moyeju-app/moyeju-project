package com.example.moyeju;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {  //8번째 화면(아두이노를 통한 자리선택)

    private BluetoothSPP bt;

    private Button bluetooth_connect;
    private TextView parking_n;
    private TextView parking_a1;
    private TextView parking_a2;
    private TextView parking_a3;
    private TextView parking_a4;
    int a1_flag = 0;
    int a2_flag = 0;
    int a3_flag = 0;
    int a4_flag = 0;

    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = new BluetoothSPP(this);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        parking_n = findViewById(R.id.parking_n);
        parking_a1 = findViewById(R.id.parking_a1);
        parking_a2 = findViewById(R.id.parking_a2);
        parking_a3 = findViewById(R.id.parking_a3);
        parking_a4 = findViewById(R.id.parking_a4);

        parking_a1.setOnClickListener(new View.OnClickListener() {//A1 주차자리 누르면
            public void onClick(View v) {
                if (a1_flag == 1) {//a1_flag값이 1이면 (주차가능 상태)
                    vibe.vibrate(200);
                    dialog_show("[ A1 ]자리를 예약하시겠습니까?");
                } else {//(주차불가능 상태)
                    vibe.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "이미 주차중입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        parking_a2.setOnClickListener(new View.OnClickListener() {//A2 주차자리
            public void onClick(View v) {
                if (a2_flag == 1) {
                    vibe.vibrate(200);
                    dialog_show("[ A2 ]자리를 예약하시겠습니까?");
                } else {
                    vibe.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "이미 주차중입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        parking_a3.setOnClickListener(new View.OnClickListener() {//A3 주차자리
            public void onClick(View v) {
                if (a3_flag == 1) {
                    vibe.vibrate(200);
                    dialog_show("[ A3 ]자리를 예약하시겠습니까?");
                } else {
                    vibe.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "이미 주차중입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        parking_a4.setOnClickListener(new View.OnClickListener() {//A4 주차자리
            public void onClick(View v) {
                if (a4_flag == 1) {
                    vibe.vibrate(200);
                    dialog_show("[ A4 ]자리를 예약하시겠습니까?");
                } else {
                    vibe.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "이미 주차중입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bluetooth_connect = findViewById(R.id.bluetooth_connect);//블루투스 연결버튼 누르면
        bluetooth_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vibe.vibrate(200);
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {//연결되있으면
                    bt.disconnect();//연결해제
                } else {//연결되있지않으면
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {//블루투스 데이터 수신
            public void onDataReceived(byte[] data, String message) {
                try {
                    int parking_count = 0;

                    if (message.charAt(0) == 'O') {//첫번째 수신데이터가 O 이면 (A1 주차가능)
                        parking_count++;//parking_count값 증가
                        parking_a1.setBackgroundResource(R.drawable.a1_green);
                        a1_flag = 1;//a1_flag값을 1로 변경
                    } else if (message.charAt(0) == 'X') {//첫번째 수신데이터가 X 이면 (A1 주차중)
                        parking_a1.setBackgroundResource(R.drawable.a1_red);
                        a1_flag = 0;//a1_flag값을 0으로 변경
                    }

                    if (message.charAt(1) == 'O') {//두번째
                        parking_count++;
                        parking_a2.setBackgroundResource(R.drawable.a2_green);
                        a2_flag = 1;
                    } else if (message.charAt(1) == 'X') {
                        parking_a2.setBackgroundResource(R.drawable.a2_red);
                        a2_flag = 0;
                    }

                    if (message.charAt(2) == 'O') {//세번째
                        parking_count++;
                        parking_a3.setBackgroundResource(R.drawable.a3_green);
                        a3_flag = 1;
                    } else if (message.charAt(2) == 'X') {
                        parking_a3.setBackgroundResource(R.drawable.a3_red);
                        a3_flag = 0;
                    }

                    if (message.charAt(3) == 'O') {//네번째
                        parking_count++;
                        parking_a4.setBackgroundResource(R.drawable.a4_green);
                        a4_flag = 1;
                    } else if (message.charAt(3) == 'X') {
                        parking_a4.setBackgroundResource(R.drawable.a4_red);
                        a4_flag = 0;
                    }

                    parking_n.setText("" + parking_count);//해당변수에 parking_count값 출력 (자리현황)

                } catch (Exception e) {
                }

            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {//블루투스 연결
            public void onDeviceConnected(String name, String address) {
                vibe.vibrate(1000);
                Toast.makeText(getApplicationContext(), "블루투스 연결성공!\n" + name + " , " + address, Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() {//연결해제
                vibe.vibrate(1000);
                Toast.makeText(getApplicationContext(), "블루투스 연결해제!", Toast.LENGTH_LONG).show();
            }

            public void onDeviceConnectionFailed() {//연결실패
                vibe.vibrate(3000);
                Toast.makeText(getApplicationContext(), "블루투스 연결실패!!!", Toast.LENGTH_LONG).show();
            }
        });

        if (!bt.isBluetoothAvailable()) {//블루투스 사용불가
            Toast.makeText(getApplicationContext(), "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void dialog_show(String ment) {//입력한 문구를 띄어주는 다이얼로그
        AlertDialog.Builder alert_dialog = new AlertDialog.Builder(MainActivity.this);
        TextView tv = new TextView(MainActivity.this);
        tv.setGravity(Gravity.CENTER);
        tv.setText("\n" + ment);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tv.setTextColor(Color.parseColor("#000000"));
        alert_dialog.setCustomTitle(tv);
        alert_dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //확인을 누르면 SubActivity 실행
                Intent intent = new Intent(getApplicationContext(), QrActivity.class);
                startActivity(intent);
            }
        });
        alert_dialog.setNeutralButton("취소", null);
        alert_dialog.setCancelable(false);
        alert_dialog.show();
    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService();
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth was not enabled.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }




}