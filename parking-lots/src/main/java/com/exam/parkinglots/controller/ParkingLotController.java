package com.exam.parkinglots.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.parkinglots.dto.ParkingResponse;
import com.exam.parkinglots.model.ParkingArea;
import com.exam.parkinglots.model.ParkingLot;
import com.exam.parkinglots.service.ParkingLotsService;

/**
 * @author Hemanth
 *
 */
@RestController
@RequestMapping(path = "/parking")
public class ParkingLotController {
	Logger log = LoggerFactory.getLogger(ParkingLotController.class);
	
	@Autowired
	private ParkingLotsService service;
	
	@GetMapping("/lots")
	public ResponseEntity<ParkingResponse<List<ParkingLot>>> getParkingLots() {
		log.info("Request received for getting all parking lots");
		ParkingResponse<List<ParkingLot>> response = new ParkingResponse<>();
		List<ParkingLot> parkingLots = service.getAllLots();
		if (parkingLots != null && !parkingLots.isEmpty()) {
			response.setMessage("Parking lots are available");
			response.setResponse(parkingLots);
		} else {
			response.setMessage("No Parking lots available");
		}
		log.info("Getting all parking lots has been processed successfully");
		return new ResponseEntity<ParkingResponse<List<ParkingLot>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/areas")
	public ResponseEntity<ParkingResponse<List<ParkingArea>>> getParkingAreas() {
		log.info("Request received for getting all parking areas");
		ParkingResponse<List<ParkingArea>> response = new ParkingResponse<>();
		List<ParkingArea> parkingAreas = service.getAllParkingAreas();
		if (parkingAreas != null && !parkingAreas.isEmpty()) {
			response.setMessage("Parking areas are available");
			response.setResponse(parkingAreas);
		} else {
			response.setMessage("No Parking areas available");
		}
		log.info("Getting all parking areas has been processed successfully");
		return new ResponseEntity<ParkingResponse<List<ParkingArea>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/area/{zipcode}")
	public ResponseEntity<ParkingResponse<ParkingArea>> getParkingAreaByZipCode(@PathVariable String zipcode) {
		log.info("Request received for getting Parking Area by zipcode {}", zipcode);
		ParkingArea parkingArea = service.getAreaByZipcode(zipcode);
		ParkingResponse<ParkingArea> response = new ParkingResponse<>();
		if (parkingArea != null) {
			response.setMessage("Parking Lots retrieved from Area");
			response.setResponse(parkingArea);
		} else {
			response.setMessage("No area is found for given zipcode: " + zipcode);
		}
		log.info("Find area by zipcode has been processed successfully");
		return new ResponseEntity<ParkingResponse<ParkingArea>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/area/{street}/{city}")
	public ResponseEntity<ParkingResponse<ParkingArea>> getParkingAreaByStreetAndCity(@PathVariable String street, @PathVariable String city) {
		log.info("Request received for getting Parking Area by Street {} and City {}", street, city);
		ParkingArea parkingArea = service.getAreaByStreetAndCity(street, city);
		ParkingResponse<ParkingArea> response = new ParkingResponse<>();
		if (parkingArea != null) {
			response.setMessage("Parkting Lots retrieved from Area");
			response.setResponse(parkingArea);
		} else {
			response.setMessage(String.format("No area is found for given Street: %s and City: %s", street, city));
		}
		log.info("Find area by street and city has been processed successfully");
		return new ResponseEntity<ParkingResponse<ParkingArea>>(response, HttpStatus.OK);
	}
}
