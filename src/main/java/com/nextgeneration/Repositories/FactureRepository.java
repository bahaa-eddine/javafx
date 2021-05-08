package com.nextgeneration.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nextgeneration.Entites.Facture;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Integer>{

}
