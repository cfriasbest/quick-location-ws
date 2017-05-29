package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.ReviewEntity;

/**
 * 
 * @author cfriasb
 *
 */
@Repository
public interface ReviewRepo extends CrudRepository<ReviewEntity, String> {

}
