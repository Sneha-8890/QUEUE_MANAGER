package com.sneha.sih_2022_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SelectLocation extends AppCompatActivity {

    ArrayList<LocationMart> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.mart_recycler);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.set_location, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                list.clear();
                if(parent.getItemAtPosition(position).toString().equals("Haridwar")){
                    list.add(new LocationMart("Vishal Mega Mart"));
                    list.add(new LocationMart("Pantaloons"));
                    list.add(new LocationMart("Trends"));
                }
                else if(parent.getItemAtPosition(position).toString().equals("Roorkee")){
                    list.add(new LocationMart("Vishal Mega Mart"));
                    list.add(new LocationMart("V2"));
                }
                else {
                    list.add(new LocationMart("Pacific Mall"));
                    list.add(new LocationMart("Time Square Mall"));
                    list.add(new LocationMart("Crossroads Mall"));
                    list.add(new LocationMart("City Center"));
                }
                LocationMartAdapter adapter = new LocationMartAdapter(list, SelectLocation.this, new LocationMartAdapter.OnClickMallInterface() {
                    @Override
                    public void onMallClick(int position) {
                        Intent intent = new Intent(SelectLocation.this, MallDetailActivity.class);
                        startActivity(intent);
                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(SelectLocation.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(SelectLocation.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();}

        return true;
    }
}