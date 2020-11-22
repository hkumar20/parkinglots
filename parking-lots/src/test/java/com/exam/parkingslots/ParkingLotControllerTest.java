package com.exam.parkingslots;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.exam.parkinglots.controller.ParkingLotController;
import com.exam.parkinglots.dto.ParkingResponse;
import com.exam.parkinglots.model.ParkingArea;
import com.exam.parkinglots.model.ParkingLot;
import com.exam.parkinglots.service.ParkingLotsService;

/**
 * @author Hemanth
 *
 */
public class ParkingLotControllerTest {

	@InjectMocks
	private ParkingLotController controller;

	@Mock
	private ParkingLotsService service;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAvailableParkingLots() {
		List<ParkingLot> value = new ArrayList<>();
		ParkingLot lot = new ParkingLot();
		lot.setId(1);
		value.add(lot);
		Mockito.when(service.getAllLots()).thenReturn(value);
		ResponseEntity<ParkingResponse<List<ParkingLot>>> result = controller.getParkingLots();
		assertEquals(result.getBody().getMessage(), "Parking lots are available");
	}

	@Test
	public void testGetNotAvailableParkingLots() {
		Mockito.when(service.getAllLots()).thenReturn(null);
		ResponseEntity<ParkingResponse<List<ParkingLot>>> result = controller.getParkingLots();
		assertEquals(result.getBody().getMessage(), "No Parking lots available");
	}

	@Test
	public void testGetAvailableParkingAreas() {
		List<ParkingArea> value = new ArrayList<>();
		ParkingArea area = new ParkingArea();
		area.setId(1);
		value.add(area);
		Mockito.when(service.getAllParkingAreas()).thenReturn(value);
		ResponseEntity<ParkingResponse<List<ParkingArea>>> result = controller.getParkingAreas();
		assertEquals(result.getBody().getMessage(), "Parking areas are available");
	}

	@Test
	public void testGetNotAvailableParkingAreas() {
		Mockito.when(service.getAllParkingAreas()).thenReturn(null);
		ResponseEntity<ParkingResponse<List<ParkingArea>>> result = controller.getParkingAreas();
		assertEquals(result.getBody().getMessage(), "No Parking areas available");
	}

	@Test
	public void getAvailableParkingAreaByZipCode() {
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setId(1);
		parkingArea.setZipcode("11111");
		String zipcode = "11111";
		Mockito.when(service.getAreaByZipcode(zipcode)).thenReturn(parkingArea);
		ResponseEntity<ParkingResponse<ParkingArea>> result = controller.getParkingAreaByZipCode(zipcode);
		assertEquals(result.getBody().getMessage(), "Parking Lots retrieved from Area");				
	}

	@Test
	public void getNotAvailableParkingAreaByZipCode() {
		String zipcode = "11111";
		Mockito.when(service.getAreaByZipcode(zipcode)).thenReturn(null);
		ResponseEntity<ParkingResponse<ParkingArea>> result = controller.getParkingAreaByZipCode(zipcode);
		assertEquals(result.getBody().getMessage(), "No area is found for given zipcode: " + zipcode);				
	}

	@Test
	public void getAvailableParkingAreaByStreetAndCity() {
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setId(1);
		parkingArea.setStreet("street1");
		parkingArea.setCity("city1");
		parkingArea.setZipcode("11111");
		String street = "street1";
		String city = "city1";
		Mockito.when(service.getAreaByStreetAndCity(street, city)).thenReturn(parkingArea);
		ResponseEntity<ParkingResponse<ParkingArea>> result = controller.getParkingAreaByStreetAndCity(street, city);
		assertEquals(result.getBody().getMessage(), "Parkting Lots retrieved from Area");				
	}

	@Test
	public void getNotAvailableParkingAreaByStreetAndCity() {
		String street = "street1";
		String city = "city1";
		Mockito.when(service.getAreaByStreetAndCity(street, city)).thenReturn(null);
		ResponseEntity<ParkingResponse<ParkingArea>> result = controller.getParkingAreaByStreetAndCity(street, city);
		assertEquals(result.getBody().getMessage(), String.format("No area is found for given Street: %s and City: %s", street, city));				
	}
}
