package com.quick.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.location.entity.ReviewEntity;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.service.ReviewServiceApi;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class ReviewService implements ReviewServiceApi {
    
    @Autowired
    ReviewEntityRepo reviewEntityRepo;

    
//    @PostConstruct
    @Transactional
    public void init() {
        
    }
    
    
    @Override
    public void save(ReviewEntity reviewEntity) {
      reviewEntityRepo.save(reviewEntity);
        
    }


	@Override
	public List<ReviewEntity> getLastReviewByPlace(String placeId) {
		
		return reviewEntityRepo.getLastReviewByPlace(placeId);
	}


    @Override
    public List<ReviewEntity> getLastReview() {
        return reviewEntityRepo.getLastReview();
    }

}
