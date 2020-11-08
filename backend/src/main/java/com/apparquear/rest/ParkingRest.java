package com.apparquear.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.apparquear.model.Bounds;
import com.apparquear.model.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apparquear.dao.LocationDAO;
import com.apparquear.dao.ParkingDAO;
import com.apparquear.exception.ApiRequestException;
import com.apparquear.model.Parking;

@RestController
@RequestMapping("apparquear/parking")
public class ParkingRest {
	@Autowired
	private ParkingDAO parkingDAO;
	@Autowired
	private LocationDAO locationDAO;

	// Methods
	@CrossOrigin
	@PostMapping("/save/{user_id}/{token}/{latitude}/{longitude}")
	public void save(@PathVariable Long user_id, @PathVariable String token, @PathVariable Double latitude,
			@PathVariable Double longitude, @RequestBody Parking parking) {
		try {
			parking.setUser_ID(user_id);
			parking.setBike_spaces_available(parking.getBike_spaces());
			parking.setCar_spaces_available(parking.getCar_spaces());
			parking.setMotorcycle_spaces_available(parking.getMotorcycle_spaces());
			parking.setScore(0.0);
			parking.setTotal_spaces(
					parking.getBike_spaces() + parking.getCar_spaces() + parking.getMotorcycle_spaces());
			parking.setTotal_spaces_available(
					parking.getBike_spaces() + parking.getCar_spaces() + parking.getMotorcycle_spaces());
			parkingDAO.save(parking);
			Optional<Parking> savedParking = parkingDAO.findOne((Example<Parking>) parking);
			Location location = new Location();
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setParkingID(savedParking.get().getParkingID());
			locationDAO.save(location);
		} catch (Exception e) {
			throw new ApiRequestException(e.getMessage());
		}
	}

	@GetMapping("/findAll")
	public List<Parking> findAll() {
		return parkingDAO.findAll();
	}

	@CrossOrigin
	@GetMapping("/findNear/{zoom}")
	public List<Location> findNear(@RequestBody Location location, @PathVariable Integer zoom) {
		List<Location> allLocation = locationDAO.findAll();
		try {
			for (Location position : allLocation) {
				zoom = 19 - zoom;
				if (position.getDistanceTo(location) > (50 * zoom))
					allLocation.remove(position);
			}
		}
		catch (Exception e){
			throw new ApiRequestException("Error inesperado");
		}
		return allLocation;
	}


	@CrossOrigin
	@PostMapping("/findRange")
	public List<Location> findRange(@RequestBody(required = false) Bounds bounds) {
		List<Location> allLocation = locationDAO.findAll();
		List<Location> nearbyLocations = new <Location> ArrayList();
		try {
			for (Location position : allLocation) {
				if (position.inside(bounds.getSouthWest(),bounds.getNorthEast()))
					nearbyLocations.add(position);
			}
		}
		catch (Exception e){
			throw new ApiRequestException("Error inesperado");
		}
		return nearbyLocations;
	}


	@CrossOrigin
	@PostMapping("/findAvailableSlots")
	public List<Integer> findAvailableSlots(@RequestBody Parking requestedParking) {
		List<Integer> resp = new ArrayList<>();
		Parking parking = new Parking();
		try {
			Optional<Parking> optionalParking = parkingDAO.findById(requestedParking.getParkingID());
			parking = optionalParking.get();
			resp.add(parking.getCar_spaces_available());
			resp.add(parking.getMotorcycle_spaces_available());
			resp.add(parking.getBike_spaces_available());
		} catch (Exception e) {
			throw new ApiRequestException("Este parqueadero no se encuentra registrado");
		}
		return resp;
	}
}
