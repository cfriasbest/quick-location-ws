package com.quick.location.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReviewEntity;

@Repository
public interface ReviewEntityRepo extends CrudRepository<ReviewEntity, Integer> {
	
	@Query(value = "SELECT * FROM review WHERE place_id = :placeId and done=0", nativeQuery = true)
	public List<ReviewEntity> getLastReviewByPlace(@Param("placeId") String placeId);

	@Query(value = "SELECT * FROM review where done=0 order by idreviews desc LIMIT 10", nativeQuery = true)
    public List<ReviewEntity> getLastReview();
	
	public ReviewEntity getReviewByIdreviews(int indReviews);
	
	@Query(value = "SELECT * FROM review where done=0 and DATE(date) >= STR_TO_DATE(:desde,'%Y-%m-%d') and DATE(date) <= STR_TO_DATE(:hasta,'%Y-%m-%d') "
	        + "order by idreviews desc LIMIT 10", nativeQuery = true)
    public List<ReviewEntity> getLastReviewByDate(@Param("desde") String desde,@Param("hasta") String hasta);
	
	
//	public List<>
}