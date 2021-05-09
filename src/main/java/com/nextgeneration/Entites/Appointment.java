package com.nextgeneration.Entites;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private Doctor doctor;
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private Patient patient;
}
