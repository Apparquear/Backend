package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apparquear.model.Parking;

public interface ParkingDAO extends JpaRepository<Parking, Long>{
}
