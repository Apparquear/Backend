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

@RestController
@RequestMapping("apparquear/reservation")
public class ReservationRest {
	@Autowired
	private ReservationDAO reservationDAO;

	// Methods
	@CrossOrigin
	@PostMapping("/save/{userID}/{parkingID}/{token}")
	public void save(@PathVariable Integer userID,@PathVariable Integer parkingID, @PathVariable String token, @RequestBody Reservation reservation) {
		reservation.setUser_ID(userID);
		reservation.setParking_ID(parkingID);
		reservation.setReservation_time(reservation.getReservation_time());
        reservation.setFinal_time(reservation.getFinal_time());
        reservation.setVehicle_type(reservation.getVehicle_type());

        reservationDAO.save(reservation);        
	}

	//Busca reservaciones por id de parqueadero
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
