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
import com.quick.location.entity.ReportEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.Geometry;
import com.quick.location.model.ImprovementInformation;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.Location;
import com.quick.location.model.OpeningHours;
import com.quick.location.model.Schedule;
import com.quick.location.model.firebase.ReportFirebase;
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

        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REPORT);

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

    @PostConstruct
    @Transactional
    public void updateReportListener() {

        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REPORT);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se procede a insertar el reporte onChildAdded");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                boolean isUpdate=false;
                boolean isRemoved=false;
                for (DataSnapshot dataSnapshotItem : dataSnapshot.getChildren()) {
                    ReportFirebase report = dataSnapshotItem.getValue(ReportFirebase.class);
                    if (report.isDone()) {
                        ReportEntity rerpot = reportService.findByPk(report.getIdReport());
                        rerpot.setDone(true);
                        reportService.save(rerpot);
                        isUpdate = true;
                    }
                    if (report.isRemove()) {
                        ReportEntity rerpot = reportService.findByPk(report.getIdReport());
                        rerpot.setDone(true);
                        reportService.save(rerpot);
                        List<ReportFirebase> repotsFirebase = reportService.getByPlacePlaceId(rerpot.getPlaceId());
                        placeFirebaseListener.updatePlace(rerpot.getPlaceId(), false, true, repotsFirebase.size(), 0);
                        firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REPORT, rerpot.getPlaceId(), repotsFirebase);
                        isRemoved = true;
                    }
                    log.info("Se cambio el elemendo 1");
                }
                if (isUpdate) {
                    updteRFirebase(dataSnapshot.getKey());
                }
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
        toDataEntity(inData, dataSnapshot.getKey());

        log.info("Se inserto el elemendo ");
    }

    public void toDataEntity(ImprovementRequest inData, String key) {
        ReportEntity palceDetail = null;

        for (ImprovementInformation dataInfo : inData.getInformations()) {
            palceDetail = new ReportEntity();

            palceDetail.setAuthor(inData.getAuthor());

            if ("location".equals(dataInfo.getInformationTag())) {

                palceDetail.setField(dataInfo.getInformationTag());
                palceDetail.setFieldHuman("Locacion");
                palceDetail.setValue(dataInfo.getInformationContent());
                palceDetail.setPlaceId(inData.getPlaceId());


            } else if ("address".equals(dataInfo.getInformationTag())) {

                palceDetail.setField("formattedAddress");
                palceDetail.setFieldHuman("Direccion");
                palceDetail.setValue(dataInfo.getInformationContent());
                palceDetail.setPlaceId(inData.getPlaceId());

            } else if ("telephone".equals(dataInfo.getInformationTag())) {

                palceDetail.setField("formattedPhoneNumber");
                palceDetail.setFieldHuman("Telefono");
                palceDetail.setValue(dataInfo.getInformationContent());
                palceDetail.setPlaceId(inData.getPlaceId());

            } else if ("schedule".equals(dataInfo.getInformationTag())) {
                StringBuilder stb = new StringBuilder();
                for (Schedule schedule : dataInfo.getSchedules()) {
                    stb.append(schedule.getDayName());
                    stb.append(":").append(schedule.getOpenFrom()).append(" - ").append(schedule.getClosedFrom()).append(",");

                }
                if ("name".equals(dataInfo.getInformationTag())) {

                    palceDetail.setField(dataInfo.getInformationTag());
                    palceDetail.setFieldHuman("Nombre");
                    palceDetail.setValue(dataInfo.getInformationContent());
                    palceDetail.setPlaceId(inData.getPlaceId());

                }

                palceDetail.setField("openingHours");
                palceDetail.setFieldHuman("Horarios");

                palceDetail.setValue(stb.toString());
                palceDetail.setPlaceId(inData.getPlaceId());

            }
            try {
                palceDetail.setDone(false);
                reportService.save(palceDetail);
                updteFirebase(inData.getPlaceId());

                 firebasePlaceService.removeObjectToFirebase(
                 QuickLocationUtil.URL_FIREBASE_DATABASE_NEW_REPORT,
                 inData.getPlaceId() + "/" + key);

            } catch (Exception e) {
                log.error("Eror en ", e);
            }
        }

    }

    public void updteRFirebase(String placeId) {

        List<ReportFirebase> repotsFirebase = reportService.getByPlacePlaceId(placeId);

        for (ReportFirebase report : repotsFirebase) {
            if ("location".equals(report.getField())) {
                Geometry geometry = new Geometry();
                Location location = new Location();
                location.setLat(NumberUtils.toFloat(((String) report.getValue()).split(":")[0]));
                location.setLng(NumberUtils.toFloat(((String) report.getValue()).split(":")[1]));
                geometry.setLocation(location);
                report.setValue(geometry);
            }
            if ("openingHours".equals(report.getField())) {
                String[] week = report.getValue().toString().split(",");
                OpeningHours openO = new OpeningHours();
                openO.setWeekdayText(Arrays.asList(week));
                report.setValue(openO);
            }
        }

        firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REPORT, placeId, repotsFirebase);
    }
    
    public void updteFirebase(String placeId) {

        // palceDetail
        List<ReportFirebase> repotsFirebase = reportService.getByPlacePlaceId(placeId);
        placeFirebaseListener.updatePlace(placeId, false, true, repotsFirebase.size(), 0);

        for (ReportFirebase report : repotsFirebase) {
            if ("location".equals(report.getField())) {
                Geometry geometry = new Geometry();
                Location location = new Location();
                location.setLat(NumberUtils.toFloat(((String) report.getValue()).split(":")[0]));
                location.setLng(NumberUtils.toFloat(((String) report.getValue()).split(":")[1]));
                geometry.setLocation(location);
                report.setValue(geometry);
            }
            if ("openingHours".equals(report.getField())) {
                String[] week = report.getValue().toString().split(",");
                OpeningHours openO = new OpeningHours();
                openO.setWeekdayText(Arrays.asList(week));
                report.setValue(openO);
            }
        }

        firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REPORT, placeId, repotsFirebase);
    }
}
