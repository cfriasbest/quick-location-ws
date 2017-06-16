package com.quick.location.firebase.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quick.location.model.PlaceDetail;
import com.quick.location.util.QuickLocationUtil;


@Service
public class FirebasePlaceService {

	@Autowired
	FirebaseServerApp firebaseServerApp;

	public void setPlaceListOnFirebase(List<PlaceDetail> places) {
		HashMap<String, Object> placestoFirebase = new HashMap<>();
		for (PlaceDetail placeDetail : places) {
			placestoFirebase.put(placeDetail.getPlaceId(), placeDetail);
		}
		firebaseServerApp.newChild(QuickLocationUtil.URL_FIREBASE_DATABASE,
		        QuickLocationUtil.URL_FIREBASE_DATABASE_CHILD_PLACES, placestoFirebase);
	}
	
	public  DatabaseReference getDatabaseReference(String url) {
		
		return FirebaseDatabase.getInstance().getReference(url);
	}
}
