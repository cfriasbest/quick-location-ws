package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.SugestDataEntity;

@Repository
public interface SugestDataEntityRepo extends CrudRepository<SugestDataEntity, Integer> {}