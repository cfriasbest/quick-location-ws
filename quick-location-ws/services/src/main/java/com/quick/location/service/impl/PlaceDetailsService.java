package com.quick.location.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.PlaceEntity;
import com.quick.location.entity.SugestDataEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.PlaceDetail;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.PlacedetailEntityRepo;
import com.quick.location.repo.SugestDataEntityRepo;
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

	@Autowired
	SugestDataEntityRepo sugestDataEntityRepo;

	@PostConstruct
	public void initData() {
		updateServer();
		updatePlaceListener();

	}

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

	@Transactional
	public void updatePlaceListener() {
		DatabaseReference ref = firebasePlaceService
		        .getDatabaseReference("server/saving-data/fireblog/updated");

		ref.addChildEventListener(new ChildEventListener() {
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				SugestDataEntity sugerencia = dataSnapshot.getValue(SugestDataEntity.class);
				sugerencia.setIdSugestData(dataSnapshot.getKey());
				sugerencia.setState("SD001");
				sugestDataEntityRepo.save(sugerencia);

			}

			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
				SugestDataEntity sugerencia = dataSnapshot.getValue(SugestDataEntity.class);
				sugerencia.setIdSugestData(dataSnapshot.getKey());
				sugerencia.setState("SD001");
				sugestDataEntityRepo.save(sugerencia);

			}

			public void onChildRemoved(DataSnapshot dataSnapshot) {
				SugestDataEntity sugerencia = dataSnapshot.getValue(SugestDataEntity.class);
				sugerencia.setIdSugestData(dataSnapshot.getKey());
				sugestDataEntityRepo.delete(sugerencia.getIdSugestData());
			}

			public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
			}

			public void onCancelled(DatabaseError databaseError) {
			}
		});
	}

}
