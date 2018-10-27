package com.evolvice.cars.service.endPoints.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.evolvice.cars.service.dto.AddNewCarDTO;
import com.evolvice.cars.service.dto.EditOldCarDTO;
import com.evolvice.cars.service.endPoints.representation.CarRepresentation;
import com.evolvice.cars.service.exceptions.CarAlreadyFoundException;
import com.evolvice.cars.service.exceptions.CommonEvolviceException;
import com.evolvice.cars.service.exceptions.NoCarFoundException;
import com.evolvice.cars.service.model.CarModel;
import com.evolvice.cars.service.model.dao.CarModelDao;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarsServiceComponent {

	private static final Logger logger = LoggerFactory.getLogger(CarsServiceComponent.class);

	@Autowired
	private CarModelDao carModelDao;

	public List<CarRepresentation> findAllCars() {

		logger.info("Get All Cars Called");

		List<CarModel> allCars = new ArrayList<>();

		try {
			allCars = carModelDao.findAll();
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		logger.info("Success to Get All Cars and Now convert it to is Representition");
		List<CarRepresentation> carsRepresentition = new ArrayList<>();

		for (CarModel i : allCars) {
			CarRepresentation car = new CarRepresentation();
			car = CarRepresentation.convertCarModelToRepresentation(i);
			carsRepresentition.add(car);

		}

		return carsRepresentition;
	}

	public void addNewCar(AddNewCarDTO newCar) {

		logger.info("Add New Car Called");

		CarModel oldCar = null;

		logger.info("Try to find old Car with the givin ChassisNumber [{}]", newCar.getChassisNumber());

		try {
			oldCar = carModelDao.findOneByChassisNumber(newCar.getChassisNumber());
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		if (oldCar != null) {

			logger.error("An old Car found with the givin ChassisNumber [{}]", newCar.getChassisNumber());

			throw new CarAlreadyFoundException("There is Car with the Given Chassis Number");
		}

		logger.info("No old Car found with the givin ChassisNumber [{}] We Will Add the Givin Car ",
				newCar.getChassisNumber());

		CarModel carModel = new CarModel();
		carModel.setBrand(newCar.getBrand());
		carModel.setModel(newCar.getModel());
		carModel.setModelDetials(newCar.getModelDetials());
		carModel.setYearOfProduction(newCar.getYearOfProduction());
		carModel.setChassisNumber(newCar.getChassisNumber());

		try {
			carModelDao.save(carModel);
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");

		}
	}

	public CarRepresentation findOneByChassisNumber(String chassisNumber) {

		logger.info("find One Car By Chassis Number Called");

		CarModel car = null;

		logger.info("Try to find old Car with the givin ChassisNumber [{}]", chassisNumber);

		try {
			car = carModelDao.findOneByChassisNumber(chassisNumber);
			Preconditions.checkNotNull(car);
		} catch (NullPointerException e) {
			logger.error("No old Car found with the givin ChassisNumber [{}]", chassisNumber);
			throw new NoCarFoundException("No Car Found with the giving Chassis Number");
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		logger.info("An old Car found with the givin ChassisNumber [{}] and Will Return it", chassisNumber);

		return CarRepresentation.convertCarModelToRepresentation(car);
	}

	public CarRepresentation findOneByItsId(Long carId) {

		logger.info("find One Car By Car ID Called");

		CarModel car = null;

		try {
			car = carModelDao.findOne(carId);
			Preconditions.checkNotNull(car);
		} catch (NullPointerException e) {
			logger.error("No old Car found with the givin Car ID [{}]", carId);
			throw new NoCarFoundException("No Car Found with the giving Chassis Number");
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		logger.info("An old Car found with the givin Car ID [{}] and Will Return it", carId);

		return CarRepresentation.convertCarModelToRepresentation(car);
	}

	public void editOldCar(EditOldCarDTO oldCarDTO) {

		logger.info("Edit Old Car Called");

		CarModel oldCar = null;

		try {
			oldCar = carModelDao.findOne(oldCarDTO.getCarId());
			Preconditions.checkNotNull(oldCar);
		} catch (NullPointerException e) {
			logger.error("No old Car found with the givin Car ID [{}]", oldCarDTO.getCarId());
			throw new NoCarFoundException("No Car Found with the giving Chassis Number");
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		if (!oldCar.getChassisNumber().equals(oldCarDTO.getChassisNumber())) {

			logger.info("Try to find old Car with the givin ChassisNumber [{}]", oldCarDTO.getChassisNumber());

			CarModel anotherCar = null;

			try {
				anotherCar = carModelDao.findOneByChassisNumber(oldCarDTO.getChassisNumber());
			} catch (Exception e) {
				logger.info("Error During Calling DB", e);
				throw new CommonEvolviceException("Error During Calling DB");
			}

			if (anotherCar != null) {
				logger.error("An old Car found with the givin ChassisNumber [{}] can't Edit The Giving Car",
						oldCarDTO.getChassisNumber());

				throw new CarAlreadyFoundException("there is a car with the modfied Chassis Number");
			}

			logger.info("No old Car found with the givin ChassisNumber [{}] Will Edit The Givin Car",
					oldCarDTO.getChassisNumber());

			oldCar.setChassisNumber(oldCarDTO.getChassisNumber());
		}

		oldCar.setBrand(oldCarDTO.getBrand());
		oldCar.setModel(oldCarDTO.getModel());
		oldCar.setModelDetials(oldCarDTO.getModelDetials());
		oldCar.setYearOfProduction(oldCarDTO.getYearOfProduction());

		try {
			carModelDao.save(oldCar);
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");

		}

	}

	public void deleteOldCarByChassisNumber(String chassisNumber) {

		logger.info("Delete Old Car By Chassis Number Called");

		CarModel oldCar = null;

		try {
			oldCar = carModelDao.findOneByChassisNumber(chassisNumber);
			Preconditions.checkNotNull(oldCar);
		} catch (NullPointerException e) {
			throw new NoCarFoundException("No Car Found with the giving Chassis Number");
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		try {
			carModelDao.delete(oldCar);
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

	}

	public void deleteOldCarByItsId(Long carId) {

		logger.info("Delete Old Car By Car ID Called");

		CarModel oldCar = null;

		try {
			oldCar = carModelDao.findOne(carId);
			Preconditions.checkNotNull(oldCar);
		} catch (NullPointerException e) {
			throw new NoCarFoundException("No Car Found with the giving Chassis Number");
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

		try {
			carModelDao.delete(oldCar);
		} catch (Exception e) {
			logger.info("Error During Calling DB", e);
			throw new CommonEvolviceException("Error During Calling DB");
		}

	}

}
