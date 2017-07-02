package com.quick.location.service;

import java.util.List;

import com.quick.location.entity.ReportEntity;
import com.quick.location.model.firebase.ReportFirebase;

public interface ReportServiceApi {

    public void save(ReportEntity reportEntity);
    
    public List<ReportFirebase> getByPlacePlaceId(String placeID);

    public ReportEntity findByPk(int idReport);
}
