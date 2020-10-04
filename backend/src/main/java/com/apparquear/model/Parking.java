package com.apparquear.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long parking_ID;
	
	@Column
	private Long user_ID;

	@ManyToOne
	@JoinColumn(name = "user_ID", referencedColumnName = "user_ID", insertable = false, updatable = false)
	private User user;
	
	@Column
	private String parking_name;
	
	@Column
	private Time opening_time;
	
	@Column
	private Time closing_time;
	
	@Column
	private Integer total_spaces;
	
	@Column
	private Integer car_spaces;
	
	@Column
	private Integer bike_spaces;
	
	@Column
	private Integer motorcycle_spaces;
	
	@Column
	private Integer total_spaces_available;
	
	@Column
	private Integer car_spaces_available;
	
	@Column
	private Integer bike_spaces_available;
	
	@Column
	private Integer motorcycle_spaces_available;
	
	@Column
	private Double bike_cost_minute;
	
	@Column
	private Double car_cost_minute;
	
	@Column
	private Double motorcycle_cost_minute;
	
	@Column
	private Double score;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getParking_ID() {
		return parking_ID;
	}

	public void setParking_ID(Long parking_ID) {
		this.parking_ID = parking_ID;
	}

	public Long getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(Long user_ID) {
		this.user_ID = user_ID;
	}

	public String getParking_name() {
		return parking_name;
	}

	public void setParking_name(String parking_name) {
		this.parking_name = parking_name;
	}

	public Time getOpening_time() {
		return opening_time;
	}

	public void setOpening_time(Time opening_time) {
		this.opening_time = opening_time;
	}

	public Time getClosing_time() {
		return closing_time;
	}

	public void setClosing_time(Time closing_time) {
		this.closing_time = closing_time;
	}

	public Integer getTotal_spaces() {
		return total_spaces;
	}

	public void setTotal_spaces(Integer total_spaces) {
		this.total_spaces = total_spaces;
	}

	public Integer getCar_spaces() {
		return car_spaces;
	}

	public void setCar_spaces(Integer car_spaces) {
		this.car_spaces = car_spaces;
	}

	public Integer getBike_spaces() {
		return bike_spaces;
	}

	public void setBike_spaces(Integer bike_spaces) {
		this.bike_spaces = bike_spaces;
	}

	public Integer getMotorcycle_spaces() {
		return motorcycle_spaces;
	}

	public void setMotorcycle_spaces(Integer motorcycle_spaces) {
		this.motorcycle_spaces = motorcycle_spaces;
	}

	public Integer getTotal_spaces_available() {
		return total_spaces_available;
	}

	public void setTotal_spaces_available(Integer total_spaces_available) {
		this.total_spaces_available = total_spaces_available;
	}

	public Integer getCar_spaces_available() {
		return car_spaces_available;
	}

	public void setCar_spaces_available(Integer car_spaces_available) {
		this.car_spaces_available = car_spaces_available;
	}

	public Integer getBike_spaces_available() {
		return bike_spaces_available;
	}

	public void setBike_spaces_available(Integer bike_spaces_available) {
		this.bike_spaces_available = bike_spaces_available;
	}

	public Integer getMotorcycle_spaces_available() {
		return motorcycle_spaces_available;
	}

	public void setMotorcycle_spaces_available(Integer motorcycle_spaces_available) {
		this.motorcycle_spaces_available = motorcycle_spaces_available;
	}

	public Double getBike_cost_minute() {
		return bike_cost_minute;
	}

	public void setBike_cost_minute(Double bike_cost_minute) {
		this.bike_cost_minute = bike_cost_minute;
	}

	public Double getCar_cost_minute() {
		return car_cost_minute;
	}

	public void setCar_cost_minute(Double car_cost_minute) {
		this.car_cost_minute = car_cost_minute;
	}

	public Double getMotorcycle_cost_minute() {
		return motorcycle_cost_minute;
	}

	public void setMotorcycle_cost_minute(Double motorcycle_cost_minute) {
		this.motorcycle_cost_minute = motorcycle_cost_minute;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	

}
