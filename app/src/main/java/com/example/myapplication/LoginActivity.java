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
import com.example.myapplication.models.UserResponse;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.utils.Utils;

import java.time.LocalTime;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Retrofit retrofit;
    Utils utils;
    public String token = null;

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

        String existingToken = Utils.getToken(this,"authCredentials");
        if(!existingToken.equals("")){
            //Toast.makeText(this, "onCreate: existing token: " + existingToken, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        }

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

                getToken(username,pwd);

            }
        });
    }

    private void registerButton(){
        Toast.makeText(this, "Oh no! Still working...", Toast.LENGTH_SHORT).show();
    }

    private void getToken(String username, String password){
        Login login = new Login(username,password);
        ApiService service = retrofit.create(ApiService.class);
        Call<UserResponse> userCall = service.getUSerInformation(login);

        userCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    UserResponse userResponse = response.body();

                    token = userResponse.getToken();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                    Log.i(TAG, "onResponse token: " + token);

                    Calendar calendar = Calendar.getInstance();
                    int hora = calendar.get(Calendar.HOUR_OF_DAY);
                    int minutos = calendar.get(Calendar.MINUTE);
                    String concatenacion = hora + "." + minutos;
                    float horario = Float.parseFloat(concatenacion);

                    Utils.setToken(getApplicationContext(),"authCredentials",token);
                    Utils.setHora(getApplicationContext(), "authHour", horario);

                } else {
                    Toast.makeText(LoginActivity.this, "Login not correct 😢", Toast.LENGTH_SHORT).show();
                    Utils.setToken(getApplicationContext(),"authCredentials","");
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
