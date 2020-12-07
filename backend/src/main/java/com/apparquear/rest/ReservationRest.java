package com.apparquear.rest;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.apparquear.dao.ParkingDAO;
//import com.apparquear.dao.ParkingDAO;
import com.apparquear.dao.ReservationDAO;
import com.apparquear.model.Parking;
//import com.apparquear.model.Parking;
import com.apparquear.model.Reservation;
import com.apparquear.exception.ApiRequestException;
import java.util.Date;
import java.sql.Timestamp;

@RestController
@RequestMapping("apparquear/reservation")
public class ReservationRest {
	@Autowired
	private ReservationDAO reservationDAO;
	@Autowired
	private ParkingDAO parkingDAO;

	// Devuelve las reservaciones por Usuario
	@CrossOrigin
	@GetMapping("/findByUser/{userID}")
	public List<Reservation> findByUser(@PathVariable Integer userID) {
		List<Reservation> optionalReservation = reservationDAO.findAllByUserID(userID);
		return optionalReservation;
	}

	//Reserva y actualizaciòn de disponibilidad
	@CrossOrigin
	@PostMapping("/save/{userID}/{parkingID}/{token}")
	public void save(@PathVariable Integer userID, @PathVariable Long parkingID, @PathVariable String token,
			@RequestBody Reservation reservation) {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		Parking parking;
		
		try {
			
			if (ts.before(reservation.getReservation_time()) && reservation.getFinal_time().after(reservation.getReservation_time())) {
				reservation.setUser_ID(userID);
				reservation.setParking_ID(parkingID);
				reservation.setReservation_time(reservation.getReservation_time());
				reservation.setFinal_time(reservation.getFinal_time());
				reservation.setVehicle_type(reservation.getVehicle_type());
				reservationDAO.save(reservation);
				
				try {
					parking = parkingDAO.findByParkingID(reservation.getParking_ID());
					if (parking!=null){
						if (reservation.getVehicle_type().equals("Carro"))parking.setCar_spaces_available(parking.getCar_spaces_available()-1);
						if (reservation.getVehicle_type().equals("Moto"))parking.setMotorcycle_spaces_available(parking.getMotorcycle_spaces_available()-1);
						if (reservation.getVehicle_type().equals("Bicicleta"))parking.setBike_spaces_available(parking.getBike_spaces_available()-1);
						parking.setTotal_spaces_available(parking.getTotal_spaces_available()-1);
						parkingDAO.save(parking);
					}
				} catch (Exception e){
					throw new ApiRequestException("No se encontro el parqueadero con el id"+reservation.getParking_ID());
				}

			}
		} catch (Exception e) {
			throw new ApiRequestException(e.getMessage());
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

