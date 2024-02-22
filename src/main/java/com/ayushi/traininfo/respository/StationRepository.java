package com.ayushi.traininfo.respository;

import com.ayushi.traininfo.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    @Query(value = "Select * from station where station_code like ?1%", nativeQuery = true)
    List<Station> findStationsStartingWith(String searchString);
    @Query(value = "Select * from station where station_code=?1", nativeQuery = true)
    Station findStationByStationCode(String stationCode);
}
