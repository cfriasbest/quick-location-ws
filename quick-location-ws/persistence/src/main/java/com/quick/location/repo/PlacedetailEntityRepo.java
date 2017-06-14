package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.PlacedetailEntity;

@Repository
public interface PlacedetailEntityRepo extends CrudRepository<PlacedetailEntity, Integer> {

	public PlacedetailEntity findPlacedetailEntityByPlaceId(String placeId);
}