package com.quick.location.service;

import java.util.List;

import com.quick.location.entity.ReviewEntity;

public interface ReviewServiceApi {

    public void save(ReviewEntity reviewEntity);
    
    public List<ReviewEntity> getLastReviewByPlace(String placeId);

    public List<ReviewEntity> getLastReview();
}
