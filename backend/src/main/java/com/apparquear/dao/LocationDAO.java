package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apparquear.model.Location;

public interface LocationDAO extends JpaRepository<Location, Long>{
	public Location findByParkingID(Long parkingID);
}
