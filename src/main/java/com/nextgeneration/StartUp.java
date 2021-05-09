package com.nextgeneration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nextgeneration.Entites.Admin;
import com.nextgeneration.Entites.Appointment;
import com.nextgeneration.Entites.Calendar;
import com.nextgeneration.Entites.Doctor;
import com.nextgeneration.Entites.Patient;
import com.nextgeneration.Entites.Speciality;
import com.nextgeneration.Services.AdminService;
import com.nextgeneration.Services.CalendarService;

@Component
public class StartUp implements CommandLineRunner {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CalendarService calendarService;

	private Admin admin;
	private Patient patient;
	private Doctor doctor;
	private Appointment appointment;
	private Calendar calendar;
	private List<Appointment> appointments;

	
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
    	
    	if(calendarService.count() == 0) {
    		doctor = new Doctor();
    		doctor.setFirstname("DX");
    		doctor.setLastname("DY");
    		doctor.setCIN("DZ");
    		doctor.setEmail("Da@a.aa");
    		doctor.setSpeciality(Speciality.Anesthesiology);
    		
    		patient = new Patient();
    		patient.setFirstname("PX");
    		patient.setLastname("PY");
    		patient.setCIN("PZ");
    		patient.setEmail("Pa@a.aa");
    		
    		appointment = new Appointment();
    		appointment.setDoctor(doctor);
    		appointment.setPatient(patient);


    		appointments = new ArrayList<Appointment>();
    		appointments.add(appointment);
    		
    		calendar = new Calendar();
    		calendar.setAppointments(appointments);
    		calendar.setDate(new Date());
    		
    		calendarService.create(calendar);
    	}
    }
}