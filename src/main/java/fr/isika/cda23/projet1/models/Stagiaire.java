package fr.isika.cda23.projet1.models;

/**
 * La classe Stagiaire est celle qui représente l'ensemble des caractéristiques associées à un stagiaire
 * @author Trévor
 *
 */
public class Stagiaire {

	private String nom;
	private String prenom;
	private String codePostal;
	private String promotion;
	private String date;
	
	
	/**
	 * Constructeur de la classe Stagiaire
	 * @param nom nom du stagiaire
	 * @param prenom prénom du stagiaire
	 * @param codePostal code postal du stagiaire
	 * @param promotion promotion du stagiaire
	 * @param date date de la formation du stagiaire
	 * @param contratPro Renseigne si l'utilisateur est en contrat pro ou pas
	 */
	public Stagiaire(String nom, String prenom, String codePostal, String promotion, String date) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.codePostal = codePostal;
		this.promotion = promotion;
		this.date = date;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", codePostal=" + codePostal + ", promotion="
				+ promotion + ", date=" + date + "]";
	}
	
	

	
}
