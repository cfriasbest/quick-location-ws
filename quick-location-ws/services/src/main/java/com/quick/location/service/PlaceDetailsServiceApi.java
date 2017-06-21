package com.quick.location.service;

import com.quick.location.model.PlaceDetail;
import com.quick.location.model.SugestData;


public interface PlaceDetailsServiceApi {

	public void savePlaceDetails(PlaceDetail placeDetail);
	
	public void updatePlaceDetails(SugestData sugestData);

}
