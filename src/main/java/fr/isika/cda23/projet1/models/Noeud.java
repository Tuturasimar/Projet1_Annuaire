package fr.isika.cda23.projet1.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.SeekableByteChannel;

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

	/**
	 * Méthode pour trouver l'emplacement du nouveau Noeud dans l'arbre binaire. On
	 * modifie l'index (gauche ou droit) du parent en conséquence.
	 * 
	 * @param nom   nom du futur Noeud que l'on va comparer aux autres nom déjà
	 *              présents dans le fichier
	 * @param index représente la place du Noeud dans le fichier. Lors du premier
	 *              appel de cette méthode, il est à 0 pour parcourir l'arbre depuis
	 *              la racine.
	 * 
	 */
	public void ajouterNoeud(String nom, int index) {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			// On récupère l'index de départ où le pointeur doit se situer dans le fichier
			// binaire
			int startPosition = index * Stagiaire.TAILLE_NOEUD_MAX;
			raf.seek(startPosition);
			String nomBin = "";
			// On récupère le nom inscrit dans le fichier
			for (int i = 0; i < Stagiaire.TAILLE_NOM_MAX; i++) {
				nomBin += raf.readChar();
			}
			// On supprime les espaces inutiles
			nomBin = nomBin.trim();

			// Si le nom que l'on souhaite ajouter est plus petit que celui à l'index
			// indiqué
			if (nomBin.compareTo(nom) > 0) {
				// On récupère l'index du fils gauche du Noeud actuel
				raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_GAUCHE);
				int indexFilsGauche = raf.readInt();
				// S'il est différent de -1 (donc, qu'un fils gauche est déjà inscrit dans le
				// fichier)
				if (indexFilsGauche != -1) {
					// On fait un appel récursif en renvoyant le nom que l'on souhaite rajouter
					// ainsi que l'index récupéré
					ajouterNoeud(nom, indexFilsGauche);
				} else {
					// Sinon on écrit à la place du -1 l'index de celui que l'on va rajouter (qui
					// est le dernier index)
					raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_GAUCHE);
					raf.writeInt(FichierBinaire.lastIndex());
					// On appelle la méthode qui permet d'écrire le contenu du Noeud dans le fichier
					FichierBinaire.ecritureFichier(this);
				}
				// Si le nom est plus grand que l'index indiqué
			} else if (nomBin.compareTo(nom) < 0) {
				// On récupère l'index du fils droit
				raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_DROIT);
				int indexFilsDroit = raf.readInt();
				if (indexFilsDroit != -1) {
					ajouterNoeud(nom, indexFilsDroit);
				} else {
					raf.seek(startPosition + Stagiaire.INDEX_ACCES_FILS_DROIT);
					raf.writeInt(FichierBinaire.lastIndex());
					FichierBinaire.ecritureFichier(this);
				}
			} else {
				// Cas où un nom est identique à un autre, pas encore traité
				System.out.println(this.cle.getNom() + " ||||" + nom);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui permet de parcourir le fichier et de trier par ordre alphabétique les stagiaires
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
			if (this.cle != null) {
				// Affiche suite à la récursivité l'ensemble des stagiaires par ordre alphabétique
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
