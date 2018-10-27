package com.evolvice.cars.service.dto;

public class DeleteOldCarDTO {

	private long carId;

	private String chassisNumber;

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

}
