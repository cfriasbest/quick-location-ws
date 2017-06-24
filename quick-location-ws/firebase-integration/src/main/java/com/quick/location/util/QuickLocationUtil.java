package com.quick.location.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.quick.location.model.Geometry;
import com.quick.location.model.ImprovementInformation;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.Location;
import com.quick.location.model.Schedule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuickLocationUtil {

	private QuickLocationUtil() {
	}

	public static String URL_FIREBASE_APP = "https://quick-location.firebaseio.com";
	public static String URL_FIREBASE_ADMIN_SDK = "/quick-location-firebase-adminsdk.json";
	public static String URL_FIREBASE_DATABASE = "/";
	public static String URL_FIREBASE_DATABASE_CHILD_PLACES = "places2";
	public static String URL_FIREBASE_DATABASE_PLACES_NEW_DATA = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/new/data";
	public static String URL_FIREBASE_DATABASE_NEW_REVIEW = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/new/reviews";

	public static String URL_FIREBASE_DATABASE_NEW_REPORT = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/new/report-issue";

	public static String URL_FIREBASE_DATABASE_PLACES_DATA = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/data";
	public static String URL_FIREBASE_DATABASE_PLACES_REVIEW = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/reviews";
	public static String URL_FIREBASE_DATABASE_PLACES_REPORT = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/report-issue";

	public static <T> T toData(ImprovementRequest inData, final Class<T> destType) {
		T palceDetail = null;
		try {
			palceDetail = destType.newInstance();
			for (ImprovementInformation dataInfo : inData.getInformations()) {

				BeanUtils.setProperty(palceDetail, dataInfo.getInformationTag(),
				        dataInfo.getInformationContent());

				if ((boolean) dataInfo.isSchedule()) {
					for (Schedule Schedule : dataInfo.getSchedules())
						log.info(Schedule.getClosedFrom() + " " + Schedule.getDayName() + ""
						        + Schedule.isOpen());
				}
				if ("location".equals(dataInfo.getInformationTag())) {
					Location location = new Location();
					location.setLat(
					        NumberUtils.toFloat(dataInfo.getInformationContent().split(":")[0]));
					location.setLng(
					        NumberUtils.toFloat(dataInfo.getInformationContent().split(":")[1]));
					Geometry geo = new Geometry();
					geo.setLocation(location);
					BeanUtils.setProperty(palceDetail, "geometry", geo);
					log.info("location");
				}
			}
			BeanUtils.setProperty(palceDetail, "placeId", inData.getPlaceId());

			return palceDetail;

		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			log.error("ERROR EN SERVICIO", e);
		}
		return palceDetail;
	}

}
