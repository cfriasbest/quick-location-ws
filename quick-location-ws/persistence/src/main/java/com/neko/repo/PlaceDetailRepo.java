package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.Placedetail;

@Repository
public interface PlaceDetailRepo extends CrudRepository<Placedetail, String>
{

	public Placedetail findByPlacePlaceId(String id);
	
}


