package com.exam.parkinglots.service;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.parkinglots.dao.ParkingAreaRepository;
import com.exam.parkinglots.dao.ParkingLotRepository;
import com.exam.parkinglots.model.ParkingArea;
import com.exam.parkinglots.model.ParkingLot;

/**
 * @author Hemanth
 *
 */
@Service
public class ParkingLotsService {
	Logger log = LoggerFactory.getLogger(ParkingLotsService.class);
	
	private static final Predicate<ParkingLot> availableLotPredicate = new Predicate<ParkingLot>() {
		@Override
		public boolean test(ParkingLot parkingLot) {
			return "Yes".equalsIgnoreCase(parkingLot.getIsAvailable());
		}
	};
	
	@Autowired
	ParkingAreaRepository areaRepository; 
	
	@Autowired
	ParkingLotRepository lotRepository;
	
	private List<ParkingLot> filterAvailableLots(List<ParkingLot> parkingLots) {
		List<ParkingLot> availableLots = parkingLots.stream().filter(availableLotPredicate).collect(Collectors.toList());
		return availableLots;
	}
	
	public List<ParkingLot> getAllLots() {
		List<ParkingLot> lots = lotRepository.findAll();
		if (lots != null && !lots.isEmpty()) {
			List<ParkingLot> availableLots = filterAvailableLots(lots);
			log.info("Total lots: {}, Available lots: {}", lots.size(), availableLots.size());
			return availableLots;
		}
		log.info("No parking lots avaiable");
		return Collections.<ParkingLot>emptyList();
	}
	
	public List<ParkingArea> getAllParkingAreas() {
		List<ParkingArea> areas = areaRepository.findAll();
		if (areas != null && !areas.isEmpty()) {
			log.info("Total areas: {}", areas.size());
			return areas;
		}
		log.info("No parking areas avaiable");
		return Collections.<ParkingArea>emptyList();
	}
	
	public ParkingArea getAreaByZipcode(String zipcode) {
		ParkingArea parkingArea = areaRepository.findByZipcode(zipcode);
		if (parkingArea != null) {
			List<ParkingLot> availableLots = filterAvailableLots(parkingArea.getParkingLots());
			if (availableLots != null && !availableLots.isEmpty()) {
				parkingArea.setParkingLots(availableLots);
				return parkingArea;
			} else {
				log.info("No Parking lots found for zipcode");
			}
		}
		log.info("No area found for zipcode");
		return null;
	}
	
	public ParkingArea getAreaByStreetAndCity(String street, String city) {
		ParkingArea parkingArea = areaRepository.findByStreetAndCity(street, city);
		if (parkingArea != null) {
			List<ParkingLot> availableLots = filterAvailableLots(parkingArea.getParkingLots());
			if (availableLots != null && !availableLots.isEmpty()) {
				parkingArea.setParkingLots(availableLots);
				return parkingArea;
			} else {
				log.info("No Parking lots found for given Street and City");
			}
		}
		log.info("No area found for given Street and City");
		return null;
	}
}
