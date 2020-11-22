package com.exam.parkinglots.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.parkinglots.model.ParkingLot;

/**
 * @author Hemanth
 *
 */
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

}
