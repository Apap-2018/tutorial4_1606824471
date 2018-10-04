package com.apap.tutorial4.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;

/**
 * DealerController
 */
@Controller
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
		dealerService.addDealer(dealer);
		return "add";
	}

	public CarService getCarService() {
		return carService;
	}

	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	
	// Menampilkan sebuah Dealer berdasarkan Id Dealer
	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
	private String viewDealer(@RequestParam(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		List<CarModel> carList = dealer.getListCar();
		carList.sort(Comparator.comparingLong(CarModel::getPrice));
		String urladdcar = "/car/add/"+dealerId;
		model.addAttribute("name", dealer.getId());
		model.addAttribute("alamat", dealer.getAlamat());
		model.addAttribute("telepon", dealer.getNoTelp());
		model.addAttribute("carList", carList);
		model.addAttribute("urladdcar", urladdcar);
		return "view-dealer";
	}
	
	@RequestMapping(value = "/dealer/viewall", method = RequestMethod.GET)
	private String viewDealer(Model model) {
		List<DealerModel> allDealer = dealerService.findAll();
	
		model.addAttribute("dealerlist", allDealer);
	
		return "viewall-dealer";
	}
	
	@RequestMapping(value = "/dealer/delete/{idDealer}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable(value = "idDealer") long idDealer, Model model) {
		dealerService.deleteDealer(idDealer);
		return "delete";
	}

	@RequestMapping(value = "/dealer/update/{id}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable(value = "id") Long id, Model model) {
		DealerModel dealer =  dealerService.getDealerDetailById(id).get();
		model.addAttribute("dealer", dealer);
		model.addAttribute("Iddealer", "ID Dealer:"+id);
		return "updateDealer";
	}
	
	@RequestMapping(value = "/dealer/update/{id}", method = RequestMethod.POST)
	private String updateDealerSubmit(@PathVariable(value = "id") Long id, @ModelAttribute Optional<DealerModel> dealer) {
		dealerService.updateDealer(id, dealer.get());
		return "update";
	}
}



