package com.apparquear.rest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.ParkingDAO;
import com.apparquear.exception.ApiRequestException;
import com.apparquear.model.Parking;

@RestController
@RequestMapping("apparquear/parking")
public class ParkingRest {
	@Autowired
	private ParkingDAO parkingDAO;

	// Methods
	@CrossOrigin
	@PostMapping("/save/{user_id}/{token}")
	public void save(@PathVariable Long user_id, @PathVariable String token, @RequestBody Parking parking) {
		System.out.println(parking.getLatitude());
		System.out.println(parking.getLongitude());
		System.out.println(user_id);
		System.out.println(token);
		parking.setUser_ID(user_id);
		parking.setBike_spaces_available(parking.getBike_spaces());
		parking.setCar_spaces_available(parking.getCar_spaces());
		parking.setMotorcycle_spaces_available(parking.getMotorcycle_spaces());
		parking.setScore(0.0);
		parking.setTotal_spaces(parking.getBike_spaces() + parking.getCar_spaces() + parking.getMotorcycle_spaces());
		parking.setTotal_spaces_available(
				parking.getBike_spaces() + parking.getCar_spaces() + parking.getMotorcycle_spaces());
		parkingDAO.save(parking);
	}

	@GetMapping("/findAll")
	public List<Parking> findAll() {
		return parkingDAO.findAll();
	}

	@CrossOrigin
	@PostMapping("/cupos")
	public List<Integer> findCupos(@RequestBody Parking park) {
		List<Integer> ret = new ArrayList<>();
		Parking parking = new Parking();
		Optional<Parking> optionalParking = parkingDAO.findById(park.getParking_ID());
		
		if (optionalParking != null) {
			parking = optionalParking.get();
			ret.add(parking.getCar_spaces_available());
			ret.add(parking.getMotorcycle_spaces_available());
			ret.add(parking.getBike_spaces_available());
		} else {
			throw new ApiRequestException("Este parqueadero no se encuentra registrado");
		}
		return ret;
	}
}
