package com.nextgeneration.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgeneration.Entites.Patient;
import com.nextgeneration.Repositories.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Iterable<Patient> getAll() {
		return patientRepository.findAll();
	}
	
	public Patient getById(int id) {
		return patientRepository.findById(id).get();
	}
	
	public void delete(int id) {
		patientRepository.deleteById(id);
	}
	
	public Patient create(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public Patient update(int id,Patient patient) {
		Patient old = getById(id);
		old.setCIN(patient.getCIN());
		old.setEmail(patient.getEmail());
		old.setFirstname(patient.getFirstname());
		old.setLastname(patient.getLastname());
		old.setPhone(patient.getPhone());
		return patientRepository.save(old);
	}
	
	public long count() {
		return patientRepository.count();
	}

}
