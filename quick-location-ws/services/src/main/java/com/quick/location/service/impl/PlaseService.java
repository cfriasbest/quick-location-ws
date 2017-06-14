package com.quick.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.location.entity.PlaceEntity;
import com.quick.location.firebase.config.FirebaseServerApp;
import com.quick.location.model.Place;
import com.quick.location.model.PlaceDetail;
import com.quick.location.model.ResponseForPlaceDetails;
import com.quick.location.model.ResponseForPlaces;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.service.PlaceServiceApi;
import com.quick.location.service.util.MapperUtil;

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

	@Override
	public Place getPlace(Place place) {
		PlaceEntity placeEntity = MapperUtil.mapBean(place, PlaceEntity.class);
		return MapperUtil.mapBean(placeEntityRepo.findOne(placeEntity.getPlaceId()), Place.class);
	}

	@Override
	public Place savePlace(Place place) {
		PlaceEntity placeEntity = MapperUtil.mapBean(place, PlaceEntity.class);
		return MapperUtil.mapBean(placeEntityRepo.save(placeEntity), Place.class);
	}

	@Override
	public Place upadatePlace(Place place) {
		return savePlace(place);
	}

	@Override
	public ResponseForPlaces getPlaces(Place place) {
		List<PlaceEntity> places = (List<PlaceEntity>) placeEntityRepo.findAll();

		ResponseForPlaces placesR = new ResponseForPlaces();

		placesR.setResults(MapperUtil.mapAsList(places, Place.class));
		return placesR;
	}

	@Override

	public ResponseForPlaceDetails getPlaceDetail(String placeId) {
		PlaceEntity place = placeEntityRepo.findOne(placeId);
		ResponseForPlaceDetails placesR = new ResponseForPlaceDetails();
		placesR.setResult(MapperUtil.mapBean(place, PlaceDetail.class));

		return placesR;
	}

}
