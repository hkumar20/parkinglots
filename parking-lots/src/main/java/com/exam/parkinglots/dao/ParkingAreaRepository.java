package com.exam.parkinglots.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.parkinglots.model.ParkingArea;

/**
 * @author Hemanth
 *
 */
public interface ParkingAreaRepository extends JpaRepository<ParkingArea, Long> {
	public final static String GET_AREA_BY_ZIPCODE = "SELECT area FROM ParkingArea area WHERE zipcode = :zipcode";
	public final static String GET_AREA_BY_STREET_CITY = "SELECT area FROM ParkingArea area WHERE street = :street AND city = :city";

	@Query(GET_AREA_BY_ZIPCODE)
	ParkingArea findByZipcode(@Param("zipcode") final String zipcode);
	
	@Query(GET_AREA_BY_STREET_CITY)
	ParkingArea findByStreetAndCity(@Param("street") final String street, @Param("city") final String city);
}
