package com.quick.location.service;

import java.util.List;

import com.quick.location.entity.ReportEntity;
import com.quick.location.model.ReportFirebase;

public interface ReportServiceApi {

    public void save(ReportEntity reportEntity);
    
    public List<ReportFirebase> getByPlacePlaceId(String placeID);
}
