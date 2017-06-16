package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.PlaceEntity;

@Repository
public interface PlaceEntityRepo extends CrudRepository<PlaceEntity, String> {
}