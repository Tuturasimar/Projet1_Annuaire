package fr.isika.cda23.projet1.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.scene.control.TableColumn.CellDataFeatures;

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
	private int filsGauche;

	/**
	 * filsDroit correspond à un Noeud disposé à droite dans l'arbre
	 */
	private int filsDroit;

	/**
	 * Constructeur du Noeud. Par défaut, ses deux fils sont null
	 * 
	 * @param cle est le Stagiaire que l'on souhaite rajouter
	 */
	public Noeud(Stagiaire cle) {
		this.cle = cle;
		filsGauche = -1;
		filsDroit = -1;
	}

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	public void ajouterNoeud(String nom, int index) {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			int startPosition = index * Stagiaire.TAILLE_NOEUD_MAX;
			raf.seek(startPosition);
			String nomBin = "";
			for (int i = 0; i < Stagiaire.TAILLE_NOM_MAX; i++) {
				nomBin += raf.readChar();
			}
			nomBin = nomBin.trim();

			if (nomBin.compareTo(nom) > 0) {
				raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_GAUCHE);
				int indexFilsGauche = raf.readInt();
				if (indexFilsGauche != -1) {
					ajouterNoeud(nom, indexFilsGauche);
				} else {
					raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_GAUCHE);
					raf.writeInt(FichierBinaire.lastIndex());
					FichierBinaire.ecritureFichier(this);
				}
			} else if (nomBin.compareTo(nom) < 0) {
				raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_DROIT);
				int indexFilsDroit = raf.readInt();
				if (indexFilsDroit != -1) {
					ajouterNoeud(nom, indexFilsDroit);
				} else {
					raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_DROIT);
					indexFilsDroit = FichierBinaire.lastIndex();
					raf.writeInt(indexFilsDroit);
					FichierBinaire.ecritureFichier(this);
				}
			} else {
				System.out.println(this.cle.getNom() + " ||||" + nom);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public void ajouterFils(String nom, String prenom, String codePostal, String promotion, String date) {
//		if (cle.getNom().compareTo(nom) > 0) {
//			if (filsGauche == null) {
//				filsGauche = new Noeud(new Stagiaire(nom, prenom, codePostal, promotion, date));
//			} else {
//				filsGauche.ajouterFils(nom, prenom, codePostal, promotion, date);
//			}
//		} else if (cle.getNom().compareTo(nom) < 0) {
//			if (filsDroit == null) {
//				filsDroit = new Noeud(new Stagiaire(nom, prenom, codePostal, promotion, date));
//			} else {
//				filsDroit.ajouterFils(nom, prenom, codePostal, promotion, date);
//			}
//		} else {
//			System.out.println("Clé déjà existante. Renseignez une autre valeur");
//		}
//	}
//
//	public void parcoursInfixe() {
//		if (this != null) {
//			if (this.filsGauche != null) {
//				this.filsGauche.parcoursInfixe();
//			}
//			if (this.cle != null) {
//				System.out.println(this.cle);
//			}
//			if (this.filsDroit != null) {
//				this.filsDroit.parcoursInfixe();
//			}
//		}
//	}
//
//	public void rechercheSupprimer(String nom) {
//		if (this.cle.getNom().compareTo(nom) == 0) {
//			supprimerNoeud(this);
//		} else if (this.cle.getNom().compareTo(nom) > 0) {
//			if (this.filsGauche != null) {
//				filsGauche.rechercheSupprimer(nom);
//			} else {
//				System.out.println("Element introuvable");
//			}
//		} else {
//			if (this.filsDroit != null) {
//				filsDroit.rechercheSupprimer(nom);
//			} else {
//				System.out.println("Element introuvable");
//			}
//		}
//
//	}
//
//	public Noeud chercherSuccesseur(Noeud noeud) {
//		Noeud noeudCourant = noeud.filsDroit;
//
//		while (noeudCourant.filsGauche != null) {
//			noeudCourant = noeudCourant.filsGauche;
//		}
//		return noeudCourant;
//	}
//
//	public void supprimerNoeud(Noeud noeud) {
//		if (noeud.filsGauche == null && noeud.filsDroit == null) {
//			this.cle = null;
//		} else if (noeud.filsGauche != null && noeud.filsDroit != null) {
//			this.cle = chercherSuccesseur(noeud).cle;
//			this.filsDroit.rechercheSupprimer(this.cle.getNom());
//		} else {
//			if (noeud.filsGauche != null) {
//				this.cle = noeud.filsGauche.cle;
//				this.filsGauche = null;
//			} else {
//				this.cle = noeud.filsDroit.cle;
//				this.filsDroit = null;
//			}
//		}
//
//	}

}
