package com.evolvice.cars.service.endPoints.component;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.evolvice.cars.service.dto.AddNewCarDTO;
import com.evolvice.cars.service.dto.EditOldCarDTO;
import com.evolvice.cars.service.endPoints.representation.CarRepresentation;
import com.evolvice.cars.service.exceptions.CarAlreadyFoundException;
import com.evolvice.cars.service.exceptions.NoCarFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarsServiceComponentTest {

	@Autowired
	private CarsServiceComponent carsServiceComponent;

	@Test
	public void test_findAllCars_HappySenario() {

		List<CarRepresentation> allCars = carsServiceComponent.findAllCars();

		Assert.assertNotNull(allCars);

	}

	@Test
	public void test_addNewCar_HappySenario() {

		AddNewCarDTO newCarDTO = new AddNewCarDTO();

		newCarDTO.setModel("Bunto");
		newCarDTO.setYearOfProduction("2018");
		newCarDTO.setModelDetials("model Details");
		newCarDTO.setChassisNumber("147852");
		newCarDTO.setBrand("Fiat");

		carsServiceComponent.addNewCar(newCarDTO);

		List<CarRepresentation> allCars = carsServiceComponent.findAllCars();

		Assert.assertEquals(5, allCars.size());

	}

	@Test(expected = CarAlreadyFoundException.class)
	public void test_addNewCar_throw_CarAlreadyFoundException() {

		AddNewCarDTO newCarDTO = new AddNewCarDTO();

		newCarDTO.setModel("Bunto");
		newCarDTO.setYearOfProduction("2018");
		newCarDTO.setModelDetials("model Details");
		newCarDTO.setChassisNumber("123456");
		newCarDTO.setBrand("Fiat");

		carsServiceComponent.addNewCar(newCarDTO);

	}

	@Test
	public void test_findOneByChassisNumber_HappySenario() {

		CarRepresentation oldCar = carsServiceComponent.findOneByChassisNumber("123456");

		Assert.assertNotNull(oldCar);
		Assert.assertEquals(10, oldCar.getCarId());

	}

	@Test(expected = NoCarFoundException.class)
	public void test_findOneByChassisNumber_throw_NoCarFoundException() {

		carsServiceComponent.findOneByChassisNumber("1234567845");

	}

	@Test
	public void test_findOneByItsId_HappySenario() {

		CarRepresentation oldCar = carsServiceComponent.findOneByItsId(10L);

		Assert.assertNotNull(oldCar);
		Assert.assertEquals("123456", oldCar.getChassisNumber());

	}

	@Test(expected = NoCarFoundException.class)
	public void test_findOneByItsId_throw_NoCarFoundException() {

		carsServiceComponent.findOneByItsId(20L);

	}

	@Test
	public void test_editOldCar_HappySenario() {

		EditOldCarDTO editOldCarDTO = new EditOldCarDTO();

		editOldCarDTO.setCarId(10L);
		editOldCarDTO.setModel("Bunto");
		editOldCarDTO.setYearOfProduction("2018");
		editOldCarDTO.setModelDetials("model Details");
		editOldCarDTO.setChassisNumber("123456");
		editOldCarDTO.setBrand("Fiat");

		carsServiceComponent.editOldCar(editOldCarDTO);

		CarRepresentation oldCar = carsServiceComponent.findOneByChassisNumber("123456");

		Assert.assertEquals("Fiat", oldCar.getBrand());

	}

	@Test(expected = CarAlreadyFoundException.class)
	public void test_editOldCar_throw_CarAlreadyFoundException() {

		EditOldCarDTO editOldCarDTO = new EditOldCarDTO();

		editOldCarDTO.setCarId(10L);
		editOldCarDTO.setModel("Bunto");
		editOldCarDTO.setYearOfProduction("2018");
		editOldCarDTO.setModelDetials("model Details");
		editOldCarDTO.setChassisNumber("1234567");
		editOldCarDTO.setBrand("Fiat");

		carsServiceComponent.editOldCar(editOldCarDTO);
	}


	@Test(expected = NoCarFoundException.class)
	public void test_deleteOldCarByChassisNumber_throw_NoCarFoundException() {

		carsServiceComponent.deleteOldCarByChassisNumber("1234567896");

	}

	@Test(expected = NoCarFoundException.class)
	public void test_deleteOldCarById_throw_NoCarFoundException() {

		carsServiceComponent.deleteOldCarByItsId(50L);

	}

	
	
}
