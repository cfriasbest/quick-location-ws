package com.quick.location.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.location.entity.OpeninghourEntity;
import com.quick.location.entity.PlaceEntity;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.OpeningHours;
import com.quick.location.model.firebase.PlaceDetailFirebase;
import com.quick.location.model.firebase.ReviewFirebase;
import com.quick.location.repo.OpeninghourEntityRepo;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.util.MapperUtil;
import com.quick.location.util.QuickLocationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cfriasb
 *
 */
@Service
@Slf4j

public class PlaceDetailsService implements PlaceDetailsServiceApi {

    @Autowired
    PlaceEntityRepo placeEntityRepo;
    
    @Autowired
    OpeninghourEntityRepo openinghourEntityRepo;

    @Autowired
    FirebasePlaceService firebasePlaceService;

    @Autowired
    ReviewEntityRepo reviewEntityRepo;

    @PostConstruct
    public void initData() {

      List<ReviewEntity> reviewEntity =  (List<ReviewEntity>) reviewEntityRepo.findAll();
      List<ReviewFirebase> reviewFirebases = MapperUtil.mapAsList(reviewEntity, ReviewFirebase.class);
      for (ReviewFirebase reviewFirebase :reviewFirebases)
      firebasePlaceService.objectPushToFirebase(QuickLocationUtil.URL_FIREBASE_DATABASE_PLACES_REVIEW, reviewFirebase.getPlaceId(), reviewFirebase);
      
    }

    @Override
    @Transactional
    public void savePlaceDetails(PlaceDetailFirebase placeDetail) {
        log.info("Se insertara el place y sus detalles en el servidor");
        DozerBeanMapper mapper = new DozerBeanMapper();
        PlaceEntity placeEntity = mapper.map(placeDetail, PlaceEntity.class);
        // placeEntity.autoSetThis();
        placeEntityRepo.save(placeEntity);
        log.info("Se Finaliza la insecion ");
    }

    private void updateFirebaseServer() {
        log.info("Se Actualizaran los datos del servidor");
        List<PlaceEntity> placesEntity = (List<PlaceEntity>) placeEntityRepo.findAll();
        List<PlaceDetailFirebase> places = MapperUtil.mapAsList(placesEntity, PlaceDetailFirebase.class);
        for(PlaceDetailFirebase place : places)
        {
        OpeninghourEntity open = openinghourEntityRepo.findOne(place.getPlaceId());
        if (null != open) {
            OpeningHours openHours = new OpeningHours();
            openHours.setWeekdayText(Arrays.asList(open.getWeekdayText().replace("[","").replace("]","").trim().split(",")));
//            for (String day : openHours.getWeekdayText())
//            {
//               Pattern regex = new Pattern("");
//               System.out.println(day); 
//            }
            place.setOpeningHours(openHours);
        }
        }
        log.info("Se Actualizaron los datos del servidor");
    }

}
