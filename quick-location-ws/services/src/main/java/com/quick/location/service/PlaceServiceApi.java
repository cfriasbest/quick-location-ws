package com.quick.location.service;

import com.quick.location.model.Place;
import com.quick.location.model.ResponseForPlaceDetails;
import com.quick.location.model.ResponseForPlaces;

public interface PlaceServiceApi {

	public ResponseForPlaces getPlaces(Place place);

	public ResponseForPlaceDetails getPlaceDetail(String placeId);

}
