package com.quick.location.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.location.firebase.config.FirebaseServerApp;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.service.PlaceServiceApi;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class PlaseService implements PlaceServiceApi {

	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Autowired
	FirebaseServerApp firebaseServerApp;
//
//	@Override
//	public ResponseForPlaces getPlaces(Place place) {
//		List<PlaceEntity> places = (List<PlaceEntity>) placeEntityRepo.findAll();
//
//		ResponseForPlaces placesR = new ResponseForPlaces();
//
//		placesR.setResults(MapperUtil.mapAsList(places, Place.class));
//		return placesR;
//	}

//	@Override
//
//	public ResponseForPlaceDetails getPlaceDetail(String placeId) {
//		PlaceEntity place = placeEntityRepo.findOne(placeId);
//		ResponseForPlaceDetails placesR = new ResponseForPlaceDetails();
//		placesR.setResult(MapperUtil.mapBean(place, PlaceDetail.class));
//
//		return placesR;
//	}

}
