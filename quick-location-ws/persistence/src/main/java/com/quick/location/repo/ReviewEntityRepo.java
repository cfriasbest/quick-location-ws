package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReviewEntity;

@Repository
public interface ReviewEntityRepo extends CrudRepository<ReviewEntity, Integer> {}