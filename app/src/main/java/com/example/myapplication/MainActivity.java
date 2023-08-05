package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.AdapterCatalog;
import com.example.myapplication.models.ItemCatalog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private AdapterCatalog adapter;

    private ArrayList<ItemCatalog> catalog = new ArrayList<>();
    private TextView txtViewCatalog;
    private TextView txtViewEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewEmpty = findViewById(R.id.txtViewEmpty);
        txtViewCatalog = findViewById(R.id.txtViewCatalog);
        recyclerView = findViewById(R.id.recyclerCatalog);

        initViews();

        initValues();
    }

    private void initViews(){
        if(catalog.isEmpty()){
            txtViewEmpty.setVisibility(View.VISIBLE);
            txtViewCatalog.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txtViewEmpty.setVisibility(View.GONE);
            txtViewCatalog.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        catalog = getCatalog();
        adapter = new AdapterCatalog(catalog);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<ItemCatalog> getCatalog(){
        //TODO: Implement getCatalog of ApiService
        ArrayList<ItemCatalog> mCatalog = new ArrayList<>();

        return mCatalog;
    }
}