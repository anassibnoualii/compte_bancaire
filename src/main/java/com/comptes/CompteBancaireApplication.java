package com.comptes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.comptes.dao.ClientRepository;
import com.comptes.dao.CompteRepository;
import com.comptes.dao.OperationRepository;
import com.comptes.entities.Client;
import com.comptes.entities.Compte;
import com.comptes.entities.CompteCourant;
import com.comptes.entities.CompteEpargne;
import com.comptes.entities.Retrait;
import com.comptes.entities.Versement;
import com.comptes.metier.IBanque;

@SpringBootApplication
public class CompteBancaireApplication  {
	/*@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(CompteBancaireApplication.class, args);
	}

	
}
