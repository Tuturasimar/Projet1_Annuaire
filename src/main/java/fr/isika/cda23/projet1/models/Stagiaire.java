package fr.isika.cda23.projet1.models;

import java.util.Objects;

/**
 * La classe Stagiaire est celle qui représente l'ensemble des caractéristiques
 * associées à un stagiaire
 * 
 * @author Trévor
 *
 */
public class Stagiaire {

	
	public String nom;
	public String prenom;
	public String departement;
	public String promotion;
	public String annee;

	public final static int TAILLE_NOM_MAX = 21;
	public final static int TAILLE_PRENOM_MAX = 20;
	public final static int TAILLE_PROMO_MAX = 11;
	public final static int TAILLE_DEPARTEMENT_MAX = 3;
	public final static int TAILLE_NOEUD_MAX = 130;
	public final static int INDEX_ACCES_FILS_GAUCHE = 118;
	public final static int INDEX_ACCES_FILS_DROIT = 122;
	public final static int INDEX_SUITE_LISTE_CHAINEE = 126;

	/**
	 * Constructeur de la classe Stagiaire
	 * 
	 * @param nom        nom du stagiaire
	 * @param prenom     prénom du stagiaire
	 * @param codePostal code postal du stagiaire
	 * @param promotion  promotion du stagiaire
	 * @param date       date de la formation du stagiaire
	 */
	public Stagiaire(String nom, String prenom, String departement, String promotion, String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		// Certaines dates n'étant pas renseignées, on remplace le vide par des X qui prendront la même taille dans le fichier
		if (annee.equals("")) {
			this.annee = "XXXX";
		} else {
			this.annee = annee;
		}

	}

	/**
	 * méthode qui permet de rajouter des espaces à un nom pour atteindre la taille maximum imposé dans le fichier binaire
	 * @return une String de 21 caractères.
	 */
	public String nomLong() {
		String nomLong = "";

		if (nom.length() < TAILLE_NOM_MAX) {
			nomLong = nom;
			for (int i = nom.length(); i < TAILLE_NOM_MAX; i++) {
				nomLong += " ";
			}
		} else {
			nomLong = nom.substring(0, TAILLE_NOM_MAX); // au cas où le nom rentré est au delà de la taille MAX actuelle
		}
		return nomLong;
	}

	public String getNom() {
		return nom.trim();
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * méthode qui permet de rajouter des espaces à un prénom pour atteindre la taille maximum imposé dans le fichier binaire
	 * @return une String de 20 caractères
	 */
	public String prenomLong() {
		String prenomLong = "";

		if (prenom.length() < TAILLE_PRENOM_MAX) {
			prenomLong = prenom;
			for (int i = prenom.length(); i < TAILLE_PRENOM_MAX; i++) {
				prenomLong += " ";
			}
		} else {
			prenomLong = prenom.substring(0, TAILLE_PRENOM_MAX);
		}
		return prenomLong;
	}

	public String getPrenom() {
		return prenom.trim();
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * méthode qui permet de rajouter des espaces à un numéro de département pour atteindre la taille maximum imposé dans le fichier binaire
	 * @return une String de 3 caractères
	 */
	public String departementLong() {
		String codePostalLong = "";

		if (departement.length() < TAILLE_DEPARTEMENT_MAX) {
			codePostalLong = departement;
			for (int i = departement.length(); i < TAILLE_DEPARTEMENT_MAX; i++) {
				codePostalLong += " ";
			}
		} else {
			codePostalLong = departement.substring(0, TAILLE_DEPARTEMENT_MAX);
		}
		return codePostalLong;
	}

	public String getDepartement() {
		return departement.trim();
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	/**
	 * méthode qui permet de rajouter des espaces à un label de promotion pour atteindre la taille maximum imposé dans le fichier binaire
	 * @return une String de 11 caractères
	 */
	public String promotionLong() {
		String promotionLong = "";

		if (promotion.length() < TAILLE_PROMO_MAX) {
			promotionLong = promotion;
			for (int i = promotion.length(); i < TAILLE_PROMO_MAX; i++) {
				promotionLong += " ";
			}
		} else {
			promotionLong = promotion.substring(0, TAILLE_PROMO_MAX);
		}
		return promotionLong;
	}

	public String getPromotion() {
		return promotion.trim();
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String date) {
		this.annee = date;
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promotion="
				+ promotion + ", annee=" + annee + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(departement, annee, nom, prenom, promotion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		return Objects.equals(departement, other.departement) && Objects.equals(annee, other.annee)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(promotion, other.promotion);
	}
	
	

}
