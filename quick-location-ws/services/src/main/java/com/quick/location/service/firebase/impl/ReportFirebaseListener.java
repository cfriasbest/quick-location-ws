package com.quick.location.service.firebase.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.PlaceEntity;
import com.quick.location.entity.ReportEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.ImprovementInformation;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.ReportFirebase;
import com.quick.location.model.Schedule;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.service.ReportServiceApi;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author friasPinzon
 *
 */
@Slf4j
@Service
public class ReportFirebaseListener {

	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Autowired
	FirebasePlaceService firebasePlaceService;

	@Autowired
	ReportServiceApi reportService;
	
	 @Autowired
	 PlaceFirebaseListener placeFirebaseListener;

	@PostConstruct
	@Transactional
	public void addReportListener() {

		DatabaseReference ref = firebasePlaceService
		        .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REPORT);

		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				log.info("Se procede a insertar el reporte");
				for (DataSnapshot dataSnapshotItem : dataSnapshot.getChildren()) {
					insertarPlaceBD(dataSnapshotItem);
				}
				log.info("Se inserto correctamente el elemendo ");

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
				log.info("Se cambio el elemendo 1");
			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				log.info("Se removio el elemendo ");
			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
				log.info("Se movio el elemendo ");
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				log.info("Se Cancelo el elemendo ");
			}
		});
	}

	private void insertarPlaceBD(DataSnapshot dataSnapshot) {
		ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
		toDataEntity(inData,dataSnapshot.getKey());
		

		log.info("Se inserto el elemendo ");
	}

	public void toDataEntity(ImprovementRequest inData,String key) {
		ReportEntity palceDetail = null;

		for (ImprovementInformation dataInfo : inData.getInformations()) {
			palceDetail = new ReportEntity();

			palceDetail.setAuthor(inData.getAuthor());

			if ("location".equals(dataInfo.getInformationTag())) {

				palceDetail.setField(dataInfo.getInformationTag());
				palceDetail.setFieldHuman("Locacion");
				palceDetail.setValue(dataInfo.getInformationContent());
				PlaceEntity place = new PlaceEntity();
				place.setPlaceId(inData.getPlaceId());
				palceDetail.setPlace(place);

			} else if ("address".equals(dataInfo.getInformationTag())) {

				palceDetail.setField(dataInfo.getInformationTag());
				palceDetail.setFieldHuman("Direccion");
				palceDetail.setValue(dataInfo.getInformationContent());
				PlaceEntity place = new PlaceEntity();
				place.setPlaceId(inData.getPlaceId());
				palceDetail.setPlace(place);

			} else if ("telephone".equals(dataInfo.getInformationTag())) {

				palceDetail.setField(dataInfo.getInformationTag());
				palceDetail.setFieldHuman("Telefono");
				palceDetail.setValue(dataInfo.getInformationContent());
				PlaceEntity place = new PlaceEntity();
				place.setPlaceId(inData.getPlaceId());
				palceDetail.setPlace(place);

			} else if ("schedule".equals(dataInfo.getInformationTag())) {
				StringBuilder stb = new StringBuilder("[");
				for (Schedule schedule : dataInfo.getSchedules()) {
					stb.append(schedule.getDayName());
					stb.append(": ").append(schedule.getOpenFrom()).append(" - ")
					        .append(schedule.getClosedFrom()).append(", ");

				}
				stb.append("]");

				palceDetail.setField(dataInfo.getInformationTag());
				palceDetail.setFieldHuman("Horarios");

				palceDetail.setValue(stb.toString());
				PlaceEntity place = new PlaceEntity();
				place.setPlaceId(inData.getPlaceId());
				palceDetail.setPlace(place);

			}
			try {
				palceDetail.setDone(false);
				reportService.save(palceDetail);
				// palceDetail
				List<ReportFirebase> repotsFirebase = reportService
				        .getByPlacePlaceId(inData.getPlaceId());
				placeFirebaseListener.updatePlace(inData.getPlaceId(), false, true);
				firebasePlaceService
				        .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REPORT)
				        .child(inData.getPlaceId()).setValue(repotsFirebase);
				
				firebasePlaceService
				        .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REPORT)
				        .child(inData.getPlaceId()).child(key).removeValue();
				 
				 
			} catch (Exception e) {
				log.error("Eror en ", e);
			}
		}

	}

}
