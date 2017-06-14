package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.PlacedetailEntity;

@Repository
public interface PlacedetailEntityRepo extends CrudRepository<PlacedetailEntity, Integer> {

	public PlacedetailEntity findPlacedetailEntityByPlaceId(String placeId);
}