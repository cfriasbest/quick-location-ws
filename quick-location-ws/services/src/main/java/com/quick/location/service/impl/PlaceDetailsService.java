package com.quick.location.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.quick.location.entity.PlaceEntity;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.entity.SugestDataEntity;
import com.quick.location.firebase.config.FirebasePlaceService;
import com.quick.location.model.ImprovementInformation;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.PlaceDetail;
import com.quick.location.model.Report;
import com.quick.location.model.Schedule;
import com.quick.location.model.SugestData;
import com.quick.location.repo.PlaceEntityRepo;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.repo.SugestDataEntityRepo;
import com.quick.location.service.PlaceDetailsServiceApi;
import com.quick.location.service.util.MapperUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cfriasb
 *
 */
@Service
@Slf4j

public class PlaceDetailsService implements PlaceDetailsServiceApi {

	// @Autowired
	// ActualizacionTread actualizacionTread;

	@Autowired
	PlaceEntityRepo placeEntityRepo;

	@Autowired
	FirebasePlaceService firebasePlaceService;

	@Autowired
	SugestDataEntityRepo sugestDataEntityRepo;
	
	@Autowired
	ReviewEntityRepo reviewEntityRepo;

	boolean changePlace;
	boolean changeReview;
	boolean changeReport;

	@PostConstruct
	public void initData() {
		
		updateFirebaseServer();
		
		
		Runnable r = new Runnable() {
			private long initialTime;

			public void run() {
				while (true) {
					esperarXsegundos(7);
					if (isChangePlace()) {
						System.out.println("Procesado la actualizacion Inicio->Tiempo: "
						        + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
						updateFirebaseServer();
						System.out.println("Procesado la actualizacion Fin->Tiempo: "
						        + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
						setChangePlace(false);
					}
				}
			}
		};

		new Thread(r).start();
		updatePlaceListener();
		addPlaceListener();
		addReviewListener();

	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	@Transactional
	public void savePlaceDetails(PlaceDetail placeDetail) {
		log.info("Se insertara el place y sus detalles en el servidor");
		DozerBeanMapper mapper = new DozerBeanMapper();

		PlaceEntity placeEntity = mapper.map(placeDetail, PlaceEntity.class);
		placeEntity.autoSetThis();

		placeEntity = placeEntityRepo.save(placeEntity);

		log.info("Se Finaliza la insecion ");
	}

	private void updateFirebaseServer() {
		log.info("Se Actualizaran los datos del servidor");
		List<PlaceEntity> placesEntity = (List<PlaceEntity>) placeEntityRepo.findAll();
		List<PlaceDetail> places = MapperUtil.mapAsList(placesEntity, PlaceDetail.class);
		firebasePlaceService.setPlaceListOnFirebase(places);
		log.info("Se Actualizaron los datos del servidor");
	}

	@Transactional
	public void updatePlaceListener() {
		DatabaseReference ref = firebasePlaceService
		        .getDatabaseReference("server/saving-data/fireblog/updated");

		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				SugestData sugerencia = dataSnapshot.getValue(SugestData.class);
				sugerencia.setIdSugestData(dataSnapshot.getKey());
				sugerencia.setState("SD001");
				sugestDataEntityRepo.save(MapperUtil.mapBean(sugerencia, SugestDataEntity.class));
				log.info("Se inserto el elemendo ");

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
				SugestData sugerencia = dataSnapshot.getValue(SugestData.class);
				sugerencia.setIdSugestData(dataSnapshot.getKey());
				sugerencia.setState("SD001");
				sugestDataEntityRepo.save(MapperUtil.mapBean(sugerencia, SugestDataEntity.class));
				log.info("Se cambio el elemendo ");

				pruebaActualizacion(sugerencia);

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				SugestData sugerencia = dataSnapshot.getValue(SugestData.class);
				sugerencia.setIdSugestData(dataSnapshot.getKey());
				sugestDataEntityRepo.delete(sugerencia.getIdSugestData());
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

		// updateFirebaseServer();
	}

	@Transactional
	public void addPlaceListener() {
		DatabaseReference ref = firebasePlaceService.getDatabaseReference("place/new");

		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				log.info("Se inserto el elemendo ");
				ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
				PlaceDetail place = toData(inData, PlaceDetail.class);
				savePlaceDetails(place);
				setChangePlace(true);
				log.info("Se inserto el elemendo ");

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
				ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
				PlaceDetail place = toData(inData, PlaceDetail.class);
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

	@Transactional
	public void addReportListener() {

		DatabaseReference ref = firebasePlaceService.getDatabaseReference("report/place");

		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				log.info("Se inserto el elemendo ");
				ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
				Report place = toData(inData, Report.class);
				// savePlaceDetails(place);

				log.info("Se inserto el elemendo ");

			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
				ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
				Report report = toData(inData, Report.class);
				log.info("Se cambio el elemendo 1", report.toString());
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

	@Transactional
	public void addReviewListener() {
		DatabaseReference ref = firebasePlaceService.getDatabaseReference("place/review");
		ref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				log.info("Se inserto el elemendo ");
				ImprovementRequest inData = dataSnapshot.getValue(ImprovementRequest.class);
//				DataSnapshot value = (DataSnapshot) dataSnapshot.get;
				
				ReviewEntity entity = toData(inData, ReviewEntity.class);
				PlaceEntity place = new PlaceEntity();
				place.setPlaceId(dataSnapshot.getKey());
				entity.setPlace(place);
				reviewEntityRepo.save(entity);
				setChangeReview(true);
				setChangePlace(true);
				log.info("Se inserto el elemendo ");

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

	private <T> T toData(ImprovementRequest inData, final Class<T> destType) {
		T palceDetail = null;
		try {
			palceDetail = destType.newInstance();
			for (ImprovementInformation dataInfo : inData.getInformations()) {

				BeanUtils.setProperty(palceDetail, dataInfo.getInformationTag(),
				        dataInfo.getInformationContent());

				if ((boolean) dataInfo.getIsSchedule()) {
					for (Schedule Schedule : dataInfo.getSchedules())
						System.out.println(Schedule.getClosedFrom() + " " + Schedule.getDayName()
						        + "" + Schedule.getIsOpen());
				}
			}
			BeanUtils.setProperty(palceDetail, "placeId", inData.getPlaceId());

			return palceDetail;

		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return palceDetail;
	}

	// private PlaceDetail toplace(ImprovementRequest inData) {
	// PlaceDetail palceDetail = new PlaceDetail();
	// try {
	//
	// for (ImprovementInformation dataInfo : inData.getInformations()) {
	//
	// BeanUtils.setProperty(palceDetail, dataInfo.getInformationTag(),
	// dataInfo.getInformationContent());
	//
	// if ((boolean) dataInfo.getIsSchedule()) {
	// for (Schedule Schedule : dataInfo.getSchedules())
	// System.out.println(Schedule.getClosedFrom() + " " + Schedule.getDayName()
	// + "" + Schedule.getIsOpen());
	// }
	// }
	// BeanUtils.setProperty(palceDetail, "placeId", inData.getPlaceId());
	//
	// return palceDetail;
	//
	// } catch (IllegalAccessException | InvocationTargetException e) {
	// log.error("Mensage error ", e);
	// }
	// return palceDetail;
	// }

	@Override
	@Transactional
	public void updatePlaceDetails(SugestData sugestData) {
		log.info("Se ingresa al metodo de Actualizacion de datos ");

		// PlaceEntity entity =
		// placeEntityRepo.findOne(sugestData.getPlaceId());
		// PlacedetailEntity placeDetail = entity.getPlacedetail();
		// if (null != sugestData.getDirection()) {
		// placeDetail.setFormattedAddress(sugestData.getDirection());
		// }
		// if (null != sugestData.getPhone()) {
		// placeDetail.setFormattedPhoneNumber(sugestData.getPhone());
		// }
		//
		// placedetailEntityRepo.save(placeDetail);

		log.info("Se  Actualizo los datos ");

	}

	private void pruebaActualizacion(SugestData sugestData) {
		// List<?> test =
		// sugestDataEntityRepo.findByPlaceId(sugestData.getPlaceId());
		// updatePlaceDetails(sugestData);
	}

	public boolean isChangePlace() {
		return changePlace;
	}

	public void setChangePlace(boolean changePlace) {
		this.changePlace = changePlace;
	}

	public boolean isChangeReview() {
		return changeReview;
	}

	public void setChangeReview(boolean changeReview) {
		this.changeReview = changeReview;
	}

	public boolean isChangeReport() {
		return changeReport;
	}

	public void setChangeReport(boolean changeReport) {
		this.changeReport = changeReport;
	}

}
