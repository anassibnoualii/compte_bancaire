package com.comptes.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.comptes.dao.CompteRepository;
import com.comptes.dao.OperationRepository;
import com.comptes.entities.Compte;
import com.comptes.entities.CompteCourant;
import com.comptes.entities.Operation;
import com.comptes.entities.Retrait;
import com.comptes.entities.Versement;

public class IBanqueImpl implements IBanque {
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte c = compteRepository.findById(codeCompte).get();
		if (c == null)
			throw new RuntimeException("Compte inexistant !!!");
		return c;
	} 

	@Override
	public void verser(String codeCompte, double montant) {
		Compte c = consulterCompte(codeCompte);
		Versement v = new Versement(new Date(), montant, c);
		operationRepository.save(v);
		c.setSolde(c.getSolde() + montant);
		compteRepository.save(c);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte c = consulterCompte(codeCompte);
		double faciliteCaisse = 0.0;
		if (c instanceof CompteCourant) {
			faciliteCaisse = ((CompteCourant) c).getDecouvert();
		}
		if (c.getSolde() + faciliteCaisse < montant) {
			throw new RuntimeException("Solde insuffisant");
		}
		Retrait r = new Retrait(new Date(), montant, c);
		operationRepository.save(r);
		c.setSolde(c.getSolde() - montant);
		compteRepository.save(c);
	}

	@Override
	public void virement(String cs, String cd, double montant) {
		retirer(cs, montant);
		verser(cd, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) {
		return operationRepository.listOperation(codeCompte, new PageRequest(page, size));
	}


}
