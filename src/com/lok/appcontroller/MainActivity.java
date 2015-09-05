package com.lok.appcontroller;

import com.lok.appcontroller.model.FileLoaderBaseVersion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String IMEI;
    private String IMSI;
    private String PHONE_NO;
    // private String MANUFACTURER;
    private String ANDROID_ID;
    private String SERIAL_NUMBER;
    private String MODEL_NUMBER;
    private String MCC;
    private String MNC;
    private String MAC_ADDR;
    private static String tostring;

    private TextView imeiTV;
    private TextView imsiTV;
    private TextView phonenoTV;
    private TextView didTV;
    private TextView serialnoTV;
    private TextView modelnoTV;
    private TextView mccTV;
    private TextView mncTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInfo();
        initView();
    }

    @SuppressLint("NewApi")
    private void initInfo() {
        TelephonyManager tm = (TelephonyManager) this
                .getSystemService(TELEPHONY_SERVICE);
        Configuration cfg = getResources().getConfiguration();
        IMEI = tm.getDeviceId();
        IMSI = tm.getSubscriberId();
        PHONE_NO = tm.getLine1Number();
        ANDROID_ID = android.provider.Settings.Secure.getString(
                getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        SERIAL_NUMBER = android.os.Build.SERIAL;
        MODEL_NUMBER = android.os.Build.MODEL;
        MCC = String.valueOf(cfg.mcc);
        MNC = String.valueOf(cfg.mnc);
    }

    private void initView() {
        imeiTV = (TextView) findViewById(R.id.imei_str);
        imsiTV = (TextView) findViewById(R.id.imsi_str);
        phonenoTV = (TextView) findViewById(R.id.phone_no);
        didTV = (TextView) findViewById(R.id.did_str);
        serialnoTV = (TextView) findViewById(R.id.serial_number);
        modelnoTV = (TextView) findViewById(R.id.model_number);
        mccTV = (TextView) findViewById(R.id.mcc_str);
        mncTV = (TextView) findViewById(R.id.mnc_str);

        setTVValue();
    }

    private void setTVValue() {
        imeiTV.setText(IMEI);
        imsiTV.setText(IMSI);
        phonenoTV.setText(PHONE_NO);
        didTV.setText(ANDROID_ID);
        serialnoTV.setText(SERIAL_NUMBER);
        modelnoTV.setText(MODEL_NUMBER);
        mccTV.setText(MCC);
        mncTV.setText(MNC);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onResume() {
        initInfo();
        setTVValue();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}