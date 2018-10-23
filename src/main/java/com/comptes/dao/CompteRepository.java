package com.comptes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comptes.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

	
}