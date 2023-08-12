package com.example.myapplication.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("discountPercentage")
	private float discountPercentage;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("images")
	private List<String> images;

	@SerializedName("price")
	private int price;

	@SerializedName("rating")
	private Object rating;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("stock")
	private int stock;

	@SerializedName("category")
	private String category;

	@SerializedName("brand")
	private String brand;

	public float getDiscountPercentage(){
		return discountPercentage;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public List<String> getImages(){
		return images;
	}

	public int getPrice(){
		return price;
	}

	public Object getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public int getStock(){
		return stock;
	}

	public String getCategory(){
		return category;
	}

	public String getBrand(){
		return brand;
	}

	@Override
	public String toString() {
		return "ProductsItem{" +
				"discountPercentage=" + discountPercentage +
				", thumbnail='" + thumbnail + '\'' +
				", images=" + images +
				", price=" + price +
				", rating=" + rating +
				", description='" + description + '\'' +
				", id=" + id +
				", title='" + title + '\'' +
				", stock=" + stock +
				", category='" + category + '\'' +
				", brand='" + brand + '\'' +
				'}';
	}
}