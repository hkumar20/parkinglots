package com.exam.parkinglots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.parkinglots.dao.ParkingAreaRepository;
import com.exam.parkinglots.dao.ParkingLotRepository;
import com.exam.parkinglots.model.ParkingArea;
import com.exam.parkinglots.model.ParkingLot;

@SpringBootApplication
public class ParkingslotsApplication implements CommandLineRunner {
	@Autowired
	ParkingAreaRepository areaRespository;
	
	@Autowired
	ParkingLotRepository lotRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParkingslotsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ParkingArea area1 = new ParkingArea(); 
		area1.setCity("City1");
		area1.setStreet("Street1");
		area1.setZipcode("11111");
		areaRespository.save(area1);
		ParkingArea area2 = new ParkingArea();
		area2.setCity("City2");
		area2.setStreet("Street2");
		area2.setZipcode("22222");
		areaRespository.save(area2);
		ParkingArea area3 = new ParkingArea();
		area3.setCity("City3");
		area3.setStreet("Street3");
		area3.setZipcode("33333");
		areaRespository.save(area3);
		
		ParkingLot lot1 = new ParkingLot();
		lot1.setIsAvailable("Yes");
		lot1.setParkingArea(area1);
		lotRepository.save(lot1);
		ParkingLot lot2 = new ParkingLot();
		lot2.setIsAvailable("No");
		lot2.setParkingArea(area1);
		lotRepository.save(lot2);
		ParkingLot lot3 = new ParkingLot();
		lot3.setIsAvailable("Yes");
		lot3.setParkingArea(area1);
		lotRepository.save(lot3);
		ParkingLot lot4 = new ParkingLot();
		lot4.setIsAvailable("No");
		lot4.setParkingArea(area2);
		lotRepository.save(lot4);
		ParkingLot lot5 = new ParkingLot();
		lot5.setIsAvailable("Yes");
		lot5.setParkingArea(area2);
		lotRepository.save(lot5);
		ParkingLot lot6 = new ParkingLot();
		lot6.setIsAvailable("Yes");
		lot6.setParkingArea(area2);
		lotRepository.save(lot6);
		ParkingLot lot7 = new ParkingLot();
		lot7.setIsAvailable("Yes");
		lot7.setParkingArea(area3);
		lotRepository.save(lot7);
		ParkingLot lot8 = new ParkingLot();
		lot8.setIsAvailable("No");
		lot8.setParkingArea(area3);
		lotRepository.save(lot8);
		ParkingLot lot9 = new ParkingLot();
		lot9.setIsAvailable("Yes");
		lot9.setParkingArea(area3);
		lotRepository.save(lot9);
	}
}
