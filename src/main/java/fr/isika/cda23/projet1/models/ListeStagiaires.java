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
	
	public void clearList() {
		liste.clear();
	}
	
	public static ListeStagiaires listInitialisation() {
		ListeStagiaires liste = new ListeStagiaires();
		Noeud racine = FichierBinaire.lireNoeud(0);
		racine.parcoursInfixe(0, liste);
		return liste;
	}
	
	public static ListeStagiaires listFilterInitialisation(String filtre, String recherche) {
		ListeStagiaires liste = new ListeStagiaires();
		FichierBinaire.lireNoeud(0).parcoursInfixeFiltre(0, liste, filtre, recherche);
		return liste;
	}

}
