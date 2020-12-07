package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.apparquear.model.Parking;

import java.util.List;

public interface ParkingDAO extends JpaRepository<Parking, Long>{
    public List<Parking> findAllByUserID(long userID);
    public Parking findParkingByParkingID(long parkingID);
    public Parking findByParkingID(long parkingID);
  
    /*@Query(value = "UPDATE Parking SET bike_spaces_available =  FROM Parking WHERE parkingID = ?1", nativeQuery = true)
    public Parking findDipsByParkingID(long parkingID);*/
}
