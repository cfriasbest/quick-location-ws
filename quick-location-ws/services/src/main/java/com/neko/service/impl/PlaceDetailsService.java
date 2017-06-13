package com.neko.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neko.entity.PlaceEntity;
import com.neko.entity.PlacedetailEntity;
import com.neko.repo.PlaceEntityRepo;
import com.neko.repo.PlacedetailEntityRepo;
import com.neko.service.PlaceDetailsServiceApi;
import com.neko.service.model.PlaceDetail;
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.util.MapperUtil;

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
	// Iterable<com.neko.entity.PlaceEntity> places = placeRepo.findAll();
	// for (com.neko.entity.PlaceEntity place : places)
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
	// private Place toPlaceModel(com.neko.entity.PlaceEntity place) {
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
	// com.neko.entity.PlaceEntity placeEntity =
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
	// private com.neko.entity.PlaceEntity toPlaceEntity(Place placeModel) {
	// com.neko.entity.PlaceEntity placeEntity = new
	// com.neko.entity.PlaceEntity();
	// placeEntity.setLat(placeModel.getGeometry().getLocation().getLat());
	// placeEntity.setLng(placeModel.getGeometry().getLocation().getLng());
	// placeEntity.setName(placeModel.getName());
	// placeEntity.setPlaceId(placeModel.getPlaceId());
	// placeEntity.setVicinty(placeModel.getVicinity());
	// return placeEntity;
	//
	// }
	//
	// private void toPhotoEntity(com.neko.entity.PlaceEntity place,
	// List<com.neko.service.model.Photo> list) {
	// for (com.neko.service.model.Photo photoModel : list) {
	// PhotoEntity PhotoEntity = new PhotoEntity();
	// PhotoEntity.setPhotoReference(photoModel.getPhotoReference());
	// place.addPhoto(PhotoEntity);
	// }
	// }
	//
	// private void toReviewEntity(List<com.neko.service.model.Review>
	// seviewModels, com.neko.entity.PlaceEntity placeEntity) {
	// for (com.neko.service.model.Review seviewModel : seviewModels) {
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
