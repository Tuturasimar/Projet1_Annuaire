package fr.isika.cda23.projet1.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDriver {

	public static void readTextFile() {
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
					Noeud nouveauStagiaire = new Noeud(new Stagiaire(dataNewStagiaire[0], dataNewStagiaire[1], dataNewStagiaire[2], dataNewStagiaire[3], dataNewStagiaire[4]));
					nouveauStagiaire.ajouterNoeud(nouveauStagiaire.getCle().getNom(),0);
					
//							racine.ajouterFils(dataNewStagiaire[0], dataNewStagiaire[1], dataNewStagiaire[2], dataNewStagiaire[3], dataNewStagiaire[4]);
					stagiaireList.clear();
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
