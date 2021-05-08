package com.nextgeneration.Entites;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Calendar {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	@OneToMany
	private List<Appointment> appointments;
	private Day day;
	private long week;
	private long year;

}