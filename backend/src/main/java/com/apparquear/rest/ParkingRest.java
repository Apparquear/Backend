package com.apparquear.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.ParkingDAO;
import com.apparquear.model.Parking;

@RestController
@RequestMapping("apparquear/parking")
public class ParkingRest {
	@Autowired
	private ParkingDAO parkingDAO;
	
	//Methods
	@PostMapping("/save")
	public void save(@RequestBody Parking parking) {
		System.out.println(parking.getOpening_time());
		parkingDAO.save(parking);
	}
}
