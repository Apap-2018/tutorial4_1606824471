package com.apap.tutorial4.service;

import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * DealerServiceImpl
 */
@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	@Override
	public List<DealerModel> findAll() {
		return dealerDb.findAll();
	}
	
	@Override
	public void deleteDealer(Long idDealer) {
		dealerDb.delete(dealerDb.findById(idDealer).get());
	}
	
	@Override
	public void updateDealer(Long idDealer, DealerModel dealer) {
		DealerModel updatedDealer = dealerDb.getOne(idDealer);
		updatedDealer.setAlamat(dealer.getAlamat());
		updatedDealer.setNoTelp(dealer.getNoTelp());
		dealerDb.save(updatedDealer);
	}
}
