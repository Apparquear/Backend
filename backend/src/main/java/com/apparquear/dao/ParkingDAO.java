package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.apparquear.model.Parking;


public interface ParkingDAO extends JpaRepository<Parking, Long>{
	
    public List<Parking> findAllByUserID(long userID);
    public Parking findParkingByParkingID(long parkingID);
    public Parking findByParkingID(long parkingID);
}
