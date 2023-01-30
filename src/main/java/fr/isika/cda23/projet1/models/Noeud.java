package fr.isika.cda23.projet1.models;

import java.io.IOException;
import java.io.RandomAccessFile;

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
	 * filsGauche correspond à l'index du Noeud disposé à gauche dans l'arbre
	 */
	private int filsGauche;

	/**
	 * filsDroit correspond à l'index du Noeud disposé à droite dans l'arbre
	 */
	private int filsDroit;

	private int nextDoublon;

	/**
	 * Constructeur du Noeud. Par défaut, ses deux fils sont null
	 * 
	 * @param cle est le Stagiaire que l'on souhaite rajouter
	 */
	public Noeud(Stagiaire cle) {
		this.cle = cle;
		filsGauche = -1;
		filsDroit = -1;
		nextDoublon = -1;
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

	public int getNextDoublon() {
		return nextDoublon;
	}

	public void setNextDoublon(int nextDoublon) {
		this.nextDoublon = nextDoublon;
	}

	/**
	 * Méthode pour trouver l'emplacement du nouveau Noeud dans l'arbre binaire. On
	 * modifie l'index (gauche ou droit) du parent en conséquence.
	 * 
	 * @param noeud noeud que l'on souhaite rajouter au fichier
	 * @param index représente la place du Noeud dans le fichier. Lors du premier
	 *              appel de cette méthode, il est à 0 pour parcourir l'arbre depuis
	 *              la racine.
	 * 
	 */
	public void ajouterNoeud(Noeud noeud, int index) {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			// Si le fichier est déjà rempli
			if (raf.length() != 0) {
				String nom = noeud.cle.getNom();
				// On place le curseur au niveau du début du noeud que l'on analyse dans le
				// fichier (0 au premier appel de la méthode pour commencer par la racine de
				// l'arbre
				int startPosition = index * Stagiaire.TAILLE_NOEUD_MAX;
				// On appelle une méthode pour récupérer le nom inscrit dans le fichier
				String nomBin = FichierBinaire.lireNom(startPosition, raf);
				// Si le nom est "inférieur" à celui récupéré dans le fichier -> on va à gauche
				// dans l'arbre
				if (nomBin.compareTo(nom) > 0) {
					FichierBinaire.ecritureInt(startPosition + Stagiaire.INDEX_ACCES_FILS_GAUCHE, raf, noeud);
					// Si le nom est supérieur à celui récupéré dans le fichier -> on va à droite
					// dans l'arbre
				} else if (nomBin.compareTo(nom) < 0) {
					FichierBinaire.ecritureInt(startPosition + Stagiaire.INDEX_ACCES_FILS_DROIT, raf, noeud);
					// Si le nom est identique, on vérifie la liste chaînée
				} else {
					FichierBinaire.ecritureInt(startPosition + Stagiaire.INDEX_SUITE_LISTE_CHAINEE, raf, noeud);
				}
			} else {
				// Si le fichier est vide, on écrit de suite le nouveau noeud
				FichierBinaire.ecritureFichier(this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui permet de parcourir le fichier et de trier par ordre alphabétique
	 * les stagiaires
	 * 
	 * @param index index actuel de la recherche (au début du parcours : 0)
	 */
	public void parcoursInfixe(int index) {
		if (this != null) {

			// Si un fils gauche existe pour ce noeud
			if (this.filsGauche != -1) {
				// On va récupérer les données du fils gauche
				Noeud filsGauche = FichierBinaire.lireNoeud(this.filsGauche);
				// Récursivité en envoyant comme index celui du fils gauche
				filsGauche.parcoursInfixe(this.filsGauche);
			}
			if (this.nextDoublon != -1) {
				Noeud nextDoublon = FichierBinaire.lireNoeud(this.nextDoublon);
				nextDoublon.parcoursInfixe(this.nextDoublon);
			}
			if (this.cle != null) {
				// Affiche suite à la récursivité l'ensemble des stagiaires par ordre
				// alphabétique
				System.out.println(this.cle);
			}

			// Si un fils droit existe pour ce noeud
			if (this.filsDroit != -1) {
				// On récupère les données du fils droit
				Noeud filsDroit = FichierBinaire.lireNoeud(this.filsDroit);
				// Appel récursif en envoyant l'index du fils droit
				filsDroit.parcoursInfixe(this.filsDroit);
			}
		}
	}

	public void rechercheSupprimer(Noeud noeud, int index) {

	}

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
