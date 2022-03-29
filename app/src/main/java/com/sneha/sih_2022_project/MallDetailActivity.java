package com.sneha.sih_2022_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MallDetailActivity extends AppCompatActivity {

    Button btn;
    public static final int CAMERA_REQUEST_PERMISSION =1;
    public static final int CAMERA_IMAGE_CODE=2;
    ArrayList<String> product_ids;
    Button payBill;
    FirebaseFirestore db;
    RecyclerView detailrecycler;
    ItemAdapter myadapter;
    ArrayList<ItemModel> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);

        product_ids = new ArrayList<>();
        userArrayList = new ArrayList<>();

        detailrecycler = findViewById(R.id.recyclerViewDetail);

        btn = findViewById(R.id.qr_scan);
        btn.setOnClickListener(v-> startScanning());

        payBill = findViewById(R.id.payBill);

        payBill.setOnClickListener(v-> {
            Intent intent = new Intent(MallDetailActivity.this, FinalActivity.class);
            startActivity(intent);
        });

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
                product_ids.add(product_id);
                updateList(product_ids);
            }
        }
    }

    private void updateList(ArrayList<String> product_ids) {

        userArrayList.clear();

        db = FirebaseFirestore.getInstance();

        myadapter = new ItemAdapter(userArrayList, MallDetailActivity.this);

        db.collection("products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error!=null){
                    Log.e("FireStore error", error.getLocalizedMessage());
                    return;
                }

                for(DocumentChange dc : value.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        userArrayList.add(dc.getDocument().toObject(ItemModel.class));
                    }
                }

            }

        });

        detailrecycler.setLayoutManager(new LinearLayoutManager(MallDetailActivity.this));
        detailrecycler.setAdapter(myadapter);

        myadapter.notifyDataSetChanged();
    }


}