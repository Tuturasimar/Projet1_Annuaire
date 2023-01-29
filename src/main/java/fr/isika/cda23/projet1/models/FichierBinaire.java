package fr.isika.cda23.projet1.models;

import java.io.RandomAccessFile;

public class FichierBinaire {

	/**
	 * Méthode qui crée le fichier binaire et ajoute directement la racine
	 */
	public static void creationFichier() {

		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			Noeud racine = new Noeud(new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008"));

			raf.writeChars(racine.getCle().nomLong());
			raf.writeChars(racine.getCle().prenomLong());
			raf.writeChars(racine.getCle().codePostalLong());
			raf.writeChars(racine.getCle().promotionLong());
			raf.writeChars(racine.getCle().getDate());
			raf.writeInt(racine.getFilsGauche());
			raf.writeInt(racine.getFilsDroit());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui écrit un nouveau Noeud à la fin du fichier binaire
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de lire et restituer les données d'un noeud
	 * @param index l'index correspondant au noeud recherché
	 * @return un Noeud contenant un Stagiaire ainsi que les index de ses fils Gauche et Droit
	 */
	public static Noeud lireNoeud(int index) {

		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "r")) {
			raf.seek(index * 126);
			String nomBin = "";
			String prenomBin = "";
			String codePostalBin = "";
			String promotionBin = "";
			String dateBin = "";
			int filsGaucheBin;
			int filsDroitBin;
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

			Noeud stagiaire = new Noeud(
					new Stagiaire(nomBin.trim(), prenomBin.trim(), codePostalBin.trim(), promotionBin.trim(), dateBin));
			stagiaire.setFilsGauche(filsGaucheBin);
			stagiaire.setFilsDroit(filsDroitBin);

			return stagiaire;

//			System.out.println(index + " || nom : " + nomBin + " || prénom : " + prenomBin + " || cp : "
//					+ codePostalBin + " || promotion : " + promotionBin + " || date : " + dateBin + " || FG : "
//					+ filsGaucheBin + " || FD : " + filsDroitBin);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Méthode permettant de récupérer le dernier index du fichier binaire
	 * @return un entier
	 */
	@SuppressWarnings("null")
	public static int lastIndex() {
		try (RandomAccessFile raf = new RandomAccessFile("STAGIAIRES.bin", "rw")) {
			return (int) raf.length() / 126;

		} catch (Exception e) {
			e.printStackTrace();
			return (Integer) null;
		}
	}
}
