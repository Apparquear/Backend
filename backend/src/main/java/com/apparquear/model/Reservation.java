package com.apparquear.model;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationID;
	
	@Column
	private Long parkingID;

	@Column
	private Integer userID;

	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "parkingID", referencedColumnName = "parkingID", insertable = false, updatable = false)
	private Parking parking;
	
	@Column
	private Timestamp reservation_time;

	@Column
	private Timestamp final_time;
	
	@Column
	private String vehicle_type;
	// set and get

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
    }
    
    public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public Integer getReservationID() {
		return reservationID;
	}

	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUser_ID(Integer userID) {
		this.userID = userID;
    }

    public Long getParking_ID() {
		return parkingID;
	}

	public void setParking_ID(Long parkingID) {
		this.parkingID = parkingID;
    }
    

	public Timestamp getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(Timestamp  reservation_time) {
		this.reservation_time = reservation_time;
    }
    
    public Timestamp getFinal_time() {
		return final_time;
	}

	public void setFinal_time(Timestamp final_time) {
		this.final_time = final_time;
	}


	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	

}