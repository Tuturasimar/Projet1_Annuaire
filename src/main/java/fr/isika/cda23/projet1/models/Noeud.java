package fr.isika.cda23.projet1.models;

import java.util.spi.AbstractResourceBundleProvider;

/**
 * La classe Noeud représente un élément de l'arbre binaire
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
	 * @param cle est le Stagiaire que l'on souhaite rajouter
	 */
	public Noeud(Stagiaire cle) {
		this.cle = cle;
		filsGauche = null;
		filsDroit = null;
	}
	
	public void ajouterFils(String nom, String prenom,int codePostal, String promotion , String date, boolean contratPro) {
		if(cle.getNom().compareTo(nom) > 0) {
			if(filsGauche == null) {
				filsGauche = new Noeud(new Stagiaire(nom, prenom, codePostal, promotion, date));
			} else {
				filsGauche.ajouterFils(nom, prenom, codePostal, promotion, date, contratPro);
			}
		} else if (cle.getNom().compareTo(nom) < 0) {
			if(filsDroit == null) {
				filsDroit = new Noeud(new Stagiaire(nom, prenom, codePostal, promotion, date));
			} else {
				filsDroit.ajouterFils(nom, prenom, codePostal, promotion, date, contratPro);
			}
		} else {
			System.out.println("Clé déjà existante. Renseignez une autre valeur");
		}
	}
	
	public void parcoursInfixe() {
		if(this.filsGauche != null) {
			this.filsGauche.parcoursInfixe();
		}
		System.out.println(this.cle.getNom() + " " + this.cle.getPrenom());
		if(this.filsDroit != null) {
			this.filsDroit.parcoursInfixe();
		}
	}
	
	
	
	
}
