package com.evolvice.cars.service.endPoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evolvice.cars.service.dto.AddNewCarDTO;
import com.evolvice.cars.service.dto.DeleteOldCarDTO;
import com.evolvice.cars.service.dto.EditOldCarDTO;
import com.evolvice.cars.service.endPoints.component.CarsServiceComponent;
import com.evolvice.cars.service.endPoints.representation.CarRepresentation;
import com.evolvice.cars.service.exceptions.CarAlreadyFoundException;
import com.evolvice.cars.service.exceptions.CommonEvolviceException;
import com.evolvice.cars.service.exceptions.NoCarFoundException;

import io.swagger.annotations.Api;

@RestController
@Api
public class CarsServiceEndPoints {

	@Autowired
	private CarsServiceComponent carsServiceComponent;

	@RequestMapping(value = "/cars/all", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getAllCars() {

		List<CarRepresentation> allCars = carsServiceComponent.findAllCars();
		return new ResponseEntity<List<CarRepresentation>>(allCars, HttpStatus.OK);
	}

	@RequestMapping(value = "/car/find/{chassisNumber}/chassisNumber", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> findOneCarByChassisNumber(@PathVariable String chassisNumber) {

		CarRepresentation representation = carsServiceComponent.findOneByChassisNumber(chassisNumber);
		return new ResponseEntity<CarRepresentation>(representation, HttpStatus.OK);
	}

	@RequestMapping(value = "/car/find/{carId}/carId", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> findOneCarById(@PathVariable Long carId) {

		CarRepresentation representation = carsServiceComponent.findOneByItsId(carId);
		return new ResponseEntity<CarRepresentation>(representation, HttpStatus.OK);
	}

	@RequestMapping(value = "/car/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> addNewCar(@RequestBody AddNewCarDTO newCar) {

		carsServiceComponent.addNewCar(newCar);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/car/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> editOldCar(@RequestBody EditOldCarDTO editOldCarDTO) {

		carsServiceComponent.editOldCar(editOldCarDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/car/delete/chassisNumber", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOneCarByChassisNumber(@RequestBody DeleteOldCarDTO deleteOldCarDTO) {

		carsServiceComponent.deleteOldCarByChassisNumber(deleteOldCarDTO.getChassisNumber());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/car/delete/carId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOneCarById(@RequestBody DeleteOldCarDTO deleteOldCarDTO) {

		carsServiceComponent.deleteOldCarByItsId(deleteOldCarDTO.getCarId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY, reason = "0001")
	@ExceptionHandler(value = CarAlreadyFoundException.class)
	public void carAlreadyFoundExceptionHandler() {

	}

	@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY, reason = "0002")
	@ExceptionHandler(value = CommonEvolviceException.class)
	public void commonEvolviceExceptionHandler() {

	}

	@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY, reason = "0003")
	@ExceptionHandler(value = NoCarFoundException.class)
	public void noCarFoundExceptionHandler() {

	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "0004")
	@ExceptionHandler(value = Exception.class)
	public void unKnouwExceptionHandler() {

	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "0005")
	@ExceptionHandler(value = RuntimeException.class)
	public void unKnouwRunTimeExceptionHandler() {

	}

}
