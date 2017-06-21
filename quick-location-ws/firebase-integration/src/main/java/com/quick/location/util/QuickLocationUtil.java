package com.quick.location.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.quick.location.model.ImprovementInformation;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.Schedule;

public class QuickLocationUtil {

	public static String URL_FIREBASE_APP = "https://quick-location.firebaseio.com";
	public static String URL_FIREBASE_ADMIN_SDK = "/quick-location-firebase-adminsdk.json";
	public static String URL_FIREBASE_DATABASE = "/";
	public static String URL_FIREBASE_DATABASE_CHILD_PLACES = "places3";
	public static String URL_FIREBASE_DATABASE_PLACES_NEW_DATA = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/new/data";
	public static String URL_FIREBASE_DATABASE_NEW_REVIEW = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/new/review";

	public static String URL_FIREBASE_DATABASE_NEW_REPORT = "places"
	        + "/new/review";

	public static String URL_FIREBASE_DATABASE_PLACES_DATA = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/data";
	public static String URL_FIREBASE_DATABASE_PLACES_REVIEW = URL_FIREBASE_DATABASE_CHILD_PLACES
	        + "/report-issue";

	public static <T> T toData(ImprovementRequest inData, final Class<T> destType) {
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
}
