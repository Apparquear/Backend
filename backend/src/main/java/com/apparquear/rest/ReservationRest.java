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

import com.apparquear.dao.ReservationDAO;
import com.apparquear.exception.ApiRequestException;
import com.apparquear.model.Reservation;

@RestController
@RequestMapping("apparquear/reservation")
public class ReservationRest {
	@Autowired
	private ReservationDAO reservationDAO;

	// Methods
	@CrossOrigin
	@PostMapping("/save/{user_id}/{token}")
	public void save(@PathVariable Long user_id, @PathVariable String token, @RequestBody Reservation reservation) {
        reservation.setUser_ID(user_id);
        reservation.setParking_ID(parking_id);
        reservation.setReservation_time(reservation_time);
        reservation.setReservation_duration(reservation_duration);
        reservation.setVehicle_type(vehicle_type);

        reservationDAO.save(reservation);        
	}

	@GetMapping("/findAll")
	public List<Reservation> findAll() {
		return reservationDAO.findAll();
	}
}
