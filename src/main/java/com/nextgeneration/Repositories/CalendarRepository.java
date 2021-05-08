package com.nextgeneration.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgeneration.Entites.Calendar;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Integer>{

}
