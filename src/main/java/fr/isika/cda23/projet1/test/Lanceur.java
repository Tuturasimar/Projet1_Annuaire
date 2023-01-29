package fr.isika.cda23.projet1.test;

import fr.isika.cda23.projet1.models.FichierBinaire;
import fr.isika.cda23.projet1.models.FileDriver;
import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;

public class Lanceur {

	public static void main(String[] args) {

		// Si le fichier binaire n'existe pas encore ou n'est pas rempli
		if (FichierBinaire.lastIndex() == 0) {
			// On créé le fichier pour initialiser la racine
			FichierBinaire.creationFichier();
			// On récupère l'ensemble des données du fichier texte pour les écrire dans le fichier BIN
			FileDriver.readTextFile();
		}
		
		Noeud racine = FichierBinaire.lireNoeud(0);
		racine.parcoursInfixe(0);
		
		System.out.println(FichierBinaire.lastIndex());

		
	}

}
