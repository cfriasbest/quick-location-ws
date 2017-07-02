package com.quick.location.service.firebase.impl;

import java.util.Arrays;
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
import com.quick.location.entity.OpeninghourEntity;
import com.quick.location.entity.PlaceEntity;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.OpeningHours;
import com.quick.location.model.firebase.PlaceDetailFirebase;
import com.quick.location.model.firebase.ReportFirebase;
import com.quick.location.repo.OpeninghourEntityRepo;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.ReportServiceApi;
import com.quick.location.service.ReviewServiceApi;
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
    ReportServiceApi reportService;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    PlaceDetailsServiceApi placeDetailsService;

    @Autowired
    ReviewServiceApi reviewService;

    @PostConstruct
    @Transactional
    public void placeListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_NEW_DATA);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inicia la insercion del lugar");
                insertarPlaceBD(dataSnapshot);
                log.info("Se Finaliza  la insercion del lugar");

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se Modifica el lugar en New No hago nada ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                log.info("Se removio el elemendo New No hago nada ");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se movio el elemendo New No hago nada ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log.info("Se Cancelo el elemendo New No hago nada ");
            }
        });

    }

    @PostConstruct
    @Transactional
    public void updatePlaceListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_DATA);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inicia la actualizacion del lugar no hago nada");

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {

                log.info("Se inicia la insercion del lugar");
                PlaceDetailFirebase placeDetailFirebase = dataSnapshot.getValue(PlaceDetailFirebase.class);
                if (placeDetailFirebase.getUpdateAcept() > 0) {
                    placeDetailFirebase.setPlaceId(dataSnapshot.getKey());
                    placeDetailFirebase.setUpdatesCount(placeDetailFirebase.getUpdatesCount() - placeDetailFirebase.getUpdateAcept());
                    placeDetailsService.savePlaceDetails(placeDetailFirebase);
                    placeDetailFirebase.setUpdateAcept(0);
                    if (null != placeDetailFirebase.getOpeningHours()) {
                        OpeninghourEntity openini = MapperUtil.mapBean(placeDetailFirebase.getOpeningHours(), OpeninghourEntity.class);
                        openini.setPlaceId(placeDetailFirebase.getPlaceId());
                        openinghourEntityRepo.save(openini);
                    }
                    firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_DATA, placeDetailFirebase.getPlaceId(), placeDetailFirebase);
                }
                log.info("Se cambio el elemendo ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                log.info("Se removio el elemendo la actualizacion del lugar no hago nada");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se movio el elemendo la actualizacion del lugar no hago nada");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log.info("Se Cancelo el elemendo la actualizacion del lugar no hago nada");
            }
        });

    }

    private void insertarPlaceBD(DataSnapshot dataSnapshot) {
        PlaceDetailFirebase place = dataSnapshot.getValue(PlaceDetailFirebase.class);
        place.setPlaceId(dataSnapshot.getKey());
        placeDetailsService.savePlaceDetails(place);

        if (null != place.getOpeningHours()) {
            OpeninghourEntity openini = MapperUtil.mapBean(place.getOpeningHours(), OpeninghourEntity.class);
            openini.setPlaceId(place.getPlaceId());
            openinghourEntityRepo.save(openini);
        }
        PlaceDetailFirebase placeDetailFirebase = MapperUtil.mapBean(place, PlaceDetailFirebase.class);

        List<ReviewEntity> lastReview = reviewService.getLastReviewByPlace(placeDetailFirebase.getPlaceId());
        float reviewProm = 0;
        for (ReviewEntity rev : lastReview) {
            reviewProm += NumberUtils.toFloat(rev.getRating());
        }
        reviewProm = reviewProm / lastReview.size();
        updatePlace(placeDetailFirebase.getPlaceId(), true, false, lastReview.size(), reviewProm);
        if (!lastReview.isEmpty()) {
            placeDetailFirebase.setReviewsCount(lastReview.size());
            placeDetailFirebase.setRating(reviewProm);
        }
        List<ReportFirebase> repotsFirebase = reportService.getByPlacePlaceId(placeDetailFirebase.getPlaceId());
        if (!lastReview.isEmpty()) {
            placeDetailFirebase.setUpdatesCount(repotsFirebase.size());
        }
        updatePlace(placeDetailFirebase.getPlaceId(), false, true, repotsFirebase.size(), 0);
        // TODO: VALIDAR FECHA ANTES DE SUBIR
        firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_DATA, place.getPlaceId(), placeDetailFirebase);

        firebasePlaceService.removeObjectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_NEW_DATA, place.getPlaceId());
        log.info("Se inserto el elemendo ");
    }

    public void updatePlace(String placeId, boolean isReview, boolean isUpdate, int cant, float reviewProm) {

        PlaceEntity entity = placeEntityRepo.findOne(placeId);
        if (null != entity) {
            if (isReview) {
                entity.setReviewsCount(cant);
                entity.setRating(reviewProm);
            }
            if (isUpdate) {
                entity.setUpdatesCount(cant);
            }
            placeEntityRepo.save(entity);
            PlaceDetailFirebase placeDetailFirebase = MapperUtil.mapBean(entity, PlaceDetailFirebase.class);

            OpeninghourEntity open = openinghourEntityRepo.findOne(entity.getPlaceId());
            if (null != open) {
                OpeningHours openHours = new OpeningHours();
                openHours.setWeekdayText(Arrays.asList(open.getWeekdayText().replace("[", "").replace("]", "").trim().split(",")));
                placeDetailFirebase.setOpeningHours(openHours);
            }
            // TODO: VALIDAR FECHA ANTES DE SUBIR
            firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_DATA, entity.getPlaceId(), placeDetailFirebase);
        }
    }

    public void updateRPlace(String placeId, boolean isReview, boolean isUpdate, int cant, float reviewProm) {

        PlaceEntity entity = placeEntityRepo.findOne(placeId);

        if (isReview) {
            entity.setReviewsCount(cant);
            entity.setRating(reviewProm);
        }
        if (isUpdate) {
            entity.setUpdatesCount(cant);
        }
        placeEntityRepo.save(entity);
        PlaceDetailFirebase placeDetailFirebase = MapperUtil.mapBean(entity, PlaceDetailFirebase.class);

        OpeninghourEntity open = openinghourEntityRepo.findOne(entity.getPlaceId());
        if (null != open) {
            OpeningHours openHours = new OpeningHours();
            openHours.setWeekdayText(Arrays.asList(open.getWeekdayText().replace("[", "").replace("]", "").trim().split(",")));
            placeDetailFirebase.setOpeningHours(openHours);
        }

        // firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_DATA,
        // entity.getPlaceId(), placeDetailFirebase);

    }
}
