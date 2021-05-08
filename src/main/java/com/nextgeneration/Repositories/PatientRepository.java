package com.nextgeneration.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgeneration.Entites.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer>{

}
