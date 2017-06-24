package com.quick.location.service.impl;

import com.quick.location.entity.ReportEntity;
import com.quick.location.entity.ReviewEntity;
import com.quick.location.model.ReportFirebase;
import com.quick.location.repo.ReportEntityRepo;
import com.quick.location.repo.ReviewEntityRepo;
import com.quick.location.service.ReportServiceApi;
import com.quick.location.service.ReviewServiceApi;
import com.quick.location.service.util.MapperUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<ReportFirebase> getByPlacePlaceId(String placeID) {
		List<ReportEntity> reports = reportEntityRepo.getByPlacePlaceId(placeID); 
		return MapperUtil.mapAsList(reports, ReportFirebase.class);
	}

}
