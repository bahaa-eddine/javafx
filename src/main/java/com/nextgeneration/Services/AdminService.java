package com.nextgeneration.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgeneration.Entites.Admin;
import com.nextgeneration.Repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Iterable<Admin> getAll() {
		return adminRepository.findAll();
	}
	
	public Admin getById(int id) {
		return adminRepository.findById(id).get();
	}
	
	public void delete(int id) {
		adminRepository.deleteById(id);
	}
	
	public Admin create(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Admin update(int id,Admin admin) {
		Admin old = getById(id);
		old.setCIN(admin.getCIN());
		old.setEmail(admin.getEmail());
		old.setFirstname(admin.getFirstname());
		old.setLastname(admin.getLastname());
		old.setPhone(admin.getPhone());
		return adminRepository.save(old);
	}

	public boolean authenticate(String username, String password) {
		if(adminRepository.findByUsername(username) != null) {
			Admin admin = adminRepository.findByUsername(username);
			if(admin.getPassword().equals(password)) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean isExist(String username) {
		if(adminRepository.findByUsername(username) != null) {
			return true;
		}else {
			return false;
		}
	}
}
