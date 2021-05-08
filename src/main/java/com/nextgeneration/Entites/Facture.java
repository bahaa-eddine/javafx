package com.nextgeneration.Entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Facture {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	@OneToOne
	private Appointment appointment;
	private Double price;
	private long reduction;
	

}
