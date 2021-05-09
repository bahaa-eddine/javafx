package com.nextgeneration.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgeneration.Entites.Calendar;
import com.nextgeneration.Repositories.CalendarRepository;

@Service
public class CalendarService {
	
	@Autowired
	private CalendarRepository calendarRepository;
	
	public Iterable<Calendar> getAll() {
		return calendarRepository.findAll();
	}
	
	public Calendar getById(int id) {
		return calendarRepository.findById(id).get();
	}
	
	public void delete(int id) {
		calendarRepository.deleteById(id);
	}
	
	public Calendar create(Calendar calendar) {
		return calendarRepository.save(calendar);
	}
	
	public Calendar update(int id,Calendar calendar) {
		Calendar old = getById(id);
		old.setAppointments(calendar.getAppointments());
		old.setDate(calendar.getDate());
		return calendarRepository.save(old);
	}
	
	public List<Calendar> getCalendarByDate(Date date){
		return calendarRepository.findAllByDate(date);
	}
	
	public long count() {
		return calendarRepository.count();
	}

}
