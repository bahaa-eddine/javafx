package com.nextgeneration.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgeneration.Entites.Facture;
import com.nextgeneration.Repositories.FactureRepository;

@Service
public class FactureService {
	
	@Autowired
	private FactureRepository factureRepository;
	
	public Iterable<Facture> getAll() {
		return factureRepository.findAll();
	}
	
	public Facture getById(int id) {
		return factureRepository.findById(id).get();
	}
	
	public void delete(int id) {
		factureRepository.deleteById(id);
	}
	
	public Facture create(Facture facture) {
		return factureRepository.save(facture);
	}
	
	public Facture update(int id,Facture facture) {
		Facture old = getById(id);
		old.setAppointment(facture.getAppointment());
		old.setPrice(facture.getPrice());
		old.setReduction(facture.getReduction());
		return factureRepository.save(old);
	}

}
