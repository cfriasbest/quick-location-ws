package com.quick.location.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReportEntity;

@Repository
public interface ReportEntityRepo extends CrudRepository<ReportEntity, Integer> {

    @Query(value = "SELECT * FROM report WHERE place_id = :placeId and done=false ", nativeQuery = true)
	public List<ReportEntity> getByPlacePlaceId(@Param("placeId")String placeID);

	@Query(value = "SELECT * FROM report WHERE place_id = :placeId order by id_report desc LIMIT 10", nativeQuery = true)
	public List<ReportEntity> getLastReportByPlace(@Param("placeId") String placeId);

}