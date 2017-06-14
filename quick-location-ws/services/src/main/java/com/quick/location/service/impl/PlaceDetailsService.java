package com.quick.location.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.location.entity.PlaceEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.PlaceDetail;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.PlacedetailEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.util.MapperUtil;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class PlaceDetailsService implements PlaceDetailsServiceApi {

	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Autowired
	PlacedetailEntityRepo placedetailEntityRepo;

	@Autowired
	FirebasePlaceService firebasePlaceService;

	@Override
	@Transactional
	public void savePlaceDetails(PlaceDetail placeDetail) {
		DozerBeanMapper mapper = new DozerBeanMapper();

		PlaceEntity placeEntity = mapper.map(placeDetail, PlaceEntity.class);
		placeEntity.autoSetThis();
		placeEntityRepo.save(placeEntity);
		updateServer();
	}

	private void updateServer() {
		List<PlaceEntity> placesEntity = (List<PlaceEntity>) placeEntityRepo.findAll();
		List<PlaceDetail> places = MapperUtil.mapAsList(placesEntity, PlaceDetail.class);
		firebasePlaceService.setPlaceListOnFirebase(places);
	}
}
