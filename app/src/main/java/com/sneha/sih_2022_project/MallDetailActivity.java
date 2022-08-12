package com.sneha.sih_2022_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

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

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    FirebaseFirestore db;
    ArrayList<ItemModel> list;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_detail);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();

        product_ids = new ArrayList<>();

        btn = findViewById(R.id.qr_scan);
        btn.setOnClickListener(v-> startScanning());

        payBill = findViewById(R.id.payBill);

        payBill.setOnClickListener(v-> {
            Intent intent = new Intent(MallDetailActivity.this, FinalActivity.class);
            startActivity(intent);
        });



        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        itemAdapter = new ItemAdapter(MallDetailActivity.this, list);

        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("products")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            if(progressDialog.isShowing()) progressDialog.dismiss();
                            Log.e("FireStore error", error.getMessage());
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){
                            list.add(dc.getDocument().toObject(ItemModel.class));
                        }
                        for(int i=0; i<list.size(); i++) Log.v("List", list.get(i).prod_name+" "+list.get(i).prod_price);
                        itemAdapter.notifyDataSetChanged();
                        if(progressDialog.isShowing()) progressDialog.dismiss();
                    }

                });
        recyclerView = findViewById(R.id.recyclerViewDetail);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

            }
        }
    }


}