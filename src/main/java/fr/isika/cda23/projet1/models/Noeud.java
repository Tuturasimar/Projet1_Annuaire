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

	/**
	 * nextDoublon correspond à l'index du premier doublon dans une liste chaînée
	 */
	private int nextDoublon;

	public Noeud(Stagiaire cle, int filsGauche, int filsDroit, int nextDoublon) {
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.nextDoublon = nextDoublon;
	}

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
					Noeud noeudBin = FichierBinaire.lireNoeud(index);
					if (noeudBin.cle.equals(noeud.cle)) {
						System.out.println("Element déjà existant");
					} else {
						FichierBinaire.ecritureInt(startPosition + Stagiaire.INDEX_SUITE_LISTE_CHAINEE, raf, noeud);
					}
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
	public void parcoursInfixe(int index, ListeStagiaires liste) {
		if (this != null) {

			// Si un fils gauche existe pour ce noeud
			if (this.filsGauche != -1) {
				// On va récupérer les données du fils gauche
				Noeud filsGauche = FichierBinaire.lireNoeud(this.filsGauche);
				// Récursivité en envoyant comme index celui du fils gauche
				filsGauche.parcoursInfixe(this.filsGauche, liste);
			}
			if (this.nextDoublon != -1) {
				Noeud nextDoublon = FichierBinaire.lireNoeud(this.nextDoublon);
				nextDoublon.parcoursInfixe(this.nextDoublon, liste);
			}
			if (this.cle != null) {
				// Affiche suite à la récursivité l'ensemble des stagiaires par ordre
				// alphabétique
				liste.ajouterStagiaire(this.cle);

			}
			// Si un fils droit existe pour ce noeud
			if (this.filsDroit != -1) {
				// On récupère les données du fils droit
				Noeud filsDroit = FichierBinaire.lireNoeud(this.filsDroit);
				// Appel récursif en envoyant l'index du fils droit
				filsDroit.parcoursInfixe(this.filsDroit, liste);
			}
		}
	}

	/**
	 * Méthode qui va chercher dans l'arbre binaire le noeud à supprimer
	 * 
	 * @param noeud       noeud à supprimer
	 * @param indexCible  l'index du noeud ciblé actuellement dans le fichier BIN (0
	 *                    au premier appel)
	 * @param indexParent l'index du parent du noeud actuel (0 au premier appel)
	 */
	public void rechercheSupprimer(Noeud noeud, int indexCible, int indexParent) {
		try {
			RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw");
			String nom = noeud.cle.getNom();
			int startPosition = indexCible * Stagiaire.TAILLE_NOEUD_MAX;
			// On récupère le nom à l'index correspondant dans le BIN
			String nomBin = FichierBinaire.lireNom(startPosition, raf);
			// Si c'est le même nom
			if (nomBin.compareTo(nom) == 0) {
				// On compare l'entièreté de l'objet Stagiaire
				if (this.cle.equals(noeud.cle)) {
					// Si c'est identique, appel de la méthode pour supprimer le noeud
					supprimerNoeud(this, indexCible, indexParent);
				} else {
					// Sinon, nous sommes dans une liste chainée de doublons, appel récursif jusqu'à
					// trouver le bon
					Noeud nextListeChainee = FichierBinaire.lireNoeud(this.nextDoublon);
					nextListeChainee.rechercheSupprimer(noeud, this.nextDoublon, indexCible);
				}
				// Si le nom du noeud à supprimer est inférieur à celui lu dans le fichier BIN
				// --> a gauche dans l'arbre
			} else if (nomBin.compareTo(nom) > 0) {
				// On vérifie si un fils gauche existe déjà
				if (this.filsGauche != -1) {
					// Appel récursif si c'est le cas, on se déplace à gauche dans l'arbre
					Noeud filsGauche = FichierBinaire.lireNoeud(this.filsGauche);
					filsGauche.rechercheSupprimer(noeud, this.filsGauche, indexCible);
				} else {
					// L'élément n'existe pas
					System.out.println("Element introuvable");
				}
				// Sinon, le nom est supérieur
			} else {
				// On vérifie si un fils droit existe déjà
				if (this.filsDroit != -1) {
					// Appel récursif si c'est le cas, on se déplace à droite dans l'arbre
					Noeud filsDroit = FichierBinaire.lireNoeud(this.filsDroit);
					filsDroit.rechercheSupprimer(noeud, this.filsDroit, indexCible);
				} else {
					System.out.println("Element introuvable");
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Méthode pour chercher le successeur du noeud supprimé dans l'arbre qui
	 * prendra sa place
	 * 
	 * @param noeud le noeud qui sera supprimé
	 * @return un noeud (dont le nom est le plus petit à un cran à droite de
	 *         l'élément supprimé)
	 */
	public Noeud chercherSuccesseur(Noeud noeud) {
		// On se décale une fois sur la droite
		Noeud noeudCourant = FichierBinaire.lireNoeud(noeud.filsDroit);

		// On se dirige constamment à gauche dans l'arbre jusqu'à trouver le dernier
		// élément à gauche
		while (noeudCourant.filsGauche != -1) {
			noeudCourant = FichierBinaire.lireNoeud(noeudCourant.filsGauche);
		}
		return noeudCourant;
	}

	/**
	 * Méthode pour supprimer un noeud
	 * 
	 * @param noeud       le noeud que l'on supprime
	 * @param indexCible  l'index du noeud dans le fichier BIN
	 * @param indexParent l'index de son parent dans le fichier BIN
	 */
	public void supprimerNoeud(Noeud noeud, int indexCible, int indexParent) {
		Noeud parent = FichierBinaire.lireNoeud(indexParent);
		// Condition pour vérifier s'il existe une liste chainée -- La racine de l'arbre
		// sera aussi dedans
		if (noeud.nextDoublon != -1 || parent.cle.getNom().compareTo(noeud.cle.getNom()) == 0) {
			// Appel de la méthode pour supprimer la liste chainée
			supprimerDansListeChainee(parent, noeud, indexCible, indexParent);
		} else {
			// Appel de la méthode qui gère les différents cas de suppression
			casDeSuppressions(noeud, parent, indexCible, indexParent);
		}
	}

	/**
	 * Méthode pour supprimer un élément d'une liste chainée (début, milieu ou fin)
	 * 
	 * @param parent      noeud parent du noeud supprimé
	 * @param noeud       noeud supprimé
	 * @param indexCible  index du noeud supprimé dans le fichier BIN
	 * @param indexParent index du parent dans le fichier BIN
	 */
	public void supprimerDansListeChainee(Noeud parent, Noeud noeud, int indexCible, int indexParent) {
		// Si la racine de l'arbre n'a pas de doublons ou si on se trouve en fin de
		// liste chainée
		if (noeud.nextDoublon == -1) {
			// Si c'est la racine de l'arbre
			if (indexCible == indexParent) {
				// Appel des méthodes de suppression
				casDeSuppressions(noeud, parent, indexCible, indexParent);
			} else {
				// On remplace l'index de la liste chainée du parent par - 1
				FichierBinaire.remplacerFils(
						indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_SUITE_LISTE_CHAINEE, -1);
			}
			// Si c'est la racine de l'arbre avec des doublons ou milieu de chaine
		} else if (parent.cle.getNom().compareTo(noeud.cle.getNom()) == 0) {
			// Si c'est la racine de l'arbre
			if (indexCible == indexParent) {
				Noeud nextDoublon = FichierBinaire.lireNoeud(noeud.nextDoublon);
				// On remplace dans le fichier les valeurs du stagiaire par son doublon
				FichierBinaire.remplacerStagiaire(indexCible, nextDoublon);
				// On remplace l'index de la liste chainée par la valeur du doublon qui suit
				// celui qui remplace
				FichierBinaire.remplacerFils(indexCible + Stagiaire.INDEX_SUITE_LISTE_CHAINEE, nextDoublon.nextDoublon);
			} else {
				// On remplace l'index de la liste chainée du parent par le doublon suivant le
				// noeud supprimé
				FichierBinaire.remplacerFils(
						indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_SUITE_LISTE_CHAINEE,
						noeud.nextDoublon);
			}
		} else {
			// C'est le début d'une liste chainée (hors racine d'arbre)
			Noeud nextDoublon = FichierBinaire.lireNoeud(noeud.nextDoublon);
			FichierBinaire.remplacerStagiaire(indexCible * Stagiaire.TAILLE_NOEUD_MAX, nextDoublon);
			FichierBinaire.remplacerFils(indexCible * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_SUITE_LISTE_CHAINEE,
					nextDoublon.nextDoublon);
		}
	}

	/**
	 * Méthode pour supprimer une feuille d'un arbre (pas d'enfants)
	 * 
	 * @param parent      Noeud qui représente le parent du noeud supprimé
	 * @param indexCible  index du noeud supprimé dans le fichier
	 * @param indexParent index du parent dans le fichier
	 */
	public void supprimerFeuille(Noeud parent, int indexCible, int indexParent) {
		// Si l'index du noeud supprimé correspond au fils gauche du parent
		if (parent.filsGauche == indexCible) {
			// On remplace la valeur de l'index du FG chez le parent par - 1
			FichierBinaire.remplacerFils(indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_ACCES_FILS_GAUCHE,
					-1);
			// Si l'index du noeud supprimé correspond au fils droit du parent
		} else {
			// On remplace la valeur de l'index du FD chez le parent par - 1
			FichierBinaire.remplacerFils(indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_ACCES_FILS_GAUCHE,
					-1);
		}
	}

	/**
	 * Méthode pour supprimer une racine qui dispose d'un seul enfant
	 * 
	 * @param parent      Noeud qui représente le parent du noeud supprimé
	 * @param noeud       noeud qui est supprimé
	 * @param indexCible  index du noeud supprimé dans le fichier
	 * @param indexParent index du parent dans le fichier
	 */
	public void supprimerRacineUnEnfant(Noeud parent, Noeud noeud, int indexCible, int indexParent) {
		// Si le fils du noeud est à gauche
		if (noeud.filsGauche != -1) {
			// On vérifie si le noeud à supprimer est le fils gauche de son parent
			if (parent.filsGauche == indexCible) {
				// On remplace la valeur de l'index du FG du parent par l'index de l'enfant du
				// noeud supprimé (ici le FG)
				FichierBinaire.remplacerFils(
						indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_ACCES_FILS_GAUCHE, this.filsGauche);
				// Ou son fils droit
			} else {
				// On remplace la valeur de l'index du FD par l'index de l'enfant du noeud
				// supprimé (ici le FG)
				FichierBinaire.remplacerFils(
						indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_ACCES_FILS_DROIT, this.filsGauche);
			}
			// Si le fils du noeud est à droite
		} else {
			// On vérifie si le noeud à supprimer est le fils gauche de son parent
			if (parent.filsGauche == indexCible) {
				// On remplace la valeur de l'index du FG du parent par l'index de l'enfant du
				// noeud supprimé (ici le FD)
				FichierBinaire.remplacerFils(
						indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_ACCES_FILS_GAUCHE, this.filsDroit);
			} else {
				// On remplace la valeur de l'index du FD du parent par l'index de l'enfant du
				// noeud supprimé (ici le FD)
				FichierBinaire.remplacerFils(
						indexParent * Stagiaire.TAILLE_NOEUD_MAX + Stagiaire.INDEX_ACCES_FILS_DROIT, this.filsDroit);
			}
		}
	}

	/**
	 * Méthode pour supprimer une racine qui dispose de deux enfants
	 * 
	 * @param noeud      Noeud à supprimer
	 * @param indexCible index du noeud dans le fichier Bin
	 */
	public void supprimerRacineDeuxEnfants(Noeud noeud, int indexCible) {
		// On remplace dans le fichier le stagiaire par son successeur
		FichierBinaire.remplacerStagiaire(indexCible * Stagiaire.TAILLE_NOEUD_MAX, chercherSuccesseur(noeud));
		Noeud filsDroit = FichierBinaire.lireNoeud(this.filsDroit);
		// On lance la suppression du successeur dans l'arbre pour éviter le doublon
		filsDroit.rechercheSupprimer(chercherSuccesseur(noeud), this.filsDroit, indexCible);
	}

	/**
	 * Méthode qui recense les différents cas de suppression
	 * 
	 * @param noeud       Noeud à supprimer
	 * @param parent      Noeud du parent
	 * @param indexCible  index du noeud à supprimer dans le fichier BIN
	 * @param indexParent index du parent dans le fichier BIN
	 */
	public void casDeSuppressions(Noeud noeud, Noeud parent, int indexCible, int indexParent) {
		// Si le noeud à supprimer est une feuille
		if (noeud.filsGauche == -1 && noeud.filsDroit == -1) {
			supprimerFeuille(parent, indexCible, indexParent);
			// Si le noeud à supprimer dispose de deux enfants
		} else if (noeud.filsGauche != -1 && noeud.filsDroit != -1) {
			supprimerRacineDeuxEnfants(noeud, indexCible);
			// Si le noeud à supprimer a un seul enfant
		} else {
			supprimerRacineUnEnfant(parent, noeud, indexCible, indexParent);
		}
	}

}
