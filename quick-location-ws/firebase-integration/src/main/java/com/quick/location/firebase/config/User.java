package com.quick.location.firebase.config;

import java.io.Serializable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public  class User implements Serializable{

    public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 8519120258017568823L;
	public String date_of_birth;
    public String full_name;
    public String nickname;
    
    
	public User() {
		super();

	}
	
	public User(String date_of_birth, String full_name, String nickname) {
		super();
		this.date_of_birth = date_of_birth;
		this.full_name = full_name;
		this.nickname = nickname;
	}
	
	


	public User(String date_of_birth, String full_name) {
		super();
		this.date_of_birth = date_of_birth;
		this.full_name = full_name;
	}
	
	

   

}
