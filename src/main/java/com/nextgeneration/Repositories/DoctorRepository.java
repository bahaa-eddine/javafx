package com.nextgeneration.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgeneration.Entites.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer>{

}
