package com.evolvice.cars.service.endPoints;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.evolvice.cars.service.dto.AddNewCarDTO;
import com.evolvice.cars.service.endPoints.representation.CarRepresentation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EndPoinsIntergationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private String getAllCars = "/cars/all";

	private String addNewCarURL = "/car/new";

	private String findOneCarByChassisNumberURL = "/car/find/123456/chassisNumber";

	private String findOneCarByItsIdURL = "/car/find/10/carId";

	private HttpEntity<AddNewCarDTO> requestAddNewCarEntity;

	private HttpEntity<String> request;

	@Test
	public void testSuccessfulFlow_findOneByChassisNumber() throws InterruptedException {

		ResponseEntity<CarRepresentation> getCarResponseEntity = testRestTemplate.exchange(findOneCarByChassisNumberURL,
				HttpMethod.GET, request, CarRepresentation.class);

		assertThat(getCarResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getCarResponseEntity.getBody().getCarId() == 10L);

	}

	@Before
	public void init() {

		requestAddNewCarEntity = new HttpEntity<>(getAddNewCarDTO());

	}

	@Test
	public void testSuccessfulFlow_findOneByCarId() throws InterruptedException {

		ResponseEntity<CarRepresentation> getCarResponseEntity = testRestTemplate.exchange(findOneCarByItsIdURL,
				HttpMethod.GET, request, CarRepresentation.class);

		assertThat(getCarResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getCarResponseEntity.getBody().getCarId() == 10L);

	}

	@Test
	public void testSuccessfulFlow_getAllCars() throws InterruptedException {

		ResponseEntity<CarRepresentation[]> getCarResponseEntity = testRestTemplate.exchange(getAllCars, HttpMethod.GET,
				request, CarRepresentation[].class);

		assertThat(getCarResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getCarResponseEntity.getBody().length == 4);

	}

	@Test
	public void testSuccessfulFlow_AddNewCar() throws InterruptedException {

		ResponseEntity<Void> getCarResponseEntity = testRestTemplate.exchange(addNewCarURL, HttpMethod.POST,
				requestAddNewCarEntity, Void.class);

		assertThat(getCarResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	private AddNewCarDTO getAddNewCarDTO() {

		AddNewCarDTO newCarDTO = new AddNewCarDTO();

		newCarDTO.setModel("Bunto");
		newCarDTO.setYearOfProduction("2018");
		newCarDTO.setModelDetials("model Details");
		newCarDTO.setChassisNumber("147852");
		newCarDTO.setBrand("Fiat");
		return newCarDTO;
	}

}
