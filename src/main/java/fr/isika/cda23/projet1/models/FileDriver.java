package fr.isika.cda23.projet1.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de gérer l'extraction des données du fichier texte
 * @author Trévor
 *
 */
public class FileDriver {

	/**
	 * Méthode pour extraire les données d'un fichier texte afin de remplir le futur fichier BIN
	 */
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
				// si la ligne lue est différente de *, on récupère chaque ligne lue et on l'ajoute à la liste
				if (ligne.compareTo("*") != 0) {
					dataStagiaire = ligne.split("\n");
					stagiaireList.add(dataStagiaire[0]);
				} else {
					// Si la ligne lue est le marqueur qui différencie les stagiaires
					// On crée un nouveau stagiaire, puis on l'ajoute
					String[] dataNewStagiaire = stagiaireList.toArray(String[]::new);
					Noeud nouveauStagiaire = new Noeud(new Stagiaire(dataNewStagiaire[0].toUpperCase(), dataNewStagiaire[1], dataNewStagiaire[2], dataNewStagiaire[3], dataNewStagiaire[4]));
					nouveauStagiaire.ajouterNoeud(nouveauStagiaire,0);
					// Une fois le stagiaire ajouté, on supprime le contenu de la liste
					// On recommence tant qu'il n'y a plus rien à lire sur le fichier
					stagiaireList.clear();
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
