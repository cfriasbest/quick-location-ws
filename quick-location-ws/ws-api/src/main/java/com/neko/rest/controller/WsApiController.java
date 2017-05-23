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
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.model.ResponseForPlaces;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WsApiController extends AbstractController {

	@Autowired
	PlaceServiceApi placeServiceApi;

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
	@RequestMapping(value = "/rest/getplaces", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<ResponseForPlaces> getPlaces() {
		
		ResponseForPlaces responce = placeServiceApi.getPlaces("");
	        if (responce.getResults() == null || responce.getResults().isEmpty()) {
	        	responce.setStatus("FAILD");
	            return new ResponseEntity<ResponseForPlaces>(HttpStatus.NOT_FOUND);
	        }
	        responce.setStatus("OK");
	        return new ResponseEntity<ResponseForPlaces>(responce, HttpStatus.OK);
	}

	// @Autowired
	// PlaceRepo placeRepo;
	//// PlaceDetailsMapper placeDetailsMapper;
	////
	//// @Autowired
	//// PlaceMapper PlaceMapper;
	////
	//// @Autowired
	//// TypeMapper typeMapper;
	//
	//// @ResponseBody
	//// @RequestMapping(value = "/rest/details", method = RequestMethod.GET,
	// headers = "Accept=application/json")
	//// public Object getResponseDetails() {
	////
	//// return
	// placeDetailsMapper.getPlaceDetailsByPlaceId("ChIJYwPo2_GorI8RDsFC8PFdoqs");
	//// }
	////
	//// @ResponseBody
	//// @RequestMapping(value = "/rest/api", method = RequestMethod.GET,
	// headers = "Accept=application/json")
	//// public Object getResponseApi() {
	////
	//// return PlaceMapper.getPlaceByPlaceId("ChIJYwPo2_GorI8RDsFC8PFdoqs");
	//// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/rest/papi", method = RequestMethod.GET, headers
	// = "Accept=application/json")
	// public PlaceJson getResponsePApi() {
	// PlaceJson placeJson = new PlaceJson();
	// PlaceDetail place = new PlaceDetail();
	// place.setPlaceId("Periculo");
	// placeJson.setResult(place);
	// return placeJson;
	// }
	//
	//
	// @ResponseBody
	// @RequestMapping(value = "/rest/save", method = RequestMethod.POST,
	// headers = "Accept=application/json")
	//// @Transactional
	// public Object getSave(@RequestBody ResponseForPlaceDetails placeDetails)
	// {
	// log.info("Ingresando al metodo ");
	////
	//// PlaceMapper.InsertPlace(place);
	//// typeMapper.InsertType(place);
	// com.neko.entity.Place place= placeRepo.findByPlaceId("1");
	//// List<Type> val = place.getTypes();
	//// System.out.println("Auxilioooooo"+val.get(0).getTypecol());
	// return placeDetails;
	// }

}
