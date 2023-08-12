package com.example.myapplication.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

public class CatalogResponse{

	@SerializedName("total")
	private int total;

	@SerializedName("limit")
	private int limit;

	@SerializedName("skip")
	private int skip;

	@SerializedName("products")
	private List<ProductsItem> products;

	public int getTotal(){
		return total;
	}

	public int getLimit(){
		return limit;
	}

	public int getSkip(){
		return skip;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}