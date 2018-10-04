package com.apap.tutorial4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.repository.CarDb;

/**
 * CarServiceImpl
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
	}
	
	@Override
	public void deleteCar(Long idCar) {
		carDb.delete(carDb.findById(idCar).get());
	}
	
	@Override
	public void updateCar(Long idCar, CarModel car) {
		CarModel updatedCar = carDb.getOne(idCar);
		updatedCar.setBrand(car.getBrand());
		updatedCar.setType(car.getType());
		updatedCar.setPrice(car.getPrice());
		updatedCar.setAmount(car.getAmount());
		carDb.save(updatedCar);
	}
	
	@Override
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
	}

}
