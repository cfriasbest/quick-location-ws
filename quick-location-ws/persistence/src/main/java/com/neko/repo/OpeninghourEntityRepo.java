package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.OpeninghourEntity;

@Repository
public interface OpeninghourEntityRepo extends CrudRepository<OpeninghourEntity, Integer> {
}