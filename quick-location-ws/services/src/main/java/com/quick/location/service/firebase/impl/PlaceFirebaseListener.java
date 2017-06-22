package com.quick.location.service.firebase.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.OpeninghourEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.PlaceDetail;
import com.quick.location.model.PlaceDetailFirebase;
import com.quick.location.repo.OpeninghourEntityRepo;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.util.MapperUtil;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlaceFirebaseListener {

    @Autowired
    PlaceEntityRepo placeEntityRepo;
    
    @Autowired
    OpeninghourEntityRepo openinghourEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    PlaceDetailsServiceApi placeDetailsService;

    @PostConstruct
    @Transactional
    public void placeListener() {
        DatabaseReference ref = firebasePlaceService
            .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_NEW_DATA);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
            	log.info("Se inicia la insercion del lugar");
                insertarPlaceBD(dataSnapshot);
                log.info("Se Finaliza  la insercion del lugar");

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
                PlaceDetail place = QuickLocationUtil.toData(inData, PlaceDetail.class);
                log.info("Se cambio el elemendo ", place.toString());
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
    	PlaceDetail place = dataSnapshot.getValue(PlaceDetail.class);
    	place.setPlaceId(dataSnapshot.getKey());
        placeDetailsService.savePlaceDetails(place);
//        firebasePlaceService
//            .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_NEW_DATA)
//            .child(place.getPlaceId()).removeValue();
        if(null!=place.getOpeningHours())
        {
        	OpeninghourEntity openini= MapperUtil.mapBean(place.getOpeningHours(), OpeninghourEntity.class);
        	openini.setPlaceId(place.getPlaceId());
        	openinghourEntityRepo.save(openini);
        }
        PlaceDetailFirebase placeDetailFirebase = MapperUtil.mapBean(place,
            PlaceDetailFirebase.class);
        firebasePlaceService
            .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_DATA)
            .child(place.getPlaceId()).setValue(placeDetailFirebase);
        log.info("Se inserto el elemendo ");
    }
}
