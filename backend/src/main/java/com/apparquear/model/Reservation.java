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
	private Integer reservation_ID;
	
	@Column
	private Integer parking_ID;

	@Column
	private Integer user_ID;

	@ManyToOne
	@JoinColumn(name = "user_ID", referencedColumnName = "user_ID", insertable = false, updatable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "parking_ID", referencedColumnName = "parking_ID", insertable = false, updatable = false)
	private Parking parking;
	
	@Column
	private Timestamp reservation_time;

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

	public Integer getReservation_ID() {
		return reservation_ID;
	}

	public void setReservation_ID(Integer reservation_ID) {
		this.reservation_ID = reservation_ID;
	}

	public Integer getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(Integer user_ID) {
		this.user_ID = user_ID;
    }

    public Integer getParking_ID() {
		return parking_ID;
	}

	public void setParking_ID(Integer parking_ID) {
		this.parking_ID = parking_ID;
    }
    

	public Timestamp getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(Timestamp  reservation_time) {
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