package com.apparquear.rest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.apparquear.model.Location;
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
		System.out.println(parking.getLocation().getLatitude());
		System.out.println(parking.getLocation().getLongitude());
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
	@GetMapping("/findNear/{zoom}")
	public List<Parking> findNear(@RequestBody Location location, @PathVariable Integer zoom){
		List<Parking> allParkings = parkingDAO.findAll();
		for (Parking parking: allParkings) {
			zoom=19 -zoom;
			if (parking.getLocation().getDistanceTo(location)>(50*zoom)) allParkings.remove(parking);
		}
		return allParkings;
    
	@CrossOrigin
	@PostMapping("/findAvailableSlots")
	public List<Integer> findAvailableSlots(@RequestBody Parking requestedParking) {
		List<Integer> resp = new ArrayList<>();
		Parking parking = new Parking();
		try {
			Optional<Parking> optionalParking = parkingDAO.findById(requestedParking.getParking_ID());
			parking = optionalParking.get();
			resp.add(parking.getCar_spaces_available());
			resp.add(parking.getMotorcycle_spaces_available());
			resp.add(parking.getBike_spaces_available());
		}catch (Exception e) {
			throw new ApiRequestException("Este parqueadero no se encuentra registrado");
		}
		return resp;
	}
}
