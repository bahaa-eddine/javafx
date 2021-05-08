package com.nextgeneration.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgeneration.Entites.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer>{

	public Admin findByUsername(String username);
}
