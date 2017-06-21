//package com.quick.location.service.impl;
//
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.quick.location.entity.PlaceEntity;
//import com.quick.location.firebase.config.FirebasePlaceService;
//import com.quick.location.model.PlaceDetail;
//import com.quick.location.repo.PlaceEntityRepo;
//import com.quick.location.service.util.MapperUtil;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class ActualizacionTread extends Runa {
//
//	@Autowired
//	PlaceEntityRepo placeEntityRepo;
//
//	@Autowired
//	FirebasePlaceService firebasePlaceService;
//
//	private long initialTime;
//
//	// public ActualizacionTread(PlaceEntityRepo placeEntityRepo,
//	// FirebasePlaceService firebasePlaceService) {
//	// super();
//	// this.placeEntityRepo = placeEntityRepo;
//	// this.firebasePlaceService = firebasePlaceService;
//	// }
//
////	@PostConstruct
//	@Override
//	public void run() {
//
//		for (;;) {
//			this.esperarXsegundos(7);
//			System.out.println("Procesado la actualizacion Inicio->Tiempo: "
//			        + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
//			updateFirebaseServer();
//			System.out.println("Procesado la actualizacion Fin->Tiempo: "
//			        + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
//		}
//
//	}
//
//	private void updateFirebaseServer() {
//		log.info("Se Actualizaran los datos del servidor");
//		List<PlaceEntity> placesEntity = (List<PlaceEntity>) placeEntityRepo.findAll();
//		List<PlaceDetail> places = MapperUtil.mapAsList(placesEntity, PlaceDetail.class);
//		firebasePlaceService.setPlaceListOnFirebase(places);
//		log.info("Se Actualizaron los datos del servidor");
//	}
//
//	private void esperarXsegundos(int segundos) {
//		try {
//			Thread.sleep(segundos * 1000);
//		} catch (InterruptedException ex) {
//			Thread.currentThread().interrupt();
//		}
//	}
//
//}
