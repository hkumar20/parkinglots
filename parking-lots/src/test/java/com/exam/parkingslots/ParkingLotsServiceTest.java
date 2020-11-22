package com.exam.parkingslots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.exam.parkinglots.dao.ParkingAreaRepository;
import com.exam.parkinglots.dao.ParkingLotRepository;
import com.exam.parkinglots.model.ParkingArea;
import com.exam.parkinglots.model.ParkingLot;
import com.exam.parkinglots.service.ParkingLotsService;

/**
 * @author Hemanth
 *
 */
public class ParkingLotsServiceTest {
	@InjectMocks
	private ParkingLotsService service;
	
	@Mock
	private ParkingAreaRepository areaRepository;
	
	@Mock
	private ParkingLotRepository lotRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAvailableGetAllLots() {
		List<ParkingLot> lots = new ArrayList<>();
		ParkingLot lot = new ParkingLot();
		lot.setId(1);
		lot.setIsAvailable("Yes");
		lots.add(lot);
		Mockito.when(lotRepository.findAll()).thenReturn(lots);
		List<ParkingLot> result = service.getAllLots();
		assertEquals(result.size(), 1);
	}
	
	@Test
	public void testNotAvailableGetAllLots() {
		Mockito.when(lotRepository.findAll()).thenReturn(null);
		List<ParkingLot> result = service.getAllLots();
		assertEquals(result.size(), 0);
	}
	
	@Test
	public void testAvailableGetAllParkingAreas() {
		List<ParkingArea> areas = new ArrayList<>();
		ParkingArea area = new ParkingArea();
		areas.add(area);
		Mockito.when(areaRepository.findAll()).thenReturn(areas);
		List<ParkingArea> result = service.getAllParkingAreas();
		assertEquals(result.size(), 1);
	}
	
	@Test
	public void testNotAvailableGetAllParkingAreas() {
		Mockito.when(areaRepository.findAll()).thenReturn(null);
		List<ParkingArea> result = service.getAllParkingAreas();
		assertEquals(result.size(), 0);
	}
	
	@Test
	public void testGetAreaByZipcode() {
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setId(1);
		parkingArea.setZipcode("11111");
		ParkingLot lot = new ParkingLot();
		lot.setId(1);
		lot.setIsAvailable("Yes");
		parkingArea.setParkingLots(new ArrayList<ParkingLot>());
		parkingArea.getParkingLots().add(lot);
		String zipcode = "11111";
		Mockito.when(areaRepository.findByZipcode(zipcode)).thenReturn(parkingArea);
		ParkingArea result = service.getAreaByZipcode(zipcode);
		assertEquals(result.getZipcode(), "11111");
	}
	
	@Test
	public void testGetAreaByZipcodeButNoAvailableLot() {
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setId(1);
		parkingArea.setZipcode("11111");
		ParkingLot lot = new ParkingLot();
		lot.setId(1);
		lot.setIsAvailable("No");
		parkingArea.setParkingLots(new ArrayList<ParkingLot>());
		parkingArea.getParkingLots().add(lot);
		String zipcode = "11111";
		Mockito.when(areaRepository.findByZipcode(zipcode)).thenReturn(parkingArea);
		ParkingArea result = service.getAreaByZipcode(zipcode);
		assertNull(result);
	}
	
	@Test
	public void testGetAreaByStreetAndCity() {
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setId(1);
		parkingArea.setStreet("Street1");
		parkingArea.setCity("City1");
		parkingArea.setZipcode("11111");
		ParkingLot lot = new ParkingLot();
		lot.setId(1);
		lot.setIsAvailable("Yes");
		parkingArea.setParkingLots(new ArrayList<ParkingLot>());
		parkingArea.getParkingLots().add(lot);
		String street = "Street1";
		String city = "City1";
		Mockito.when(areaRepository.findByStreetAndCity(street, city)).thenReturn(parkingArea);
		ParkingArea result = service.getAreaByStreetAndCity(street, city);
		assertEquals(result.getStreet(), "Street1");
		assertEquals(result.getCity(), "City1");
	}
	
	@Test
	public void testGetAreaByStreetAndCityButNoAvailableLot() {
		ParkingArea parkingArea = new ParkingArea();
		parkingArea.setId(1);
		parkingArea.setStreet("Street1");
		parkingArea.setCity("City1");
		parkingArea.setZipcode("11111");
		ParkingLot lot = new ParkingLot();
		lot.setId(1);
		lot.setIsAvailable("No");
		parkingArea.setParkingLots(new ArrayList<ParkingLot>());
		parkingArea.getParkingLots().add(lot);
		String street = "Street1";
		String city = "City1";
		Mockito.when(areaRepository.findByStreetAndCity(street, city)).thenReturn(parkingArea);
		ParkingArea result = service.getAreaByStreetAndCity(street, city);
		assertNull(result);
	}
}
