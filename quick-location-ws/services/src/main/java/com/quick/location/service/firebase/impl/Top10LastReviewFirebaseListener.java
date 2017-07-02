package com.quick.location.service.firebase.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.firebase.ResponseFirebase;
import com.quick.location.model.firebase.TopReviewFirebase;
import com.quick.location.repo.OpeninghourEntityRepo;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.util.MapperUtil;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Top10LastReviewFirebaseListener {

    private static final String REQUEST = "request";
    @Autowired
    PlaceEntityRepo placeEntityRepo;
    @Autowired
    ReviewEntityRepo reviewEntityRepo;

    @Autowired
    OpeninghourEntityRepo openinghourEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    PlaceDetailsServiceApi placeDetailsService;

    @PostConstruct
    @Transactional
    public void placeListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_TOP_10_LAST_REVIEW);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inicia Busqueda de Utimos review");
                search(dataSnapshot);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                search(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                log.info("Se removio el elemendo No hago nada");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se movio el elemendo No hago nada");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log.info("Se Cancelo el elemendo No hago nada");
            }
        });

    }

    private void search(DataSnapshot dataSnapshot) {
        String user = dataSnapshot.getKey();
        DataSnapshot dataSnapshot3 = dataSnapshot.child(REQUEST).child("desde");
        DataSnapshot dataSnapshot4 = dataSnapshot.child(REQUEST).child("hasta");
        @SuppressWarnings("unchecked")
        HashMap<String, Object> fechaRequest = (HashMap<String, Object>) dataSnapshot.child(REQUEST).getValue();
        boolean flag = dataSnapshot.child(REQUEST).child("flag").getValue(Boolean.class);
        log.info("Se valida si se debe Buscar valor= [ " + flag + " ]");
        if (flag) {
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaDesdeDate = sdf.parse(dataSnapshot3.getValue(String.class));
                Date fechaHastaDate = sdf.parse(dataSnapshot4.getValue(String.class));
                String fechaDesde = sdf.format(fechaDesdeDate);
                String fechaHasta = sdf.format(fechaHastaDate);

                List<ReviewEntity> lastReview = reviewEntityRepo.getLastReviewByDate(fechaDesde, fechaHasta);
                fechaRequest.put("flag", false);
                ResponseFirebase resp = new ResponseFirebase();
                resp.setRequest(fechaRequest);
                if (!lastReview.isEmpty()) {
                    resp.setResponse(MapperUtil.mapAsList(lastReview, TopReviewFirebase.class));
                } else {
                    List<Object> topTenFirebase = new ArrayList<>();
                    HashMap<String, Integer> responseDefault = new HashMap<>();
                    responseDefault.put("count", 0);
                    topTenFirebase.add(responseDefault);
                    resp.setResponse(topTenFirebase);
                }
                firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_TOP_10_LAST_REVIEW, user, resp);
            } catch (Exception e) {
                log.error("Se genero error en ", e);
            }
            log.info("Se finaliza Busqueda de Utimos review");
        } else {
            log.info("Saliendo de la Busqueda no se realizala misma por flag valor= [ " + flag + " ]");
        }
    }
}
