package com.quick.location.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quick.location.model.Place;
import com.quick.location.model.ResponseForPlaceDetails;
import com.quick.location.model.ResponseForPlaces;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.PlaceServiceApi;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cfriasb
 *
 */
@RestController
@Slf4j
public class WsApiController extends AbstractController {

	@Autowired
	PlaceDetailsServiceApi placeDetailsServiceApi;

	@Autowired
	PlaceServiceApi placeServiceApi;


	public void savePlace() {

	}

	public void setScheduleSuggestion() {

	}

	public void setDirSuggestion() {

	}

	public void setPhoneSuggestion() {

	}


	@ResponseBody
	@RequestMapping(value = "/rest/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> savePlacesDetails(@RequestBody ResponseForPlaceDetails placeDetails) {
		log.info("Ingresando al metodo savePlacesDetails");
		log.info(" Se almacenara el la siguiente Trama" + placeDetails.toString());

		placeDetailsServiceApi.savePlaceDetails(placeDetails.getResult());

		log.info("Saliendo al metodo savePlacesDetails");
		
		return  new ResponseEntity<>(HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/rest/getplaces", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<ResponseForPlaces> getPlaces() {

		ResponseForPlaces responce = placeServiceApi.getPlaces(new Place());
		if (responce.getResults() == null || responce.getResults().isEmpty()) {
			responce.setStatus("FAILD");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		responce.setStatus("OK");
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/rest/getplacesdetail", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<ResponseForPlaceDetails> getPlaceDetail() {

		ResponseForPlaceDetails responce = placeServiceApi.getPlaceDetail("ChIJe3IIQPqorI8R2kRfwnn9FBw");
		if (responce.getResult() == null) {
			responce.setStatus("FAILD");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		responce.setStatus("OK");
		return new ResponseEntity<>(responce, HttpStatus.OK);
	}

}
