package com.nextgeneration.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgeneration.Entites.Appointment;
import com.nextgeneration.Repositories.AppointmentRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private CalendarService calendarService;
	
	public Iterable<Appointment> getAll() {
		return appointmentRepository.findAll();
	}
	
	public Appointment getById(int id) {
		return appointmentRepository.findById(id).get();
	}
	
	public void delete(int id) {
		appointmentRepository.deleteById(id);
	}
	
	public Appointment create(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}
	
	public Appointment update(int id,Appointment appointment) {
		Appointment old = getById(id);
		old.setDoctor(appointment.getDoctor());
		old.setPatient(appointment.getPatient());
		return appointmentRepository.save(old);
	}
	
	public List<Appointment> getAppointmentsByDate(Date date){
		return calendarService.getCalendarByDate(date).get(0).getAppointments();
	}

}
