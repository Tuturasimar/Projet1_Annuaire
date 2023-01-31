package fr.isika.cda23.projet1.models;

import java.util.ArrayList;
import java.util.List;

public class ListeStagiaires {

	private List<Stagiaire> liste = new ArrayList<>();
	
	public void ajouterStagiaire(Stagiaire stagiaire) {
		liste.add(stagiaire);
	}

	public List<Stagiaire> getListe() {
		return liste;
	}

}
