package com.quick.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quick.location.entity.ReportEntity;
import com.quick.location.model.firebase.ReportFirebase;
import com.quick.location.repo.ReportEntityRepo;
import com.quick.location.service.ReportServiceApi;
import com.quick.location.service.util.MapperUtil;

/**
 * 
 * @author cfriasb
 *
 */
@Service
public class ReportService implements ReportServiceApi {

    @Autowired
    ReportEntityRepo reportEntityRepo;

    @Override
    public void save(ReportEntity reportEntity) {
        reportEntityRepo.save(reportEntity);

    }

    @Override
    @Transactional
    public List<ReportFirebase> getByPlacePlaceId(String placeID) {
        List<ReportEntity> reports = reportEntityRepo.getByPlacePlaceId(placeID);
        return MapperUtil.mapAsList(reports, ReportFirebase.class);
    }

    @Override
    public ReportEntity findByPk(int idReport) {
        return reportEntityRepo.findOne(idReport);
    }

}
