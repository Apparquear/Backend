package com.apparquear.model;

import java.sql.Date;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reservation_ID;
	
	@Column
	private Long parking_ID;

	@Column
	private Long user_ID;

	@ManyToOne
	@JoinColumn(name = "user_ID", referencedColumnName = "user_ID", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "parking_ID", referencedColumnName = "parking_ID", insertable = false, updatable = false)
	private Parking parking;
	
	@Column
	private Date reservation_time;

	@Column
	private Double reservation_duration;
	
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

	public void setReservation_ID(Long reservation_ID) {
		this.reservation_ID = reservation_ID;
	}

	public Long getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(Long user_ID) {
		this.user_ID = user_ID;
    }

    public Long getParking_ID() {
		return parking_ID;
	}

	public void setParking_ID(Long parking_ID) {
		this.parking_ID = parking_ID;
    }
    

	public Date getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(Date reservation_time) {
		this.reservation_time = reservation_time;
    }
    
    public Double getReservation_duration() {
		return reservation_duration;
	}

	public void setReservation_duration(Double reservation_duration) {
		this.reservation_duration = reservation_duration;
	}


	public String getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	

}