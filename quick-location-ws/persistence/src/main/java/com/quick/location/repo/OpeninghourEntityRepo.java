package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.OpeninghourEntity;

@Repository
public interface OpeninghourEntityRepo extends CrudRepository<OpeninghourEntity, String> {
}