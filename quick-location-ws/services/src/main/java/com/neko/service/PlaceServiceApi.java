package com.neko.service;

import java.util.List;

import com.neko.service.model.Place;
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.model.ResponseForPlaces;

public interface PlaceServiceApi {
	
	public Place getPlace(Place place);
	
	public ResponseForPlaces getPlaces(Place place);
	
	public Place savePlace(Place place);
	
	public Place upadatePlace(Place place);
	
	public ResponseForPlaceDetails getPlaceDetail(String placeId);

}
