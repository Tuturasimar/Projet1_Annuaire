package fr.isika.cda23.projet1.test;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.FileDriver;
import fr.isika.cda23.projet1.models.ListeStagiaires;
import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;

public class Lanceur {

	public static void main(String[] args) {

		// Si le fichier binaire n'existe pas encore ou n'est pas rempli
		if (FichierBinaire.lastIndex() == 0) {
			// On récupère l'ensemble des données du fichier texte pour les écrire dans le
			// fichier BIN
			FileDriver.readTextFile();
		}
		
		
		
		
//		racine.ajouterNoeud(new Noeud(new Stagiaire("GARIJO", "Rosie", "75", "AI 79", "2011")), 0);
//		racine.ajouterNoeud(new Noeud(new Stagiaire("GARIJO", "Watson", "75", "AI 79", "2011")), 0);
		//racine.ajouterNoeud(new Noeud(new Stagiaire("LACROIX", "Billy", "75", "AI 79", "2011")), 0);
		
//		racine.ajouterNoeud(new Noeud(new Stagiaire("POTIN", "Billy", "75", "AI 79", "2011")), 0);
//		racine.ajouterNoeud(new Noeud(new Stagiaire("POTIN", "Andréa", "75", "AI 79", "2011")), 0);


//		racine.rechercheSupprimer(new Noeud(new Stagiaire("GARIJO", "Rosie", "75", "AI 79", "2011")), 0, 0);
//		racine.rechercheSupprimer(new Noeud(new Stagiaire("NOUAR", "Adel", "94", "ATOD 5", "2009")), 0, 0);
//		racine.rechercheSupprimer(new Noeud(new Stagiaire("POTIN", "Thomas", "75", "ATOD 21", "2014")), 0, 0);
//		racine.rechercheSupprimer(new Noeud(new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008")), 0, 0);
//		racine.rechercheSupprimer(new Noeud(new Stagiaire("LACROIX", "Billy", "75", "AI 79", "2011")), 0, 0);


		

		System.out.println(FichierBinaire.lastIndex());

	}

}
