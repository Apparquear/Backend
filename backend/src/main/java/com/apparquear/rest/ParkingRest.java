package com.apparquear.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.ParkingDAO;
import com.apparquear.model.Parking;
import com.apparquear.model.User;

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
	
	@GetMapping("/findAll")
	public List<Parking> findAll(){
		return parkingDAO.findAll();
	}
}
