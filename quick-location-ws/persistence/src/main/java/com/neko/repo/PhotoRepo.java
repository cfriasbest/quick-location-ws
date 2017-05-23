package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.Photo;

@Repository
public interface PhotoRepo extends CrudRepository<Photo, String>
{

}


