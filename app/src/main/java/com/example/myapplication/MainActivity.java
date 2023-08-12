package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.AdapterCatalog;
import com.example.myapplication.models.CatalogResponse;
import com.example.myapplication.models.Login;
import com.example.myapplication.models.ProductsItem;
import com.example.myapplication.models.UserResponse;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    Utils utils;
    private RecyclerView recyclerView;
    private TextView txtViewLogout;
    private AdapterCatalog adapter;

    private ArrayList<ProductsItem> catalog = new ArrayList<>();
    private TextView txtViewCatalog;
    private TextView txtViewEmpty;

    private Retrofit retrofit;
    private String token;

    private int offset;
    private boolean aptoParaCargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        txtViewEmpty = findViewById(R.id.txtViewEmpty);
        txtViewCatalog = findViewById(R.id.txtViewCatalog);
        recyclerView = findViewById(R.id.recyclerCatalog);
        txtViewLogout = findViewById(R.id.txtViewLogout);

        if(horario()){
            token = Utils.getToken(this,"authCredentials");
            getCatalog();
        } else {
            logout();
        }

        txtViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    private boolean horario(){
        float mHorario1 = Utils.getHora(this, "authHour");
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minutos = calendar.get(Calendar.MINUTE);
        String concatenacion = hora + "." + minutos;
        float mHorario2 = Float.parseFloat(concatenacion);
        float res = mHorario1 - mHorario2;

        if (res <= -1){
            return false;
        } else {
            return true;
        }
    }

    private void logout() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
        Utils.setToken(getApplicationContext(),"authCredentials","");
        Utils.setHora(getApplicationContext(), "authHour", 0);
    }

    private void getCatalog(){
        ApiService service = retrofit.create(ApiService.class);
        Call<CatalogResponse> catalogResponseCall = service.getCatalog("Bearer " + token);

        catalogResponseCall.enqueue(new Callback<CatalogResponse>() {
            @Override
            public void onResponse(Call<CatalogResponse> call, Response<CatalogResponse> response) {
                if(response.isSuccessful()){
                    CatalogResponse catalogResponse = response.body();
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(manager);
                    catalog = (ArrayList<ProductsItem>) catalogResponse.getProducts();
                    adapter = new AdapterCatalog(catalog, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e(TAG, "onResponse error: " + response.message() );
                    Toast.makeText(getApplicationContext(), "Oh no! Something wrong", Toast.LENGTH_SHORT).show();
                    logout();
                }
            }

            @Override
            public void onFailure(Call<CatalogResponse> call, Throwable t) {
                Log.e(TAG, "onFailure getting catalog response: " + t.getMessage() );
            }
        });
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

}