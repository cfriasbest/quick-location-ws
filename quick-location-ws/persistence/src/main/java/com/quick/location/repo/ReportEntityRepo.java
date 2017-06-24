package com.quick.location.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReportEntity;

@Repository
public interface ReportEntityRepo extends CrudRepository<ReportEntity, Integer> {
	
	public List<ReportEntity> getByPlacePlaceId(String placeID);
}