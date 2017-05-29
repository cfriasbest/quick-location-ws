package com.neko.service;

import com.neko.service.model.Place;
import com.neko.service.model.Review;

/**
 * 
 * @author cfriasb
 *
 */
public interface ReviewServiceApi {

	public void saveReview(Review review);

	public Review getReview(Place place);

}
