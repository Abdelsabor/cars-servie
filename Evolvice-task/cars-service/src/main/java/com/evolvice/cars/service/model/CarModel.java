package com.evolvice.cars.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CAR_MODEL")
public class CarModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE1")
	@SequenceGenerator(name = "SEQUENCE1", sequenceName = "SEQUENCE")
	@Column(name = "ID")
	private Long id;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "YEAR_OF_PRODUCTION")
	private String yearOfProduction;

	@Column(name = "MODEL_DETAILS")
	private String modelDetials;

	@Column(name = "CHASSIS_NUMBER", nullable = false, unique = true)
	private String chassisNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
