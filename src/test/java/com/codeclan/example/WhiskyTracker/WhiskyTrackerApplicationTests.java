package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void canFindWhiskyByYear(){
		List<Whisky> found = whiskyRepository.findByYear(2018);
		assertEquals(2, found.size());
	}

	@Test
	public void canFindDistilleryRegion(){
		List<Distillery> found = distilleryRepository.findByRegion("Highland");
		assertEquals(1, found.size());
	}

	@Test
	public void canFindAllWhiskyByDistilleryThatsASpecificAge(){
		List<Whisky> found = whiskyRepository.findByDistilleryNameAndAge("Macallan", 25);
		assertEquals(1, found.size());
	}

	@Test
	public void canFindWhiskysByDistillery(){
		List<Whisky> found = whiskyRepository.findByDistilleryRegion("Highland");
		assertEquals(2, found.size());
	}

	@Test
	public void canFindDistilleryWithWhiskyOver12(){
		List<Distillery> found = distilleryRepository.findByWhiskiesAgeGreaterThan(12);
		assertEquals(2, found.size());
	}

}
