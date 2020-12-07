package com.apparquear.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.apparquear.model.Reservation;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM Reservation WHERE userID = ?1", nativeQuery = true)
    List<Reservation> findAllByUserID(Integer userID);

}