package com.evolvice.cars.service.endPoints.representation;

import com.evolvice.cars.service.model.CarModel;

public class CarRepresentation {

	private long carId;

	private String brand;

	private String model;

	private String yearOfProduction;

	private String modelDetials;

	private String chassisNumber;

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(String yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getModelDetials() {
		return modelDetials;
	}

	public void setModelDetials(String modelDetials) {
		this.modelDetials = modelDetials;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public static CarRepresentation convertCarModelToRepresentation(CarModel carModel) {
		CarRepresentation car = new CarRepresentation();
		car.setBrand(carModel.getBrand());
		car.setCarId(carModel.getId());
		car.setChassisNumber(carModel.getChassisNumber());
		car.setModel(carModel.getModel());
		car.setYearOfProduction(carModel.getYearOfProduction());
		car.setModelDetials(carModel.getModelDetials());

		return car;
	}

}
