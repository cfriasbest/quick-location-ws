package com.quick.location.service.firebase.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.ReviewFirebase;
import com.quick.location.model.TopReviewFirebase;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.service.ReviewServiceApi;
import com.quick.location.service.util.MapperUtil;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewFirebaseListener {

    @Autowired
    PlaceEntityRepo placeEntityRepo;
    
    @Autowired
    ReviewEntityRepo reviewEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    ReviewServiceApi reviewService;

    @Autowired
    PlaceFirebaseListener placeFirebaseListener;

    @PostConstruct
    @Transactional
    public void reviewListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REVIEW);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inserto el elemendo onChildAdded");
                for (DataSnapshot dataSnapshotItem : dataSnapshot.getChildren()) {
                    insertarPlaceBD(dataSnapshotItem);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se cambio el elemendo onChildChanged");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                log.info("Se removio el elemendo onChildRemoved");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("onChildMoved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log.info("Se Cancelo el elemendo onCancelled");
            }
        });

    }
    
    @PostConstruct
    @Transactional
    public void reviewUpdateListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REVIEW);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inserto el elemendo ");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                for (DataSnapshot dataSnapshotItem : dataSnapshot.getChildren()) {
                    insertarUpdatePlaceBD(dataSnapshotItem,dataSnapshot.getKey() );
                }
                log.info("Se cambio el elemendo ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                ReviewFirebase entity =dataSnapshot.getValue(ReviewFirebase.class);
                try {
                ReviewEntity entitys =reviewEntityRepo.findOne(entity.getIdreviews());
                entitys.setDone(true);
                reviewEntityRepo.save(entitys);
                List<ReviewEntity> lastReview = reviewService.getLastReviewByPlace(entity.getPlaceId());
                float reviewProm =0;
                for(ReviewEntity rev :lastReview)
                {
                    reviewProm+=NumberUtils.toFloat(rev.getRating());
                }
                reviewProm=reviewProm/lastReview.size();
                placeFirebaseListener.updatePlace(entity.getPlaceId(), true, false,lastReview.size(),reviewProm);
                log.info("Se removio el elemendo ");
                } catch (Exception e) {
                    List<ReviewEntity> lastReview = reviewService.getLastReviewByPlace(entity.getPlaceId());
                    float reviewProm =0;
                    for(ReviewEntity rev :lastReview)
                    {
                        reviewProm+=NumberUtils.toFloat(rev.getRating());
                    }
                    reviewProm=reviewProm/lastReview.size();
                    placeFirebaseListener.updatePlace(entity.getPlaceId(), true, false,lastReview.size(),reviewProm);
                    log.error("Mensage error", e);
                }
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

    private void insertarUpdatePlaceBD(DataSnapshot dataSnapshot,String key) {
       
        ReviewFirebase entity =dataSnapshot.getValue(ReviewFirebase.class);
        entity.setPlaceId(key);
        if(entity.isDone())
        {
            ReviewEntity reviewEntity = MapperUtil.mapBean(entity, ReviewEntity.class);
            reviewEntity.setDone(false);
            reviewService.save(reviewEntity);
        }
//        if(entity.isRemove())
//        {
//            ReviewEntity reviewEntity = MapperUtil.mapBean(entity, ReviewEntity.class);
//            reviewEntity.setDone(true);
//            reviewService.save(reviewEntity);
//            
//            List<ReviewEntity> lastReview = reviewService.getLastReviewByPlace(entity.getPlaceId());
//            float reviewProm =0;
//            for(ReviewEntity rev :lastReview)
//            {
//                reviewProm+=NumberUtils.toFloat(rev.getRating());
//            }
//            reviewProm=reviewProm/lastReview.size();
//            placeFirebaseListener.updatePlace(entity.getPlaceId(), true, false,lastReview.size(),reviewProm);
//            firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_TOP_10_LAST_REVIEW, "", MapperUtil.mapAsList(lastReview, TopReviewFirebase.class));
//            
//        }

        log.info("Se inserto el elemendo ");
    }
    
    private void insertarPlaceBD(DataSnapshot dataSnapshot) {
        ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
        ReviewFirebase entity = QuickLocationUtil.toData(inData, ReviewFirebase.class);
        entity.setAuthorName(inData.getAuthor());

        ReviewEntity reviewEntity = MapperUtil.mapBean(entity, ReviewEntity.class);
        reviewEntity.setDone(false);
        try {
            reviewService.save(reviewEntity);
            ReviewFirebase reviewFirebase = MapperUtil.mapBean(reviewEntity, ReviewFirebase.class);
           
//            List<ReviewEntity> lastReview = reviewService.getLastReviewByPlace(inData.getPlaceId());
            List<ReviewEntity> lastReview = reviewService.getLastReviewByPlace(inData.getPlaceId());
            float reviewProm =0;
            for(ReviewEntity rev :lastReview)
            {
                reviewProm+=NumberUtils.toFloat(rev.getRating());
            }
            reviewProm=reviewProm/lastReview.size();
            placeFirebaseListener.updatePlace(inData.getPlaceId(), true, false,lastReview.size(),reviewProm);
            firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_TOP_10_LAST_REVIEW, "", MapperUtil.mapAsList(lastReview, TopReviewFirebase.class));

            firebasePlaceService.objectPushToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REVIEW, reviewFirebase.getPlaceId(), reviewFirebase);

            // firebasePlaceService.removeObjectToFirebase(
            // QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REVIEW,
            // inData.getPlaceId() + "/" + dataSnapshot.getKey());

        } catch (Exception e) {
            log.error("Mensage error", e);
        }

        log.info("Se inserto el elemendo ");
    }
}
