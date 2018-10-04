package com.apap.tutorial4.service;

import com.apap.tutorial4.model.CarModel;

/*
 * CarService
 */
public interface CarService {
	void addCar(CarModel car);
	void deleteCar(Long idCar);
	void updateCar(Long idCar, CarModel car);
	public CarModel getCar(Long id);
	
}
