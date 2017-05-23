package com.neko.service;

import java.util.List;

import com.neko.service.model.Place;
import com.neko.service.model.PlaceDetail;
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.model.ResponseForPlaces;

public interface PlaceServiceApi {
	
	public ResponseForPlaceDetails getPlaceDetail(String placeId);
	
	public void savePlace(Place place);
	
	public void savePlaceDetails(PlaceDetail placeDetail);

	ResponseForPlaces getPlaces(String id);

}
