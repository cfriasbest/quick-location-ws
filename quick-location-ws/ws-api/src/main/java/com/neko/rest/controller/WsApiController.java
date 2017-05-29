package com.neko.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neko.service.PlaceServiceApi;
import com.neko.service.ReviewServiceApi;
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.model.ResponseForPlaces;
import com.neko.service.model.Review;

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
	PlaceServiceApi placeServiceApi;

	@Autowired
	ReviewServiceApi reviewServiceApi;

	public void savePlace() {

	}

	public void setScheduleSuggestion() {

	}

	public void setDirSuggestion() {

	}

	public void setPhoneSuggestion() {

	}

	public void getPlaceDetail() {

	}

	@ResponseBody
	@RequestMapping(value = "/rest/review", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> setReview(@RequestBody Review review) {
		log.info("Ingresando al metodo setReview");
		reviewServiceApi.saveReview(review);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// ChIJe3IIQPqorI8R2kRfwnn9FBw Place ID
	@ResponseBody
	@RequestMapping(value = "/rest/save", method = RequestMethod.POST, headers = "Accept=application/json")
	public Object savePlacesDetails(@RequestBody ResponseForPlaceDetails placeDetails) {
		log.info("Ingresando al metodo savePlacesDetails");
		log.info(" Se almacenara el la siguiente Trama" + placeDetails.toString());

		placeServiceApi.savePlaceDetails(placeDetails.getResult());

		log.info("Saliendo al metodo savePlacesDetails");
		return placeDetails;
	}

	 @ResponseBody
	 @RequestMapping(value = "/rest/getplaces", method = RequestMethod.GET,
	 headers = "Accept=application/json")
	 public ResponseEntity<ResponseForPlaces> getPlaces() {
	
	 ResponseForPlaces responce = placeServiceApi.getPlaces("");
	 if (responce.getResults() == null || responce.getResults().isEmpty()) {
	 responce.setStatus("FAILD");
	 return new ResponseEntity<ResponseForPlaces>(HttpStatus.NOT_FOUND);
	 }
	 responce.setStatus("OK");
	 return new ResponseEntity<ResponseForPlaces>(responce, HttpStatus.OK);
	 }

}
