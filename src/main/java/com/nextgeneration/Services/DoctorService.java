package com.nextgeneration.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgeneration.Entites.Doctor;
import com.nextgeneration.Repositories.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Iterable<Doctor> getAll() {
		return doctorRepository.findAll();
	}
	
	public Doctor getById(int id) {
		return doctorRepository.findById(id).get();
	}
	
	public void delete(int id) {
		doctorRepository.deleteById(id);
	}
	
	public Doctor create(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public Doctor update(int id,Doctor doctor) {
		Doctor old = getById(id);
		old.setCIN(doctor.getCIN());
		old.setEmail(doctor.getEmail());
		old.setFirstname(doctor.getFirstname());
		old.setLastname(doctor.getLastname());
		old.setPhone(doctor.getPhone());
		old.setSpeciality(doctor.getSpeciality());
		return doctorRepository.save(old);
	}
	
	public long count() {
		return doctorRepository.count();
	}

}
