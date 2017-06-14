package com.quick.location.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.PhotoEntity;

@Repository
public interface PhotoEntityRepo extends CrudRepository<PhotoEntity, String > {
	
//	public List<PhotoEntity> findPhotoEntitiesByPlaceAndPhotoReference(PlaceEntity place,String photoReference);
//	
//	
	
	public PhotoEntity findPhotoEntityByPhotoReferenceAndPlacePlaceId(String PhotoReference , String PlaceId );
	public List<PhotoEntity> findPhotoEntitiesByPlacePlaceId(String placeId);
}