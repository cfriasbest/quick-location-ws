package com.quick.location.service.firebase.impl;

import java.math.BigInteger;
import java.text.ParseException;
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
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.firebase.ResponseFirebase;
import com.quick.location.model.firebase.TopTenFirebase;
import com.quick.location.repo.OpeninghourEntityRepo;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.ReportEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Top10FirebaseListener {

    private static final String HASTA = "hasta";
    private static final String DESDE = "desde";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String FLAG2 = "flag";
    private static final String REQUEST = "request";
    @Autowired
    PlaceEntityRepo placeEntityRepo;
    @Autowired
    ReportEntityRepo reportEntityRepo;

    @Autowired
    OpeninghourEntityRepo openinghourEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    PlaceDetailsServiceApi placeDetailsService;

    @PostConstruct
    @Transactional
    public void placeListener() {
        DatabaseReference ref = firebasePlaceService.getDatabaseReference(QuickLocationUtil.URL_FIREBASE_DATABASE_TOP_10_USER);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inicia la Busqueda Usuarios");
                search(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                log.info("Se inicia la Busqueda Usuarios");
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
        DataSnapshot dataSnapshot3 = dataSnapshot.child(REQUEST).child(DESDE);
        DataSnapshot dataSnapshot4 = dataSnapshot.child(REQUEST).child(HASTA);
        @SuppressWarnings("unchecked")
        HashMap<String, Object> fechaRequest = (HashMap<String, Object>) dataSnapshot.child(REQUEST).getValue();
        boolean flag = dataSnapshot.child(REQUEST).child(FLAG2).getValue(Boolean.class);
        log.info("Se valida si se debe Buscar valor= [ " + flag + " ]");
        if (flag) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Date fechaDesdeDate;
            Date fechaHastaDate;
            try {
                fechaDesdeDate = sdf.parse(dataSnapshot3.getValue(String.class));

                fechaHastaDate = sdf.parse(dataSnapshot4.getValue(String.class));
                String fechaDesde = sdf.format(fechaDesdeDate);
                String fechaHasta = sdf.format(fechaHastaDate);
                int total = 0;
                List<Object> topTenFirebase = new ArrayList<>();
                ArrayList<Object[]> mapa = reportEntityRepo.getListTopTenUserByDate(fechaDesde, fechaHasta);
                for (Object[] objeto : mapa) {
                    BigInteger bInt = (BigInteger) objeto[1];
                    total += bInt.longValue();
                }
                for (Object[] objeto : mapa) {
                    if (null != objeto[0]) {
                        TopTenFirebase topTen = new TopTenFirebase();
                        topTen.setName((String) objeto[0]);
                        BigInteger bInt = (BigInteger) objeto[1];
                        topTen.setCount(bInt.longValue());
                        topTen.setPorcent((topTen.getCount() / total) * 100);
                        topTenFirebase.add(topTen);
                    } else {
                        HashMap<String, Integer> responseDefault = new HashMap<>();
                        responseDefault.put("count", 0);
                        topTenFirebase.add(responseDefault);
                    }
                }
                fechaRequest.put(FLAG2, false);
                ResponseFirebase resp = new ResponseFirebase();
                resp.setRequest(fechaRequest);
                resp.setResponse(topTenFirebase);
                firebasePlaceService.objectToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_TOP_10_USER, user, resp);
            } catch (ParseException e) {
                log.error("Se genero error en ", e);
            }
            log.info("Se finaliza la Busqueda de Usuarios");
        } else {
            log.info("Saliendo de la Busqueda no se realizala misma por flag valor= [ " + flag + " ]");
        }
    }
}
