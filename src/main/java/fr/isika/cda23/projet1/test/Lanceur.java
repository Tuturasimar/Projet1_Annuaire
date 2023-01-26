package fr.isika.cda23.projet1.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda23.projet1.models.Noeud;
import fr.isika.cda23.projet1.models.Stagiaire;

public class Lanceur {

	public static void main(String[] args) {

		Noeud racine = new Noeud(new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008"));

		// selection du fichier
		File file = new File("STAGIAIRES.DON");

		// ouvrir le fichier
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String ligne;
			String[] dataStagiaire = null;
			List<String> stagiaireList = new ArrayList<>();

			// extraction des données
			while ((ligne = bufferedReader.readLine()) != null) {
				if (ligne.compareTo("*") != 0) {
					dataStagiaire = ligne.split("\n");
					stagiaireList.add(dataStagiaire[0]);
				} else {
					String[] dataNewStagiaire = stagiaireList.toArray(String[]::new);
					racine.ajouterFils(dataNewStagiaire[0], dataNewStagiaire[1], dataNewStagiaire[2], dataNewStagiaire[3], dataNewStagiaire[4]);
					stagiaireList.clear();
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		racine.parcoursInfixe();
		
		

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
