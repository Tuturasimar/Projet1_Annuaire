package fr.isika.cda23.projet1.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui correspond à la liste qui contient l'ensemble des stagiaires à
 * afficher dans le tableau
 * 
 * @author Trévor
 *
 */
public class ListeStagiaires {

	private List<Stagiaire> liste = new ArrayList<>();

	/**
	 * Méthode pour ajouter un stagiaire à la liste
	 * 
	 * @param stagiaire Le Stagiaire rajouté à la liste
	 */
	public void ajouterStagiaire(Stagiaire stagiaire) {
		liste.add(stagiaire);
	}

	public List<Stagiaire> getListe() {
		return liste;
	}

	/**
	 * Méthode pour vider la liste
	 */
	public void clearList() {
		liste.clear();
	}

	/**
	 * Méthode pour initialiser la liste suite à un parcours infixe
	 * 
	 * @return une liste de stagiaire triée alphabétiquement
	 */
	public static ListeStagiaires listInitialisation() {
		ListeStagiaires liste = new ListeStagiaires();
		Noeud racine = FichierBinaire.lireNoeud(0);
		racine.parcoursInfixe(0, liste);
		return liste;
	}

	/**
	 * Méthode pour initialiser une liste suite à un parcours infixe, puis filtrer
	 * les données en fonction d'un filtre et d'une recherche utilisateur
	 * 
	 * @param filtre Le type de filtre renseigné par l'utilisateur
	 * @param recherche La recherche de l'utilisateur
	 * @return une liste de stagiaire triée alphabétiquement et filtrée en fonction des critères de recherche
	 */
	public static ListeStagiaires listFilterInitialisation(String filtre, String recherche) {
		ListeStagiaires liste = new ListeStagiaires();
		FichierBinaire.lireNoeud(0).parcoursInfixeFiltre(0, liste, filtre, recherche);
		return liste;
	}

}
