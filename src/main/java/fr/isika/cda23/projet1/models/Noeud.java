package fr.isika.cda23.projet1.models;

import java.util.spi.AbstractResourceBundleProvider;

/**
 * La classe Noeud représente un élément de l'arbre binaire
 * 
 * @author Trévor
 *
 */
public class Noeud {

	/**
	 * La clé de chaque Noeud est un Stagiaire
	 */
	private Stagiaire cle;

	/**
	 * filsGauche correspond à un Noeud disposé à gauche dans l'arbre
	 */
	private Noeud filsGauche;

	/**
	 * filsDroit correspond à un Noeud disposé à droite dans l'arbre
	 */
	private Noeud filsDroit;

	/**
	 * Constructeur du Noeud. Par défaut, ses deux fils sont null
	 * 
	 * @param cle est le Stagiaire que l'on souhaite rajouter
	 */
	public Noeud(Stagiaire cle) {
		this.cle = cle;
		filsGauche = null;
		filsDroit = null;
	}

	public void ajouterFils(String nom, String prenom, int codePostal, String promotion, String date) {
		if (cle.getNom().compareTo(nom) > 0) {
			if (filsGauche == null) {
				filsGauche = new Noeud(new Stagiaire(nom, prenom, codePostal, promotion, date));
			} else {
				filsGauche.ajouterFils(nom, prenom, codePostal, promotion, date);
			}
		} else if (cle.getNom().compareTo(nom) < 0) {
			if (filsDroit == null) {
				filsDroit = new Noeud(new Stagiaire(nom, prenom, codePostal, promotion, date));
			} else {
				filsDroit.ajouterFils(nom, prenom, codePostal, promotion, date);
			}
		} else {
			System.out.println("Clé déjà existante. Renseignez une autre valeur");
		}
	}

	public void parcoursInfixe() {
		if (this.filsGauche != null) {
			this.filsGauche.parcoursInfixe();
		}
		System.out.println(this.cle.getNom() + " " + this.cle.getPrenom());
		if (this.filsDroit != null) {
			this.filsDroit.parcoursInfixe();
		}
	}

	public void rechercheSupprimer(String nom) {
		if (this.cle.getNom().compareTo(nom) == 0) {
			supprimerNoeud(this);
			
		} else if (this.cle.getNom().compareTo(nom) > 0) {
			if (this.filsGauche != null) {
				filsGauche.rechercheSupprimer(nom);
			} else {
				System.out.println("Element introuvable");
			}
		} else {
			if(this.filsDroit != null) {
				filsDroit.rechercheSupprimer(nom);
			} else {
				System.out.println("Element introuvable");
			}
		}
	}
	
	public Noeud chercherSuccesseur(Noeud noeud) {
		Noeud noeudCourant = noeud.filsDroit;
		
		while(noeudCourant.filsGauche != null) {
			noeudCourant = noeudCourant.filsGauche;
		}
		return noeudCourant;
	}

	public Noeud supprimerNoeud(Noeud noeud) {
		if (noeud.filsGauche == null && noeud.filsDroit == null) {
			return null;
		} else if (noeud.filsGauche != null && noeud.filsDroit != null) {

			this.cle = chercherSuccesseur(noeud).cle;
			this.filsDroit.rechercheSupprimer(this.cle.getNom());
			return this;
		} else {
			if (noeud.filsGauche != null) {
				return noeud.filsGauche;
			} else {
				return noeud.filsDroit;
			}
		}

	}

}
