package com.quick.location.util;

import com.quick.location.model.Geometry;
import com.quick.location.model.ImprovementInformation;
import com.quick.location.model.ImprovementRequest;
import com.quick.location.model.Location;
import com.quick.location.model.Schedule;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.lang.reflect.InvocationTargetException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuickLocationUtil {

    private QuickLocationUtil() {}

    public final static String URL_FIREBASE_APP = "https://quick-location.firebaseio.com";
    public final static String URL_FIREBASE_ADMIN_SDK = "/quick-location-firebase-adminsdk.json";
    public final static String URL_FIREBASE_DATABASE = "/";
    public final static String URL_FIREBASE_DATABASE_CHILD_PLACES = "places3";
    public final static String URL_FIREBASE_DATABASE_PLACES_NEW_DATA =
        URL_FIREBASE_DATABASE_CHILD_PLACES
            + "/new/data";
    public final static String URL_FIREBASE_DATABASE_NEW_REVIEW = URL_FIREBASE_DATABASE_CHILD_PLACES
        + "/new/review";

    public final static String URL_FIREBASE_DATABASE_NEW_REPORT = "places"
        + "/new/review";

    public final static String URL_FIREBASE_DATABASE_PLACES_DATA =
        URL_FIREBASE_DATABASE_CHILD_PLACES
            + "/data";
    public final static String URL_FIREBASE_DATABASE_PLACES_REVIEW =
        URL_FIREBASE_DATABASE_CHILD_PLACES
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
                        log.info(Schedule.getClosedFrom() + " " + Schedule.getDayName()
                            + "" + Schedule.getIsOpen());
                }
                if("location".equals(dataInfo.getInformationTag()))
                {
                    Location location = new Location();
                    location.setLat(NumberUtils.toFloat(dataInfo.getInformationContent().split(":")[0]));
                    location.setLng(NumberUtils.toFloat(dataInfo.getInformationContent().split(":")[1]));
                    Geometry geo = new Geometry();
                    geo.setLocation(location);
                    BeanUtils.setProperty(palceDetail, "geometry",geo);
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
