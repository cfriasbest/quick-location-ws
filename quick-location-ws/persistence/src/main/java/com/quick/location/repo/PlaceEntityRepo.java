package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.PlaceEntity;

@Repository
public interface PlaceEntityRepo extends CrudRepository<PlaceEntity, String> {
}