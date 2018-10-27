package com.evolvice.cars.service.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.evolvice.cars.service.model.CarModel;

@Repository
public interface CarModelDao extends JpaRepository<CarModel, Long> {

	public CarModel findOneByChassisNumber(String chassisNumber);
}
