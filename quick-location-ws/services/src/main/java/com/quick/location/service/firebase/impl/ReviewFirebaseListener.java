package com.quick.location.service.firebase.impl;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.PlaceEntity;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.ReviewFirebase;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.service.ReviewServiceApi;
import com.quick.location.service.util.MapperUtil;
import com.quick.location.util.QuickLocationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewFirebaseListener {

    @Autowired
    PlaceEntityRepo placeEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    ReviewServiceApi reviewService;

    @PostConstruct
    @Transactional
    public void reviewListener() {
        DatabaseReference ref = firebasePlaceService
            .getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REVIEW);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inserto el elemendo ");
                for (DataSnapshot dataSnapshotItem : dataSnapshot.getChildren()) {
                    insertarPlaceBD(dataSnapshotItem);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se cambio el elemendo ");
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
        ReviewEntity entity = QuickLocationUtil.toData(inData, ReviewEntity.class);
        PlaceEntity place = new PlaceEntity();
        place.setPlaceId(dataSnapshot.getKey());
        entity.setPlace(place);
        firebasePlaceService
            .getDatabaseReference(
                QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REVIEW)
            .child(place.getPlaceId()).child(dataSnapshot.getKey())
            .removeValue();
        reviewService.save(entity);
        ReviewFirebase reviewFirebase = MapperUtil.mapBean(entity,
            ReviewFirebase.class);
        firebasePlaceService
            .getDatabaseReference(
                QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REVIEW)
            .child(reviewFirebase.getPlaceId()).push().setValue(reviewFirebase);
        log.info("Se inserto el elemendo ");
    }
}
