package com.nextgeneration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nextgeneration.Entites.Admin;
import com.nextgeneration.Services.AdminService;

@Component
public class StartUp implements CommandLineRunner {

	@Autowired
	private AdminService adminService;
	private Admin admin;
	
    @Override
    public void run(String...args) throws Exception {
    	admin = new Admin();
    	admin.setFirstname("X");
    	admin.setLastname("Y");
    	admin.setCIN("Z");
    	admin.setEmail("a@a.aa");
    	admin.setUsername("admin");
    	admin.setPassword("admin");
    	if(!adminService.isExist(admin.getUsername())){
    		adminService.create(admin);
    	}
    }
}