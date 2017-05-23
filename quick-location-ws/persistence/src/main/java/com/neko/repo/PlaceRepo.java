package com.neko.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neko.entity.Place;

@Repository
public interface PlaceRepo extends CrudRepository<Place, String>
{

}


