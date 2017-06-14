package com.quick.location.firebase.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.quick.location.model.Place;

public class FirebasePlaceService {

	@Autowired
	FirebaseServerApp firebaseServerApp;
	
	public void setPlaceListOnFirebase(List<Place> places)
	{
		
	}
}
