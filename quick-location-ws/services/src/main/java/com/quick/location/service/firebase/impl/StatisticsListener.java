package com.quick.location.service.firebase.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.Statistic;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.StatisticsFirebase;
import com.quick.location.repo.OpeninghourEntityRepo;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.StatictsEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.util.MapperUtil;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StatisticsListener {

    private static final String REQUEST = "request";
    @Autowired
    PlaceEntityRepo placeEntityRepo;
    @Autowired
    StatictsEntityRepo statictsEntityRepo;

    @Autowired
    OpeninghourEntityRepo openinghourEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    PlaceDetailsServiceApi placeDetailsService;

    @PostConstruct
    @Transactional
    public void placeListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_STATISTICS);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inicia Busqueda de Utimos review");
                for (DataSnapshot dataSnapshotChild :dataSnapshot.getChildren())
                insertToTable(dataSnapshotChild);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                insertToTable(dataSnapshot);
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

    private void insertToTable(DataSnapshot dataSnapshot) {
        String user = dataSnapshot.getKey();
        StatisticsFirebase statisticsFirebase = dataSnapshot.getValue(StatisticsFirebase.class);
        Statistic entity = MapperUtil.mapBean(statisticsFirebase, Statistic.class);
        statictsEntityRepo.save(entity);
        log.info("Se valida si se debe Buscar valor= [ " + "" + " ]");
       
            log.info("Se finaliza Busqueda de Utimos review");
        
    }
}
