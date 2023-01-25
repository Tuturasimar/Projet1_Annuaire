package fr.isika.cda23.projet1.test;

import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;

public class Lanceur {

	public static void main(String[] args) {


		Noeud racine = new Noeud(new Stagiaire("Kyle", "Stephan", 75, "AOE", "2007"));

		racine.ajouterFils("Blake", "Sandy", 63, "AOE", "2007");
		racine.ajouterFils("Vanz", "Damian", 20, "AOE", "2007");
		racine.ajouterFils("Artum", "Sadam", 92, "AOE", "2010");
		racine.ajouterFils("Monark", "Elsa", 55, "AOE", "2010");
		racine.ajouterFils("Nanty", "Isabelle", 78, "AOE", "2017");
		racine.ajouterFils("Zangbar", "Will", 93, "AOE", "2016");
		
		racine.parcoursInfixe();
		
		racine.rechercheSupprimer("Artum");
		
		System.out.println("--------------------------");

		racine.parcoursInfixe();
	}

}
 