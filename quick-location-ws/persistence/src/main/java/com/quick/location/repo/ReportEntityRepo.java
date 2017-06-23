package com.quick.location.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReportEntity;

@Repository
public interface ReportEntityRepo extends CrudRepository<ReportEntity, String> {}