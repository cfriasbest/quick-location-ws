package com.neko.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neko.entity.Photo;
import com.neko.entity.Placedetail;
import com.neko.entity.ReviewEntity;
import com.neko.repo.PhotoRepo;
import com.neko.repo.PlaceDetailRepo;
import com.neko.repo.PlaceRepo;
import com.neko.service.PlaceServiceApi;
import com.neko.service.model.Geometry;
import com.neko.service.model.Location;
import com.neko.service.model.Place;
import com.neko.service.model.PlaceDetail;
import com.neko.service.model.ResponseForPlaceDetails;
import com.neko.service.model.ResponseForPlaces;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class PlaseService implements PlaceServiceApi {

	@Autowired
	PlaceDetailRepo placeDetailRepoRepo;

	@Autowired
	PlaceRepo placeRepo;

	@Autowired
	PhotoRepo photoRepo;

	@Override
	public ResponseForPlaces getPlaces(String id) {
		ResponseForPlaces responseForPlaces = new ResponseForPlaces();
		responseForPlaces.setResults(new ArrayList<Place>());
		Iterable<com.neko.entity.Place> places = placeRepo.findAll();
		for (com.neko.entity.Place place : places)
			responseForPlaces.getResults().add(toPlaceModel(place));
		return responseForPlaces;
	}

	@Override
	public ResponseForPlaceDetails getPlaceDetail(String placeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePlace(Place place) {
		// TODO Auto-generated method stub

	}

	private Place toPlaceModel(com.neko.entity.Place place) {
		Place placeModel = new Place();
		Geometry geometry = new Geometry();
		Location location = new Location();
		location.setLat(place.getLat());
		 location.setLng(place.getLng());
		geometry.setLocation(location);
		placeModel.setGeometry(geometry);

		placeModel.setPlaceId(place.getPlaceId());
		placeModel.setName(place.getName());
		return placeModel;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePlaceDetails(PlaceDetail placeDetailModel) {

		com.neko.entity.Place placeEntity = toPlaceEntity(placeDetailModel);
		Placedetail placeDetailEntity = toPlaceDetailsEntity(placeDetailModel);
		placeDetailEntity.setPlace(placeEntity);
		placeEntity.setPlacedetail(placeDetailEntity);
		toPhotoEntity(placeEntity, placeDetailModel.getPhotos());
		toReviewEntity(placeDetailModel.getReviews(), placeEntity);

		placeRepo.save(placeEntity);

	}

	private Placedetail toPlaceDetailsEntity(PlaceDetail placeDetailModel) {
		Placedetail placeDetailEntity = new Placedetail();
		placeDetailEntity.setFormattedAddress(placeDetailModel.getFormattedAddress());
		placeDetailEntity.setFormattedPhoneNumber(placeDetailModel.getFormattedPhoneNumber());
		placeDetailEntity.setUrl(placeDetailModel.getUrl());
		placeDetailEntity.setWebsite(placeDetailModel.getWebsite());
		return placeDetailEntity;
	}

	private com.neko.entity.Place toPlaceEntity(Place placeModel) {
		com.neko.entity.Place placeEntity = new com.neko.entity.Place();
		placeEntity.setLat(placeModel.getGeometry().getLocation().getLat());
		placeEntity.setLng(placeModel.getGeometry().getLocation().getLng());
		placeEntity.setName(placeModel.getName());
		placeEntity.setPlaceId(placeModel.getPlaceId());
		placeEntity.setVicinty(placeModel.getVicinity());
		return placeEntity;

	}

	private void toPhotoEntity(com.neko.entity.Place place, List<com.neko.service.model.Photo> list) {
		for (com.neko.service.model.Photo photoModel : list) {
			Photo PhotoEntity = new Photo();
			PhotoEntity.setPhotoReference(photoModel.getPhotoReference());
			place.addPhoto(PhotoEntity);
		}
	}

	private void toReviewEntity(List<com.neko.service.model.Review> seviewModels, com.neko.entity.Place placeEntity) {
		for (com.neko.service.model.Review seviewModel : seviewModels) {
			ReviewEntity review = new ReviewEntity();
			review.setAuthorName(seviewModel.getAuthorName());
			// review.setAuthorUrl(seviewModel.getAuthorName());
			review.setText(seviewModel.getText());
			// review.setAuthorName(seviewModel.getAuthorName());
			// review.setAuthorName(seviewModel.getAuthorName());
			// review.setAuthorName(seviewModel.getAuthorName());
			placeEntity.addReviewEntity(review);

		}

	}

}
