package com.comptes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comptes.entities.Compte;
import com.comptes.entities.Operation;
import com.comptes.metier.IBanque;

@Controller
public class BanqueController {
	@Autowired
	IBanque banqueMetier;

	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}

	@RequestMapping("/consulterCompte")
	public String consulterCompte(Model model, String codeCompte) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte compte = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, 0, 4);
			model.addAttribute("listeOperations", pageOperations.getContent());
			model.addAttribute("compte", compte);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "comptes";
	}

	@RequestMapping(value = "/enregistrerOperation", method = RequestMethod.POST)
	public String enregisterOperation(Model model, String typeOperation, String codeCompte, double montant,
			String codeCompte2) {
		try {
			if (typeOperation.equals("VERS")) {
				banqueMetier.verser(codeCompte, montant);
			}
			if (typeOperation.equals("RET")) {
				banqueMetier.retirer(codeCompte, montant);
			}
			if (typeOperation.equals("VIR")) {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:consulterCompte?codeCompte=" + codeCompte + "&error=" + e.getMessage();
		}
		return "redirect:consulterCompte?codeCompte=" + codeCompte;
	}

}
