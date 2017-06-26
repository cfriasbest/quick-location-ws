package com.quick.location.service.impl;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.location.entity.PlaceEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.PlaceDetailFirebase;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cfriasb
 *
 */
@Service
@Slf4j

public class PlaceDetailsService implements PlaceDetailsServiceApi {


	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Autowired
	FirebasePlaceService firebasePlaceService;

	@Autowired
	ReviewEntityRepo reviewEntityRepo;


	@PostConstruct
	public void initData() {

//		updateFirebaseServer();
	}

	@Override
	@Transactional
	public void savePlaceDetails(PlaceDetailFirebase placeDetail) {
		log.info("Se insertara el place y sus detalles en el servidor");
		DozerBeanMapper mapper = new DozerBeanMapper();
		PlaceEntity placeEntity = mapper.map(placeDetail, PlaceEntity.class);
//		placeEntity.autoSetThis();
		placeEntityRepo.save(placeEntity);
		log.info("Se Finaliza la insecion ");
	}

//	private void updateFirebaseServer() {
//		log.info("Se Actualizaran los datos del servidor");
//		List<PlaceEntity> placesEntity = (List<PlaceEntity>) placeEntityRepo.findAll();
//		List<PlaceDetailFirebase> places = MapperUtil.mapAsList(placesEntity,
//		        PlaceDetailFirebase.class);
//		firebasePlaceService.setPlaceListOnFirebase(places);
//		List<ReviewEntity> reviewEntity = (List<ReviewEntity>) reviewEntityRepo.findAll();
//		List<ReviewFirebase> reviews = MapperUtil.mapAsList(reviewEntity, ReviewFirebase.class);
//		firebasePlaceService.setReviewsListOnFirebase(reviews);
//		log.info("Se Actualizaron los datos del servidor");
//	}

//	@Transactional
//	public void updatePlaceListener() {
//		DatabaseReference ref = firebasePlaceService
//		        .getDatabaseReference("server/saving-data/fireblog/updated");
//
//		ref.addChildEventListener(new ChildEventListener() {
//			@Override
//			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//				SugestData sugerencia = dataSnapshot.getValue(SugestData.class);
//				sugerencia.setIdSugestData(dataSnapshot.getKey());
//				sugerencia.setState("SD001");
////				sugestDataEntityRepo.save(MapperUtil.mapBean(sugerencia, SugestDataEntity.class));
//				log.info("Se inserto el elemendo ");
//
//			}
//
//			@Override
//			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
//				SugestData sugerencia = dataSnapshot.getValue(SugestData.class);
//				sugerencia.setIdSugestData(dataSnapshot.getKey());
//				sugerencia.setState("SD001");
////				sugestDataEntityRepo.save(MapperUtil.mapBean(sugerencia, SugestDataEntity.class));
//				log.info("Se cambio el elemendo ");
//
////				pruebaActualizacion(sugerencia);
//
//			}
//
//			@Override
//			public void onChildRemoved(DataSnapshot dataSnapshot) {
//				SugestData sugerencia = dataSnapshot.getValue(SugestData.class);
//				sugerencia.setIdSugestData(dataSnapshot.getKey());
////				sugestDataEntityRepo.delete(sugerencia.getIdSugestData());
//				log.info("Se removio el elemendo ");
//			}
//
//			@Override
//			public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
//				log.info("Se movio el elemendo ");
//			}
//
//			@Override
//			public void onCancelled(DatabaseError databaseError) {
//				log.info("Se Cancelo el elemendo ");
//			}
//		});
//	}

	

	

//	@Override
//	@Transactional
//	public void updatePlaceDetails(SugestData sugestData) {
//		log.info("Se ingresa al metodo de Actualizacion de datos ");
//
//		// PlaceEntity entity =
//		// placeEntityRepo.findOne(sugestData.getPlaceId());
//		// PlacedetailEntity placeDetail = entity.getPlacedetail();
//		// if (null != sugestData.getDirection()) {
//		// placeDetail.setFormattedAddress(sugestData.getDirection());
//		// }
//		// if (null != sugestData.getPhone()) {
//		// placeDetail.setFormattedPhoneNumber(sugestData.getPhone());
//		// }
//		//
//		// placedetailEntityRepo.save(placeDetail);
//
//		log.info("Se  Actualizo los datos ");
//
//	}

	

}
