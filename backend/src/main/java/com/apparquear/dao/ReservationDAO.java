package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apparquear.model.Reservation;

public interface ReservationDAO extends JpaRepository<Reservation, Long> {
}