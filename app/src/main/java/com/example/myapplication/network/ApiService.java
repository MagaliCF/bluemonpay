package com.example.myapplication.network;

import com.example.myapplication.models.ItemCatalog;
import com.example.myapplication.models.Login;
import com.example.myapplication.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //POST Login
    @POST("auth/login")
    Call<User> getUSerInformation(@Body Login login);

    //GET catalog
    //Call<ItemCatalog> getCatalog(@Header("Authorization") token: String);

}
