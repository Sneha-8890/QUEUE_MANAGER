package com.sneha.sih_2022_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.SearchView;

public class MallDetailActivity extends AppCompatActivity {

    Button btn;
    public static final int CAMERA_REQUEST_PERMISSION =1;
    public static final int CAMERA_IMAGE_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);

        btn = findViewById(R.id.qr_scan);
        btn.setOnClickListener(v-> startScanning());

    }

    private void startScanning(){
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                //ask for permission
                requestPermissions(new String[]{Manifest.permission.CAMERA},CAMERA_REQUEST_PERMISSION);
            }
            else{
                //permission is granted
                startActivityForResult(new Intent(MallDetailActivity.this, ScanningActivity.class),CAMERA_IMAGE_CODE);
            }
        }
        else {
            startActivityForResult(new Intent(MallDetailActivity.this, ScanningActivity.class),CAMERA_IMAGE_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CAMERA_REQUEST_PERMISSION){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(MallDetailActivity.this, ScanningActivity.class),CAMERA_IMAGE_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_IMAGE_CODE){
            if(resultCode == RESULT_OK) {
                String product_id = data.getStringExtra("scanning_result");

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }
}