package com.comptes.metier;

import org.springframework.data.domain.Page;

import com.comptes.entities.Compte;
import com.comptes.entities.Operation;

public interface IBanque {
	public Compte consulterCompte(String codeCompte);

	public void verser(String codeCompte, double montant);

	public void retirer(String codeCompte, double montant);

	public void virement(String cs, String cd, double montant);

	public Page<Operation> listOperation(String codeCompte, int page, int size);
}
