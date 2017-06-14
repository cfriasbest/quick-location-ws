package com.quick.location.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.location.entity.PlaceEntity;
import com.quick.location.model.PlaceDetail;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.PlacedetailEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class PlaceDetailsService implements PlaceDetailsServiceApi {

	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Autowired
	PlacedetailEntityRepo placedetailEntityRepo;


	@Override
	@Transactional
	public void savePlaceDetails(PlaceDetail placeDetail) {
		DozerBeanMapper mapper = new DozerBeanMapper();

		PlaceEntity placeEntity = mapper.map(placeDetail, PlaceEntity.class);
		placeEntity.autoSetThis();
		placeEntityRepo.save(placeEntity);
	}

	// @Autowired
	// PlaceDetailRepo placeDetailRepoRepo;
	//
	// @Autowired
	// PlaceRepo placeRepo;
	//
	// @Autowired
	// PhotoRepo photoRepo;
	//
	// @Override
	// public ResponseForPlaces getPlaces(String id) {
	// ResponseForPlaces responseForPlaces = new ResponseForPlaces();
	// responseForPlaces.setResults(new ArrayList<Place>());
	// Iterable<com.quick.location.entity.PlaceEntity> places = placeRepo.findAll();
	// for (com.quick.location.entity.PlaceEntity place : places)
	// responseForPlaces.getResults().add(toPlaceModel(place));
	// return responseForPlaces;
	// }
	//
	// @Override
	// public ResponseForPlaceDetails getPlaceDetail(String placeId) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public void savePlace(Place place) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// private Place toPlaceModel(com.quick.location.entity.PlaceEntity place) {
	// Place placeModel = new Place();
	// Geometry geometry = new Geometry();
	// Location location = new Location();
	// location.setLat(place.getLat());
	// location.setLng(place.getLng());
	// geometry.setLocation(location);
	// placeModel.setGeometry(geometry);
	//
	// placeModel.setPlaceId(place.getPlaceId());
	// placeModel.setName(place.getName());
	// return placeModel;
	// }
	//
	// @Override
	// @Transactional(propagation = Propagation.REQUIRED)
	// public void savePlaceDetails(PlaceDetail placeDetailModel) {
	//
	// com.quick.location.entity.PlaceEntity placeEntity =
	// toPlaceEntity(placeDetailModel);
	// PlacedetailEntity placeDetailEntity =
	// toPlaceDetailsEntity(placeDetailModel);
	// placeDetailEntity.setPlace(placeEntity);
	// placeEntity.setPlacedetail(placeDetailEntity);
	// toPhotoEntity(placeEntity, placeDetailModel.getPhotos());
	// toReviewEntity(placeDetailModel.getReviews(), placeEntity);
	//
	// placeRepo.save(placeEntity);
	//
	// }
	//
	// private PlacedetailEntity toPlaceDetailsEntity(PlaceDetail
	// placeDetailModel) {
	// PlacedetailEntity placeDetailEntity = new PlacedetailEntity();
	// placeDetailEntity.setFormattedAddress(placeDetailModel.getFormattedAddress());
	// placeDetailEntity.setFormattedPhoneNumber(placeDetailModel.getFormattedPhoneNumber());
	// placeDetailEntity.setUrl(placeDetailModel.getUrl());
	// placeDetailEntity.setWebsite(placeDetailModel.getWebsite());
	// return placeDetailEntity;
	// }
	//
	// private com.quick.location.entity.PlaceEntity toPlaceEntity(Place placeModel) {
	// com.quick.location.entity.PlaceEntity placeEntity = new
	// com.quick.location.entity.PlaceEntity();
	// placeEntity.setLat(placeModel.getGeometry().getLocation().getLat());
	// placeEntity.setLng(placeModel.getGeometry().getLocation().getLng());
	// placeEntity.setName(placeModel.getName());
	// placeEntity.setPlaceId(placeModel.getPlaceId());
	// placeEntity.setVicinty(placeModel.getVicinity());
	// return placeEntity;
	//
	// }
	//
	// private void toPhotoEntity(com.quick.location.entity.PlaceEntity place,
	// List<com.quick.location.service.model.Photo> list) {
	// for (com.quick.location.service.model.Photo photoModel : list) {
	// PhotoEntity PhotoEntity = new PhotoEntity();
	// PhotoEntity.setPhotoReference(photoModel.getPhotoReference());
	// place.addPhoto(PhotoEntity);
	// }
	// }
	//
	// private void toReviewEntity(List<com.quick.location.service.model.Review>
	// seviewModels, com.quick.location.entity.PlaceEntity placeEntity) {
	// for (com.quick.location.service.model.Review seviewModel : seviewModels) {
	// ReviewEntity review = new ReviewEntity();
	// review.setAuthorName(seviewModel.getAuthorName());
	// // review.setAuthorUrl(seviewModel.getAuthorName());
	// review.setText(seviewModel.getText());
	// // review.setAuthorName(seviewModel.getAuthorName());
	// // review.setAuthorName(seviewModel.getAuthorName());
	// // review.setAuthorName(seviewModel.getAuthorName());
	// placeEntity.addReviewEntity(review);
	//
	// }
	//
	// }
}
