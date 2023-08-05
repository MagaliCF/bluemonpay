package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models.Login;
import com.example.myapplication.models.User;
import com.example.myapplication.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Retrofit retrofit;

    private TextView txtViewRegister;
    private TextView txtViewLogin;
    private EditText editTxtUsername;
    private EditText editTxtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        txtViewRegister = findViewById(R.id.txtViewRegister);
        txtViewLogin = findViewById(R.id.txtViewLogin);
        editTxtUsername = findViewById(R.id.editTxtUsername);
        editTxtPwd = findViewById(R.id.editTxtPwd);

        txtViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButton();
            }
        });

        txtViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTxtUsername.getText().toString();
                String pwd = editTxtPwd.getText().toString();

                getData(username,pwd);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void registerButton(){
        Toast.makeText(this, "Oh no! Still working...", Toast.LENGTH_SHORT).show();
    }

    private void getData(String username, String password){
        Login login = new Login(username,password);
        ApiService service = retrofit.create(ApiService.class);
        Call<User> userCall = service.getUSerInformation(login);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG, "onResponse code: " + response.code());
                Log.i(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
