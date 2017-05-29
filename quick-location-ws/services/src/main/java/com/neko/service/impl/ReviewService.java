package com.neko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neko.entity.ReviewEntity;
import com.neko.repo.ReviewRepo;
import com.neko.service.ReviewServiceApi;
import com.neko.service.model.Place;
import com.neko.service.model.Review;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class ReviewService implements ReviewServiceApi {

	@Autowired
	ReviewRepo reviewRepo;

	@Override
	public void saveReview(Review review) {
		ReviewEntity reviewEntity = new ReviewEntity();
		// reviewEntity.setAuthorUrl(review.getAuthorUrl());
		// reviewEntity.setLanguage(review.getLanguage());
		com.neko.entity.Place place = new com.neko.entity.Place();
		place.setPlaceId("ChIJe3IIQPqorI8R2kRfwnn9FBwyyyy");
		reviewEntity.setPlace(place);
		// reviewEntity.setProfilePhotoUrl(review.getProfilePhotoUrl());
		reviewEntity.setText(review.getText());
		// reviewEntity.setAuthorName(review.getAuthorName());
		// reviewEntity.setAuthorName(review.getAuthorName());
		reviewRepo.save(reviewEntity);
	}

	@Override
	public com.neko.service.model.Review getReview(Place place) {
		// TODO Auto-generated method stub
		return null;
	}

}
