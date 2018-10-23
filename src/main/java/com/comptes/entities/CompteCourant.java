package com.comptes.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity 
@DiscriminatorValue("CC") 
public class CompteCourant extends Compte {
	private static final long serialVersionUID = 1L;
	private double decouvert;

	public CompteCourant() {
	}

	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
		super(codeCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
