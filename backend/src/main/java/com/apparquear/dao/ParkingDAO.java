package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.apparquear.model.Parking;

public interface ParkingDAO extends JpaRepository<Parking, Long>{
    @Query("SELECT p  FROM Parking p WHERE p.parking_ID = ?1")
    Parking findCupo(long idParking );
}
