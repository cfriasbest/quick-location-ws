package com.quick.location.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quick.location.entity.ReportEntity;

@Repository
public interface ReportEntityRepo extends CrudRepository<ReportEntity, Integer> {

    @Query(value = "SELECT * FROM report WHERE place_id = :placeId and done=false  order by date desc ", nativeQuery = true)
    public List<ReportEntity> getByPlacePlaceId(@Param("placeId") String placeID);

    @Query(value = "SELECT * FROM report WHERE place_id = :placeId order by date desc ", nativeQuery = true)
    public List<ReportEntity> getLastReportByPlace(@Param("placeId") String placeId);

    @Query(value = "SELECT author_name as author,count(author_name) as count FROM  review " + "union " + "SELECT author,count(author) as count FROM  report "
            + "group by author order by count desc ", nativeQuery = true)

    public ArrayList<Object[]> getListTopTenUser();

    @Query(value = "select t.author,count(*) as count from (select r.author_name as author,r.date from review r union all select re.author,re.date from report re )"
            + " as t where DATE(t.date) >= STR_TO_DATE(:desde,'%Y-%m-%d') and DATE(t.date) <= STR_TO_DATE(:hasta,'%Y-%m-%d') group by t.author order by count desc", nativeQuery = true)

    public ArrayList<Object[]> getListTopTenUserByDate(@Param("desde") String desde, @Param("hasta") String hasta);

}