package com.apparquear.rest;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.apparquear.dao.ReservationDAO;
import com.apparquear.model.Reservation;
import com.apparquear.exception.ApiRequestException;
import java.util.Date;
import java.sql.Timestamp;

@RestController
@RequestMapping("apparquear/reservation")
public class ReservationRest {
	@Autowired
	private ReservationDAO reservationDAO;

	//Realizar reserva

	@CrossOrigin
	@PostMapping("/save/{userID}/{parkingID}/{token}")
	public void save(@PathVariable Integer userID,@PathVariable Integer parkingID, @PathVariable String token, @RequestBody Reservation reservation) {
	Date date = new Date();
	long time = date.getTime();
	Timestamp ts = new Timestamp(time);
		try {
			if(ts.before(reservation.getReservation_time()) && reservation.getFinal_time().after(reservation.getReservation_time()) ){
			reservation.setUser_ID(userID);
			reservation.setParking_ID(parkingID);
			reservation.setReservation_time(reservation.getReservation_time());
			reservation.setFinal_time(reservation.getFinal_time());
			reservation.setVehicle_type(reservation.getVehicle_type());
			reservationDAO.save(reservation);     
			}   
		}catch (Exception e) {
			throw new ApiRequestException(e.getMessage());
		}
	}

		//Busca reservaciones por id de reservación
	@GetMapping("/findByParking/{parkingID}")
	public List<Reservation> findById(@PathVariable Integer parkingID) {
		List<Reservation> resp = new ArrayList<>();
		Reservation reservation = new Reservation();
		Optional<Reservation> optionalReservation = reservationDAO.findById(parkingID);
		reservation = optionalReservation.get();
		resp.add(reservation);
		return resp;
	}

}
