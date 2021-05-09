package com.nextgeneration.Entites;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Calendar {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Appointment> appointments;
	@Temporal(TemporalType.DATE)
	private Date date;

}
