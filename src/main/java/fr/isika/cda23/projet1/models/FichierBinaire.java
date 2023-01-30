package fr.isika.cda23.projet1.models;

import java.io.RandomAccessFile;

public class FichierBinaire {

	/**
	 * Méthode qui écrit un nouveau Noeud à la fin du fichier binaire
	 * 
	 * @param noeud Noeud rajouté au fichier
	 */
	public static void ecritureFichier(Noeud noeud) {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			raf.seek(raf.length());

			raf.writeChars(noeud.getCle().nomLong());
			raf.writeChars(noeud.getCle().prenomLong());
			raf.writeChars(noeud.getCle().codePostalLong());
			raf.writeChars(noeud.getCle().promotionLong());
			raf.writeChars(noeud.getCle().getDate());
			raf.writeInt(noeud.getFilsGauche());
			raf.writeInt(noeud.getFilsDroit());
			raf.writeInt(noeud.getNextDoublon());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de lire et restituer les données d'un noeud
	 * 
	 * @param index l'index correspondant au noeud recherché
	 * @return un Noeud contenant un Stagiaire ainsi que les index de ses fils
	 *         Gauche et Droit et l'index du doublon dans la liste chaînée
	 */
	public static Noeud lireNoeud(int index) {

		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "r")) {
			// On place le pointeur au début du Noeud souhaité
			raf.seek(index * Stagiaire.TAILLE_NOEUD_MAX);
			String nomBin = "";
			String prenomBin = "";
			String codePostalBin = "";
			String promotionBin = "";
			String dateBin = "";
			int filsGaucheBin;
			int filsDroitBin;
			int nextDoublonBin;
			// On récupère toutes les données
			for (int i = 0; i < Stagiaire.TAILLE_NOM_MAX; i++) {
				nomBin += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_PRENOM_MAX; i++) {
				prenomBin += raf.readChar();
			}
			for (int i = 0; i < Stagiaire.TAILLE_CODE_POSTAL_MAX; i++) {
				codePostalBin += raf.readChar();
			}
			for (int i = 0; i < Stagiaire.TAILLE_PROMO_MAX; i++) {
				promotionBin += raf.readChar();
			}
			for (int i = 0; i < 4; i++) {
				dateBin += raf.readChar();
			}
			filsGaucheBin = raf.readInt();
			filsDroitBin = raf.readInt();
			nextDoublonBin = raf.readInt();

			// On instancie un nouveau Noeud en appliquant la méthode .trim() pour supprimer
			// les espaces inutiles
			Noeud stagiaire = new Noeud(
					new Stagiaire(nomBin.trim(), prenomBin.trim(), codePostalBin.trim(), promotionBin.trim(), dateBin));
			stagiaire.setFilsGauche(filsGaucheBin);
			stagiaire.setFilsDroit(filsDroitBin);
			stagiaire.setNextDoublon(nextDoublonBin);

//			System.out.println(index + " || nom : " + nomBin + " || prénom : " + prenomBin + " || cp : "
//					+ codePostalBin + " || promotion : " + promotionBin + " || date : " + dateBin + " || FG : "
//					+ filsGaucheBin + " || FD : " + filsDroitBin + " || LC : " + nextDoublonBin);

			return stagiaire;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Méthode permettant de récupérer seulement le nom d'un individu dans le fichier BIN
	 * @param index l'index du noeud correspondant
	 * @param raf permet d'utiliser des méthodes pour lire le fichier BIN
	 * @return une chaine de caractère
	 */
	public static String lireNom(int index, RandomAccessFile raf) {
		try {
			raf.seek(index);
			String nomBin = "";
			// On récupère le nom inscrit dans le fichier
			for (int i = 0; i < Stagiaire.TAILLE_NOM_MAX; i++) {
				nomBin += raf.readChar();
			}
			// On supprime les espaces inutiles
			return nomBin.trim();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * Méthode permettant d'écrire un entier dans le fichier binaire (index FG, index FD, index LC)
	 * @param index index du noeud correspondant
	 * @param raf permet d'utiliser des méthodes pour lire/écrire le fichier BIN
	 * @param noeud noeud qui sera rajouté dans le fichier
	 */
	public static void ecritureInt(int index, RandomAccessFile raf, Noeud noeud) {
		try {
			// On se place au bon endroit
			raf.seek(index);
			int indexFichier = raf.readInt();
			// Si ce que l'on cible (FG, FD, LC) existe déjà
			if (indexFichier != -1) {
				// appel récursif en prenant l'index récupéré dans le fichier
				noeud.ajouterNoeud(noeud, indexFichier);
			} else {
				// On remet le curseur avant l'entier dans le fichier
				raf.seek(index);
				// On le remplace par le dernier index (donc l'index du futur noeud ajouté)
				raf.writeInt(FichierBinaire.lastIndex());
				// On appelle la méthode pour écrire le noeud
				FichierBinaire.ecritureFichier(noeud);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * Méthode permettant de récupérer le dernier index du fichier binaire
	 * 
	 * @return un entier
	 */
	@SuppressWarnings("null")
	public static int lastIndex() {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			// Chaque Noeud fait 130 octets, donc on peut récupérer le dernier index en
			// divisant la longueur totale du fichier par 130
			return (int) raf.length() / Stagiaire.TAILLE_NOEUD_MAX;

		} catch (Exception e) {
			e.printStackTrace();
			return (Integer) null;
		}
	}
}
