package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.Statistic;

@Repository
public interface StatictsEntityRepo extends CrudRepository<Statistic, Integer> {
	
	
}