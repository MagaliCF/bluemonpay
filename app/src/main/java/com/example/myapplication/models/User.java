package com.example.myapplication.models;


import com.google.gson.annotations.SerializedName;
public class User {

	@SerializedName("id")
	private int id;

	@SerializedName("username")
	private String username;

	@SerializedName("email")
	private String email;

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("gender")
	private String gender;

	@SerializedName("image")
	private String image;

	@SerializedName("token")
	private String token;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"id = '" + id + '\'' + 
			",username = '" + username + '\'' + 
			",email = '" + email + '\'' + 
			",firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",gender = '" + gender + '\'' + 
			",image = '" + image + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}