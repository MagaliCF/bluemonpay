package com.example.myapplication.network;

import com.example.myapplication.models.CatalogResponse;
import com.example.myapplication.models.Login;
import com.example.myapplication.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //POST Login
    @POST("auth/login")
    Call<UserResponse> getUSerInformation(@Body Login login);

    //GET catalog
    @GET("auth/products")
    Call<CatalogResponse> getCatalog(@Header("Authorization") String authToken);

}
