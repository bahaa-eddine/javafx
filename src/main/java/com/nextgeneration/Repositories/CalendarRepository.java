package com.nextgeneration.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgeneration.Entites.Calendar;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Integer>{
	List<Calendar> findAllByDate(Date date);
}
