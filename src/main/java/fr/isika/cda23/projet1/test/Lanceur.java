package fr.isika.cda23.projet1.test;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.FileDriver;
import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;

public class Lanceur {

	public static void main(String[] args) {

//		FichierBinaire.creationFichier();
//		FileDriver.readTextFile();
		
		FichierBinaire.lireFichier(0);

		//FichierBinaire.ecritureFichier(new Noeud(new Stagiaire("Blake", "Sandy", "63", "AOE", "2007")));
		System.out.println(FichierBinaire.lastIndex());
		

		// racine.parcoursInfixe();

//		Noeud racine = new Noeud(new Stagiaire("Kyle", "Stephan", 75, "AOE", "2007"));
//
//		racine.ajouterFils("Blake", "Sandy", 63, "AOE", "2007");
//		racine.ajouterFils("Vanz", "Damian", 20, "AOE", "2007");
//		racine.ajouterFils("Artum", "Sadam", 92, "AOE", "2010");
//		racine.ajouterFils("Monark", "Elsa", 55, "AOE", "2010");
//		racine.ajouterFils("Nanty", "Isabelle", 78, "AOE", "2017");
//		racine.ajouterFils("Zangbar", "Will", 93, "AOE", "2016");
//		racine.ajouterFils("Ouioui", "Sandy", 63, "AOE", "2007");
//		racine.ajouterFils("Lawrence", "Damian", 20, "AOE", "2007");
//		racine.ajouterFils("Mutin", "Sadam", 92, "AOE", "2010");
//		racine.ajouterFils("Baobab", "Elsa", 55, "AOE", "2010");
//		racine.ajouterFils("Bob", "Isabelle", 78, "AOE", "2017");
//		racine.ajouterFils("Aazimar", "Will", 93, "AOE", "2016");
//		
//		racine.parcoursInfixe();
//		System.out.println("--------------");
//		
//		//racine.rechercheSupprimer("Kyle");
//		racine.rechercheSupprimer("Nanty");
//		
//		racine.parcoursInfixe();
	}

}
