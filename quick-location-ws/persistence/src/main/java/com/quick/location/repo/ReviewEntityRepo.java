package com.quick.location.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReviewEntity;

@Repository
public interface ReviewEntityRepo extends CrudRepository<ReviewEntity, Integer> {
	
	@Query(value = "SELECT * FROM review WHERE place_id = :placeId and done=false", nativeQuery = true)
	public List<ReviewEntity> getLastReviewByPlace(@Param("placeId") String placeId);

	@Query(value = "SELECT * FROM review where done=false order by idreviews desc LIMIT 10", nativeQuery = true)
    public List<ReviewEntity> getLastReview();
	
//	public List<>
}