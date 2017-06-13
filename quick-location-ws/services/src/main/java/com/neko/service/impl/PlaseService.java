package com.neko.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neko.entity.PlaceEntity;
import com.neko.entity.PlacedetailEntity;
import com.neko.repo.PlaceEntityRepo;
import com.neko.service.PlaceServiceApi;
import com.neko.service.model.Place;
import com.neko.service.model.PlaceDetail;
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.model.ResponseForPlaces;
import com.neko.service.util.MapperUtil;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class PlaseService implements PlaceServiceApi {



	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Override
	public Place getPlace(Place place) {
		PlaceEntity placeEntity = MapperUtil.mapBean(place, PlaceEntity.class);
		return  MapperUtil.mapBean(placeEntityRepo.findOne(placeEntity.getPlaceId()),Place.class);
	}

	@Override
	public Place savePlace(Place place) {
		PlaceEntity placeEntity = MapperUtil.mapBean(place, PlaceEntity.class);
		return MapperUtil.mapBean(placeEntityRepo.save(placeEntity),Place.class);
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
		PlaceEntity place =placeEntityRepo.findOne(placeId);

		ResponseForPlaceDetails placesR = new ResponseForPlaceDetails();

		placesR.setResult(MapperUtil.mapBean(place, PlaceDetail.class));
		return placesR;
	}

}
