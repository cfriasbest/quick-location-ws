package com.neko.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.PhotoEntity;
import com.neko.entity.PlaceEntity;

@Repository
public interface PhotoEntityRepo extends CrudRepository<PhotoEntity, String > {
	
//	public List<PhotoEntity> findPhotoEntitiesByPlaceAndPhotoReference(PlaceEntity place,String photoReference);
//	
//	
	
	public PhotoEntity findPhotoEntityByPhotoReferenceAndPlacePlaceId(String PhotoReference , String PlaceId );
	public List<PhotoEntity> findPhotoEntitiesByPlacePlaceId(String placeId);
}