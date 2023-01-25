package fr.isika.cda23.projet1.models;

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
}
